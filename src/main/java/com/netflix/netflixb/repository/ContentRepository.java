package com.netflix.netflixb.repository;

import com.netflix.netflixb.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    // İstersen burada özel sorgular yazılabilir, şimdilik JpaRepository'nin tüm metodları yeterli.
    Optional<Content> findByTitle(String title);

}
