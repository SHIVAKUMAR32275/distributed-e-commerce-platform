package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.Models.constants.NotificationType;
import com.example.distributed_e_commerence_platform.dtos.NotificationResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Notification extends BaseModel {

    private String message;

    private Boolean isRead;

    private String title;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private LocalDateTime sentAt;

    @ManyToOne
    private User user;


    public NotificationResponseDto convertToResponse( Notification savedNotification ){

        NotificationResponseDto responseDto = new NotificationResponseDto();

        responseDto.setNotificationId(savedNotification.getId());
        responseDto.setRead(true);
        responseDto.setMessage(savedNotification.getMessage());
        responseDto.setTitle(savedNotification.getTitle());

        if( savedNotification.getUser() != null ){
            responseDto.setUserId(savedNotification.getUser().getId());
            responseDto.setUserName(savedNotification.getUser().getFirstName());
        }

        return responseDto;

    }


}