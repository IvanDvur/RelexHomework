package com.relex.userservice.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rating {

    private UUID ratingId;

    private UUID userId;

    private UUID hotelId;

    @Min(0L)
    @Max(10L)
    private int rating;

    private Hotel hotel;

    private String feedback;
}
