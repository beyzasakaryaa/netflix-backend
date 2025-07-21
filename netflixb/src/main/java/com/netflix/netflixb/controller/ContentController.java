package com.netflix.netflixb.controller;

import com.netflix.netflixb.entity.Content;
import com.netflix.netflixb.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentController {

    @Autowired
    private ContentService contentService;

    // Admin sadece içerik ekleyebilir
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Content> addContent(@RequestBody Content content) {
        Content saved = contentService.saveContent(content);
        return ResponseEntity.ok(saved);
    }

    // Tüm kullanıcılar içerikleri görebilir
    @GetMapping
    public ResponseEntity<List<Content>> getAllContents() {
        List<Content> list = contentService.getAllContents();
        return ResponseEntity.ok(list);
    }

    // İçerik detayını getirmek
    @GetMapping("/{id}")
    public ResponseEntity<Content> getContent(@PathVariable Long id) {
        return contentService.getContentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Admin içerik güncelleyebilir
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Content> updateContent(@PathVariable Long id, @RequestBody Content content) {
        try {
            Content updated = contentService.updateContent(id, content);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Admin içerik silebilir
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.noContent().build();
    }
}
