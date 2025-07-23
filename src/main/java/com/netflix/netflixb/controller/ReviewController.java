package com.netflix.netflixb.controller;

import com.netflix.netflixb.dto.ReviewDTO;
import com.netflix.netflixb.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Sadece USER yorum bırakabilsin
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/{title}")
    public ResponseEntity<?> addReview(
            @PathVariable String title,
            @RequestBody ReviewDTO dto,
            Principal principal
    ) {
        reviewService.addReview(principal.getName(), title, dto);
        return ResponseEntity.ok("Yorum eklendi!");
    }
}
