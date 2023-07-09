package com.relex.userservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
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
