package com.example.distributed_e_commerence_platform.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDto {

    private String title;

    private String message;

    private boolean isRead;

    private Long userId;

}
