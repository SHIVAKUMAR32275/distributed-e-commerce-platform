package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.NotificationRequestDto;
import com.example.distributed_e_commerence_platform.dtos.NotificationResponseDto;
import com.example.distributed_e_commerence_platform.service.INotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/notification")
@RestController
public class NotificationController {

    private final INotificationService iNotificationService;

    public NotificationController(
            INotificationService iNotificationService) {

        this.iNotificationService =
                iNotificationService;
    }

    // ADMIN creates notifications
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NotificationResponseDto>
    createNotification(
            @RequestBody
            NotificationRequestDto notificationRequestDto) {

        NotificationResponseDto responseDto =
                iNotificationService
                        .createNotification(
                                notificationRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED);
    }

    // CUSTOMER and ADMIN can view notifications
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<NotificationResponseDto>
    getNotificationDetails(
            @PathVariable("id")
            Long notificationId) {

        NotificationResponseDto responseDto =
                iNotificationService
                        .getNotification(
                                notificationId);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    // ADMIN only
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<NotificationResponseDto>>
    getAllNotificationDetails() {

        List<NotificationResponseDto>
                responseDtoList =
                iNotificationService
                        .getAllNotifications();

        return new ResponseEntity<>(
                responseDtoList,
                HttpStatus.OK);
    }

    // ADMIN only
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<NotificationResponseDto>
    updateNotificationDetails(
            @PathVariable("id")
            Long notificationId,

            @RequestBody
            NotificationRequestDto notificationRequestDto) {

        NotificationResponseDto responseDto =
                iNotificationService
                        .updateNotificationDetails(
                                notificationId,
                                notificationRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    // ADMIN only
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void>
    deleteNotification(
            @PathVariable("id")
            Long notificationId) {

        iNotificationService
                .deleteNotification(
                        notificationId);

        return ResponseEntity
                .noContent()
                .build();
    }
}