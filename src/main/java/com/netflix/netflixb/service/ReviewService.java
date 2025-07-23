package com.netflix.netflixb.service;

import com.netflix.netflixb.entity.Review;
import com.netflix.netflixb.dto.ReviewDTO;
import com.netflix.netflixb.repository.ReviewRepository;
import com.netflix.netflixb.repository.ContentRepository;
import com.netflix.netflixb.entity.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ContentRepository contentRepository;

    public void addReview(String email, String title, ReviewDTO dto) {
        // Eğer başlık yanlışsa veya içerik yoksa, burada kontrol edebilirsin
        Content content = contentRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("İçerik bulunamadı!"));

        Review review = new Review();
        review.setEmail(email);
        review.setTitle(content.getTitle()); // Doğrudan gönderilen başlık da olabilir
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());
        reviewRepository.save(review);
    }
}
