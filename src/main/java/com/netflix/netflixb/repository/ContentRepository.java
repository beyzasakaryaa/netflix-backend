package com.netflix.netflixb.repository;

import com.netflix.netflixb.entity.Content;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {

    Optional<Content> findByTitle(String title);

    @Query(value = "SELECT * FROM contents c " +
            "WHERE (:title IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:genre IS NULL OR c.genre = :genre) " +
            "AND (:year IS NULL OR EXTRACT(YEAR FROM c.release_date) = :year) " +
            "AND (:minImdb IS NULL OR c.imdb_rating >= :minImdb) " +
            "AND (:maxImdb IS NULL OR c.imdb_rating <= :maxImdb)",
            nativeQuery = true)
    List<Content> findByFilters(
            @Param("title") String title,
            @Param("genre") String genre,
            @Param("year") Integer year,
            @Param("minImdb") Double minImdb,
            @Param("maxImdb") Double maxImdb
    );
}
