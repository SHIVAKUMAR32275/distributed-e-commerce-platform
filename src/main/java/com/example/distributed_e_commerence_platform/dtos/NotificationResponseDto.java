package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationResponseDto {


    private Long notificationId;

    private String title;

    private String message;

    private boolean isRead;

    private Long userId;

    private String userName;

}
