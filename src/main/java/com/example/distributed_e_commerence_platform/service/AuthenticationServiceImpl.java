package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Role;
import com.example.distributed_e_commerence_platform.Models.Session;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.Models.constants.SessionStatus;
import com.example.distributed_e_commerence_platform.Models.constants.Status;
import com.example.distributed_e_commerence_platform.Pojos.UserToken;
import com.example.distributed_e_commerence_platform.dtos.LoginRequestDto;
import com.example.distributed_e_commerence_platform.dtos.SignUpRequestDto;
import com.example.distributed_e_commerence_platform.exceptions.*;
import com.example.distributed_e_commerence_platform.repository.RoleRepo;
import com.example.distributed_e_commerence_platform.repository.SessionRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class AuthenticationServiceImpl implements IAuthService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder passwordEncoder;

    private final SecretKey secretKey;

    private final SessionRepo sessionRepo;



    public AuthenticationServiceImpl(UserRepo userRepo, RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder, SecretKey secretKey, SessionRepo sessionRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = bCryptPasswordEncoder;
        this.secretKey = secretKey;
        this.sessionRepo = sessionRepo;
    }


    @Override
    public User signUpUser(SignUpRequestDto signUpRequestDto) {

        Optional<User> optionalUser = userRepo.findByEmail(signUpRequestDto.getEmail());

        if( optionalUser.isPresent() ){
            throw new UserAlreadyExistException(" User Already Exist in Data base ");
        }

        User user = new User();
        user.setFirstName(
                signUpRequestDto.getFirstName());
        user.setLastName(
                signUpRequestDto.getLastName());
        user.setPhoneNumber(
                signUpRequestDto.getPhoneNumber());
        user.setUserStatus(
                Status.ACTIVE);
        user.setEmail(
                signUpRequestDto.getEmail());
        user.setPassword(
                passwordEncoder.encode(
                        signUpRequestDto.getPassword()));


        Role role;
        Optional<Role> optionalRole = roleRepo.findByRoleName("CUSTOMER");

        if ( optionalRole.isEmpty() ){
             role = new Role();
             role.setRoleName("CUSTOMER");
             role.setUpdatedAt(new Date());
             role.setCreatedAt(new Date() );

             role = roleRepo.save(role);
        }
        else {
            role  = optionalRole.get();
        }

        user.setRole(role);

        return userRepo.save(user);
    }

    @Override
    public UserToken loginUser(LoginRequestDto loginRequestDto) {

        Optional<User> optionalUser = userRepo.findByEmail(loginRequestDto.getEmail());

        if( optionalUser.isEmpty() ){
            throw new UserNotSignedUpException(" user not signed up , Please signup first ");
        }
        else
        {
            User user = optionalUser.get();
            if( passwordEncoder.matches(loginRequestDto.getPassword(),user.getPassword()))
            {
                HashMap<String,Object> payload = new LinkedHashMap<>();

                Long currentTimeInSeconds = System.currentTimeMillis() / 1000 ;

                payload.put("iat",currentTimeInSeconds);
                payload.put("exp",currentTimeInSeconds+(10*60));
                payload.put("user",user.getFirstName());
                payload.put("userId",user.getId());
                payload.put("iss","shiva kumar - Distributed E-commerence  ");
                payload.put("role",user.getRole().getRoleName());

                String jwtToken = Jwts.builder().claims(payload).signWith(secretKey).compact();

                Session session = new Session();
                session.setUserName(user.getFirstName());
                session.setUserToken(jwtToken);
                session.setCreatedAt(new Date());
                session.setUpdatedAt(new Date());
                session.setSessionStatus(SessionStatus.ACTIVE);

                sessionRepo.save(session);
                return new UserToken(jwtToken,user);
            }
            else {
                throw new PasswordIncorrectException(" Password doesn't matches , Please enter correct password ");
            }
        }

    }

    @Override
    public boolean validateToken(String userToken) {

        Optional<Session> optionalSession = sessionRepo.findByUserToken(userToken);

        if ( optionalSession.isEmpty() ){
           return false;
        }

        Session session = optionalSession.get();

        if(session.getSessionStatus() == SessionStatus.IN_ACTIVE){
            return false;
        }

        try{
            JwtParser  jwtParser = Jwts.parser().verifyWith(secretKey).build();
            Claims claims =jwtParser.parseSignedClaims(userToken).getPayload();

            Long expiryTime = (Long) claims.get("exp");
            Long currTimeInSeconds = System.currentTimeMillis() / 1000 ;

            if( currTimeInSeconds > expiryTime ){
                session.setSessionStatus(SessionStatus.IN_ACTIVE);
                sessionRepo.save(session);
                return  false;
            }

        }
        catch (Exception ex ){
            session.setSessionStatus(SessionStatus.IN_ACTIVE);
            sessionRepo.save(session);

            return false;
        }
        return true;
    }


    @Override
    public boolean logoutUser(String userToken) {

        Optional<Session> optionalSession =
                sessionRepo.findByUserToken(userToken);

        if(optionalSession.isEmpty()){
            return false;
        }

        Session session = optionalSession.get();

        session.setSessionStatus(SessionStatus.IN_ACTIVE);
        session.setUpdatedAt(new Date());

        sessionRepo.save(session);

        return true;
    }
}
