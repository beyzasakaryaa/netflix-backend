package com.netflix.netflixb.service;

import com.netflix.netflixb.dto.ContentRequestDTO;
import com.netflix.netflixb.dto.ContentResponseDTO;

import java.util.List;

public interface ContentService {

    ContentResponseDTO saveContent(ContentRequestDTO contentRequestDTO);

    List<ContentResponseDTO> getAllContents();

    ContentResponseDTO getContentById(Long id);

    ContentResponseDTO updateContent(Long id, ContentRequestDTO contentRequestDTO);

    void deleteContent(Long id);

    // Yeni eklenen metot: Arama ve filtreleme
    List<ContentResponseDTO> filterContents(
            String title,
            String genre,
            Integer year,
            Double minImdb,
            Double maxImdb
    );
}
