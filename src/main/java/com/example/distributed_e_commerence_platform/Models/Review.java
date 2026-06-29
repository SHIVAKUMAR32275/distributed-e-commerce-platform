package com.example.distributed_e_commerence_platform.Models;

import com.example.distributed_e_commerence_platform.dtos.ReviewResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Review extends BaseModel{

    private int rating;

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;

    public ReviewResponseDto convertToReviewResponse(Review savedReview ){
        ReviewResponseDto responseDto = new ReviewResponseDto();
        responseDto.setReviewId(savedReview.getId());
        responseDto.setRating(savedReview.getRating());
        responseDto.setComment(savedReview.getComment());

        if (savedReview.getProduct() != null) {
            responseDto.setProductId(savedReview.getProduct().getId());
            responseDto.setProductName(savedReview.getProduct().getName());
        }

        if( savedReview.getUser() != null ){
            responseDto.setUserId(savedReview.getUser().getId());
        }

        return responseDto;
    }


}
