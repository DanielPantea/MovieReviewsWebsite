package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, Long> {

}
