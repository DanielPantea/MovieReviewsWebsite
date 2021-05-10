package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {

    Optional<Tag> findTagByTagKey(String tagKey);
}
