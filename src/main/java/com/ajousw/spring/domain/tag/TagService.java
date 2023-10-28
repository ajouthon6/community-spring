package com.ajousw.spring.domain.tag;

import com.ajousw.spring.web.controller.dto.tag.TagDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class TagService {
    private final TagJpaRepository tagJpaRepository;

    public void addOrUpdateTags(TagDto tagDto) {
        for (String tagName : tagDto.getTags()) {
            Optional<Tag> existingTag = tagJpaRepository.findByTagName(tagName);

            if (existingTag.isPresent()) {
                existingTag.get().recordTagAccess();
            } else {
                Tag newTag = new Tag(tagName);
                tagJpaRepository.save(newTag);
            }
        }
    }

    @Transactional(readOnly = true)
    public TagDto getAllTagsSortedByRefCount() {
        List<Tag> tags = tagJpaRepository.findAllByOrderByTagRefCountDesc();
        List<String> tagNames = tags.stream().map(Tag::getTagName).collect(Collectors.toList());
        return new TagDto(tagNames);
    }

}
