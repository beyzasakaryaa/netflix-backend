package com.netflix.netflixb.controller;

import com.netflix.netflixb.dto.ContentRequestDTO;
import com.netflix.netflixb.dto.ContentResponseDTO;
import com.netflix.netflixb.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    // İçerik ekleme (sadece ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ContentResponseDTO> addContent(
            @Valid @RequestBody ContentRequestDTO contentRequestDTO) {
        ContentResponseDTO saved = contentService.saveContent(contentRequestDTO);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // Tüm içerikleri getir (sadece USER)
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<ContentResponseDTO>> getAllContents() {
        return ResponseEntity.ok(contentService.getAllContents());
    }

    // Belirli içerik detayını getir (herkes erişebilir)
    @GetMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> getContentById(@PathVariable Long id) {
        ContentResponseDTO content = contentService.getContentById(id);
        return ResponseEntity.ok(content);
    }

    // İçerik güncelle (sadece ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ContentResponseDTO> updateContent(
            @PathVariable Long id,
            @Valid @RequestBody ContentRequestDTO contentRequestDTO) {
        ContentResponseDTO updated = contentService.updateContent(id, contentRequestDTO);
        return ResponseEntity.ok(updated);
    }

    // İçerik silme (sadece ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }

    // Filtreli içerik arama (herkes erişebilir)
    @GetMapping("/filter")
    public ResponseEntity<List<ContentResponseDTO>> filterContents(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) Double minImdb,
            @RequestParam(required = false) Double maxImdb
    ) {
        return ResponseEntity.ok(
                contentService.filterContents(title, genre, year, minImdb, maxImdb)
        );
    }

    // Basit global hata yakalayıcı
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
