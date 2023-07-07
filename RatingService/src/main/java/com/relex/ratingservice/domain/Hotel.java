package com.relex.ratingservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Hotel {

    private UUID hotelId;
    private String name;
    private String location;
    private String description;

}
