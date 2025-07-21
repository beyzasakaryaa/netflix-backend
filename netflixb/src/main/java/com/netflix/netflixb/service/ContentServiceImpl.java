package com.netflix.netflixb.service;

import com.netflix.netflixb.entity.Content;
import com.netflix.netflixb.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public Content saveContent(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public List<Content> getAllContents() {
        return contentRepository.findAll();
    }

    @Override
    public Optional<Content> getContentById(Long id) {
        return contentRepository.findById(id);
    }

    @Override
    public Content updateContent(Long id, Content content) {
        Optional<Content> existing = contentRepository.findById(id);
        if (existing.isPresent()) {
            Content c = existing.get();
            c.setTitle(content.getTitle());
            c.setGenre(content.getGenre());
            c.setReleaseDate(content.getReleaseDate());
            c.setActors(content.getActors());
            c.setDirector(content.getDirector());
            c.setDescription(content.getDescription());
            c.setImdbRating(content.getImdbRating());
            return contentRepository.save(c);
        } else {
            throw new RuntimeException("Content not found with id: " + id);
        }
    }

    @Override
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}
