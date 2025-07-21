package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.ContentRequestDTO;
import com.netflix.netflixb.dto.ContentResponseDTO;
import com.netflix.netflixb.entity.Content;
import com.netflix.netflixb.repository.ContentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ContentResponseDTO saveContent(ContentRequestDTO contentRequestDTO) {
        Content content = modelMapper.map(contentRequestDTO, Content.class);
        Content saved = contentRepository.save(content);
        return modelMapper.map(saved, ContentResponseDTO.class);
    }

    @Override
    public List<ContentResponseDTO> getAllContents() {
        List<Content> contents = contentRepository.findAll();
        return contents.stream()
                .map(content -> modelMapper.map(content, ContentResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ContentResponseDTO getContentById(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));
        return modelMapper.map(content, ContentResponseDTO.class);
    }

    @Override
    public ContentResponseDTO updateContent(Long id, ContentRequestDTO contentRequestDTO) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found with id: " + id));

        // Güncelle
        content.setTitle(contentRequestDTO.getTitle());
        content.setGenre(contentRequestDTO.getGenre());
        content.setReleaseDate(contentRequestDTO.getReleaseDate());
        content.setActors(contentRequestDTO.getActors());
        content.setDirector(contentRequestDTO.getDirector());
        content.setDescription(contentRequestDTO.getDescription());
        content.setImdbRating(contentRequestDTO.getImdbRating());

        Content updated = contentRepository.save(content);
        return modelMapper.map(updated, ContentResponseDTO.class);
    }

    @Override
    public void deleteContent(Long id) {
        contentRepository.deleteById(id);
    }
}
