package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.ContentRequestDTO;
import com.netflix.netflixb.dto.ContentResponseDTO;
import com.netflix.netflixb.entity.Content;
import com.netflix.netflixb.repository.ContentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final ModelMapper modelMapper;

    // Constructor injection, field injection yerine önerilir
    public ContentServiceImpl(ContentRepository contentRepository, ModelMapper modelMapper) {
        this.contentRepository = contentRepository;
        this.modelMapper = modelMapper;
    }

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
        // Eğer silme işleminde id'nin varlığını kontrol etmek istersen:
        /*
        if (!contentRepository.existsById(id)) {
            throw new RuntimeException("Content not found with id: " + id);
        }
        */
        contentRepository.deleteById(id);
    }
}
