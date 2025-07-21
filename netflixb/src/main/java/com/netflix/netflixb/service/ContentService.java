package com.netflix.netflixb.service;

import com.netflix.netflixb.entity.Content;

import java.util.List;
import java.util.Optional;

public interface ContentService {

    Content saveContent(Content content);

    List<Content> getAllContents();

    Optional<Content> getContentById(Long id);

    Content updateContent(Long id, Content content);

    void deleteContent(Long id);
}
