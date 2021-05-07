package com.panteapaliuc.movie.reviews.repository;

import com.panteapaliuc.movie.reviews.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Long> {
    void deleteTagByTagId(Long tagId);

    Optional<Tag> findTagByTagId(Long tagId);
}
