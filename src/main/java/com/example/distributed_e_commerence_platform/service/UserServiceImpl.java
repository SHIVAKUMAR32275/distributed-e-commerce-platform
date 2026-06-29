package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Role;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.Models.constants.Status;
import com.example.distributed_e_commerence_platform.dtos.UserRequestDto;
import com.example.distributed_e_commerence_platform.dtos.UserResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.UserNotFoundException;
import com.example.distributed_e_commerence_platform.repository.RoleRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepo userRepo , RoleRepo roleRepo, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }




    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        Role role = roleRepo.findById(userRequestDto.getRoleId())
                .orElseThrow(()-> new UserNotFoundException(" role with roleId "+userRequestDto.getRoleId()+ " is not found"));



        User user = new User();
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        user.setPhoneNumber(userRequestDto.getPhoneNumber());
        user.setUserStatus(Status.ACTIVE);
        user.setRole(role);


        User savedUser = userRepo.save(user);



        return savedUser.convertUserResponse(savedUser);
    }



    @Override
    public UserResponseDto getUserDetails(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new UserNotFoundException
                        ( " user not found with id "
                                +userId+ " Enter a Valid Id "));

        return user.convertUserResponse(user);
    }



    @Override
    public List<UserResponseDto> getAllUserDetails() {
        List<User> userList = userRepo.findAll();

        List<UserResponseDto> userResponseDtoList = new ArrayList<>();

        for( User user : userList )
        {
            userResponseDtoList.add(user.convertUserResponse(user));
        }

        return userResponseDtoList;
    }

    @Override
    public UserResponseDto updateTheUser(Long userId, UserRequestDto userRequestDto) {
        User user = userRepo.findById(userId).orElseThrow(()->
                new UserNotFoundException("User Not found exception "));

        user.setEmail(
                userRequestDto.getEmail());

        user.setLastName(
                userRequestDto.getLastName());

        user.setFirstName(
                userRequestDto.getFirstName());

        user.setPassword(
                userRequestDto.getPassword());

        user.setPhoneNumber(
                userRequestDto.getPhoneNumber());

        User savedUser = userRepo.save(user);

        return user.convertUserResponse(savedUser);
    }

    @Override
    public String deleteUserDetails(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(" User not Found !! "));

        user.setUserStatus(Status.IN_ACTIVE);
        userRepo.delete(user);
        return " User Deleted successfully ...............!!!!";
    }

}
