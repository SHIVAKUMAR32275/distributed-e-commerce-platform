package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Notification;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.dtos.NotificationRequestDto;
import com.example.distributed_e_commerence_platform.dtos.NotificationResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.NotificationNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.UserNotFoundException;
import com.example.distributed_e_commerence_platform.repository.NotificationRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationServiceImpl implements INotificationService{

    private UserRepo userRepo ;

    private NotificationRepo notificationRepo;

    public NotificationServiceImpl( UserRepo userRepo , NotificationRepo notificationRepo ){
        this.notificationRepo = notificationRepo;
        this.userRepo = userRepo;
    }



    @Override
    public NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto) {

        User user = userRepo.findById(notificationRequestDto.getUserId())
                .orElseThrow(
                        ()-> new UserNotFoundException(" user not found ")
                );


        Notification notification = new Notification();
        notification.setUser(user);
        notification.setTitle(notificationRequestDto.getTitle());
        notification.setMessage(notificationRequestDto.getMessage());
        notification.setIsRead(true);

        Notification savedNotification = notificationRepo.save(notification);

        return savedNotification.convertToResponse(savedNotification);
    }

    @Override
    public NotificationResponseDto getNotification(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(
                        ()->new NotificationNotFoundException(" Notification not found ")
                );

        return notification.convertToResponse(notification);
    }

    @Override
    public List<NotificationResponseDto> getAllNotifications() {

        List<Notification> notifications = notificationRepo.findAll();


        List<NotificationResponseDto> notificationResponseDtos = new ArrayList<>();

        for( Notification notification : notifications ){
            notificationResponseDtos.add(notification.convertToResponse(notification));
        }

        return notificationResponseDtos;
    }

    @Override
    public NotificationResponseDto updateNotificationDetails(Long notificationId, NotificationRequestDto notificationRequestDto) {

        User user = userRepo.findById(notificationRequestDto.getUserId())
                .orElseThrow(
                        ()-> new UserNotFoundException(" user not found ")
                );

        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(
                        ()->new NotificationNotFoundException(" Notification not found ")
                );

        notification.setUser(user);
        notification.setTitle(notificationRequestDto.getTitle());
        notification.setMessage(notificationRequestDto.getMessage());

        Notification savedNotification = notificationRepo.save(notification);

        return savedNotification.convertToResponse(savedNotification);
    }

    @Override
    public void deleteNotification(Long notificationId) {
        Notification notification = notificationRepo.findById(notificationId)
                .orElseThrow(
                        ()->new NotificationNotFoundException(" Notification not found ")
                );

        notificationRepo.delete(notification);
    }
}
