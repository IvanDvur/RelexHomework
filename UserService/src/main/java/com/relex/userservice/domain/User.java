package com.relex.userservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty
    private String firstName;

    @Email(message = "Некорректный формат эл.почты")
    @NotEmpty
    private String email;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String username;

    @Transient
    private List<Rating> userRatings = new ArrayList<>();
}
