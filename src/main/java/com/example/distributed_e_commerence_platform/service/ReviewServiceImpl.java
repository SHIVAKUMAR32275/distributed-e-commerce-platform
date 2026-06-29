package com.example.distributed_e_commerence_platform.service;

import com.example.distributed_e_commerence_platform.Models.Product;
import com.example.distributed_e_commerence_platform.Models.Review;
import com.example.distributed_e_commerence_platform.Models.User;
import com.example.distributed_e_commerence_platform.dtos.ReviewRequestDto;
import com.example.distributed_e_commerence_platform.dtos.ReviewResponseDto;
import com.example.distributed_e_commerence_platform.exceptions.ProductNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.ReviewNotFoundException;
import com.example.distributed_e_commerence_platform.exceptions.UserNotFoundException;
import com.example.distributed_e_commerence_platform.repository.ProductRepo;
import com.example.distributed_e_commerence_platform.repository.ReviewRepo;
import com.example.distributed_e_commerence_platform.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements IReviewService {


    private final UserRepo userRepo;

    private final ProductRepo productRepo;

    private final ReviewRepo reviewRepo;

    public ReviewServiceImpl(UserRepo userRepo , ProductRepo productRepo , ReviewRepo reviewRepo){
        this.productRepo = productRepo;
        this.userRepo = userRepo;
        this.reviewRepo= reviewRepo;
    }

    @Override
    public ReviewResponseDto createAReview(ReviewRequestDto reviewRequestDto) {

        User user = userRepo.findById(reviewRequestDto.getUserId())
                .orElseThrow(
                        ()-> new UserNotFoundException(" User  not Found ")
                );

        Product product = productRepo.findById(reviewRequestDto.getProductId())
                .orElseThrow(
                        ()-> new ProductNotFoundException(" Product Not found ")
                );

        Review review = new Review();
        review.setComment(reviewRequestDto.getComment());
        review.setRating(reviewRequestDto.getRating());
        review.setUser(user);
        review.setProduct(product);

        Review savedReview = reviewRepo.save(review);

        return savedReview.convertToReviewResponse(savedReview);
    }

    @Override
    public ReviewResponseDto getReview(Long reviewId) {

        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(
                        ()-> new ReviewNotFoundException(" review not Found ")
                );


        return review.convertToReviewResponse(review);
    }

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        List<Review> reviews = reviewRepo.findAll();

        List<ReviewResponseDto> responseDtoList = new ArrayList<>();

        for( Review review : reviews ){
            responseDtoList.add(review.convertToReviewResponse(review));
        }

        return responseDtoList;
    }

    @Override
    public ReviewResponseDto updateReview(Long reviewId, ReviewRequestDto reviewRequestDto) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(
                        ()-> new ReviewNotFoundException(" review not found ")
                );

        review.setRating(reviewRequestDto.getRating());
        review.setComment(reviewRequestDto.getComment());

        Review savedReview = reviewRepo.save(review);

        return savedReview.convertToReviewResponse(savedReview);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review review = reviewRepo.findById(reviewId)
                .orElseThrow(
                        ()-> new ReviewNotFoundException(" review not found ")
                );

        reviewRepo.delete(review);
    }
}
