package com.panteapaliuc.movie.reviews.model;

import com.panteapaliuc.movie.reviews.utility.enUserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserInfo {

    @Id
    private Long userId;

    private String username;

    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    private enUserRole userRole;
}
