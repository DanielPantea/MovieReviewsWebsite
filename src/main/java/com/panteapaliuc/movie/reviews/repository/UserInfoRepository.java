package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
