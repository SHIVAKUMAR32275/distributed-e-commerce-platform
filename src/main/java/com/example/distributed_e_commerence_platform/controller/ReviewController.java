package com.example.distributed_e_commerence_platform.controller;

import com.example.distributed_e_commerence_platform.dtos.ReviewRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ReviewResponseDto;
import com.example.distributed_e_commerence_platform.service.IReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    private final IReviewService iReviewService;

    public ReviewController(
            IReviewService iReviewService) {

        this.iReviewService =
                iReviewService;
    }

    // CUSTOMER creates review
    @PostMapping("/review")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReviewResponseDto>
    createReview(
            @RequestBody
            ReviewRequestDto reviewRequestDto) {

        ReviewResponseDto responseDto =
                iReviewService
                        .createAReview(
                                reviewRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.CREATED);
    }

    // CUSTOMER and ADMIN can view review
    @GetMapping("/review/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ReviewResponseDto>
    getReviewRecord(
            @PathVariable("id")
            Long reviewId) {

        ReviewResponseDto responseDto =
                iReviewService
                        .getReview(reviewId);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    // CUSTOMER and ADMIN can view reviews
    @GetMapping("/review")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ReviewResponseDto>>
    getAllReviewRecords() {

        List<ReviewResponseDto>
                responseDtoList =
                iReviewService
                        .getAllReviews();

        return new ResponseEntity<>(
                responseDtoList,
                HttpStatus.OK);
    }

    // CUSTOMER updates own review
    @PutMapping("/review/{id}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ReviewResponseDto>
    updateReviewRecord(
            @PathVariable("id")
            Long reviewId,

            @RequestBody
            ReviewRequestDto reviewRequestDto) {

        ReviewResponseDto responseDto =
                iReviewService
                        .updateReview(
                                reviewId,
                                reviewRequestDto);

        return new ResponseEntity<>(
                responseDto,
                HttpStatus.OK);
    }

    // CUSTOMER deletes own review OR ADMIN deletes any review
    @DeleteMapping("/review/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    public ResponseEntity<Void>
    deleteReviewRecord(
            @PathVariable("id")
            Long reviewId) {

        iReviewService.deleteReview(reviewId);

        return ResponseEntity
                .noContent()
                .build();
    }
}