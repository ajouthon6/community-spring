package com.ajousw.spring.web.controller;

import com.ajousw.spring.domain.tag.TagService;
import com.ajousw.spring.web.controller.dto.tag.TagDto;
import com.ajousw.spring.web.controller.json.ApiResponseJson;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping("/tags/addOrUpdate")
    public ApiResponseJson addOrUpdateTags(@Valid @RequestBody TagDto tagDto) {
        tagService.addOrUpdateTags(tagDto);

        return new ApiResponseJson(HttpStatus.OK, "Tag Processing Successfully");
    }

    @GetMapping("/tags/all")
    public ApiResponseJson getAllTagsSortedByRefCount() {
        TagDto tagDto = tagService.getAllTagsSortedByRefCount();
        return new ApiResponseJson(HttpStatus.OK, tagDto);
    }
}
