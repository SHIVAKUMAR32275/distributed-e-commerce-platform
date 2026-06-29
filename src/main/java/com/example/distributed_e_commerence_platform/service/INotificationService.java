package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.NotificationRequestDto;
import com.example.distributed_e_commerence_platform.dtos.NotificationResponseDto;

import java.util.List;

public interface INotificationService {

    NotificationResponseDto createNotification(NotificationRequestDto notificationRequestDto );

    NotificationResponseDto getNotification( Long notificationId );

    List<NotificationResponseDto> getAllNotifications();

    NotificationResponseDto updateNotificationDetails(Long notificationId , NotificationRequestDto notificationRequestDto);

    void deleteNotification(Long notificationId);

}
