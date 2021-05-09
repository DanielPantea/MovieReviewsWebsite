package com.panteapaliuc.movie.reviews.service;

import com.panteapaliuc.movie.reviews.model.Tag;
import com.panteapaliuc.movie.reviews.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag addTag(Tag tag)
    {
        return tagRepository.save(tag);
    }

    public Boolean checkTagExists(Tag tag)
    {
        return tagRepository.findTagByTagKey(tag.getTagKey()).isPresent();
    }
}
