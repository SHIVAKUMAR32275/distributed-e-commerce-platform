package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.dtos.ReviewRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ReviewResponseDto;

import java.util.List;

public interface IReviewService {


    ReviewResponseDto createAReview(ReviewRequestDto reviewRequestDto );

    ReviewResponseDto getReview( Long reviewId );

    List<ReviewResponseDto> getAllReviews();

    ReviewResponseDto updateReview(Long reviewId , ReviewRequestDto reviewRequestDto);

    void deleteReview(Long reviewId);

}
