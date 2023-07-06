package com.relex.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @Column(length = 20)
    private String firstName;

    @Email(message = "Некорректный формат эл.почты")
    private String email;

    private String lastName;

    private String username;

    private String password;

    @Transient
    private List<Rating> userRatings = new ArrayList<>();
}
