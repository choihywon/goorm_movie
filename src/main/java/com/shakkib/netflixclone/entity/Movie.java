package com.shakkib.netflixclone.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie")
@NoArgsConstructor
@Getter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "movie_id", unique = true, nullable = false)
    private Long movieId;

    private String title;
    private String originalTitle;
    private String posterPath;
    private boolean adult;

    @Column(length = 1000)
    private String overview;
    private LocalDate releaseDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @Column(nullable = false)
    private boolean isUse = true;


    public Movie(Long id, Long movieId, String title, String originalTitle, String posterPath,
                 boolean adult, String overview, LocalDate releaseDate, Genre genre,
                 LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.movieId = movieId;
        this.title = title;
        this.originalTitle = originalTitle;
        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isUse = true;
    }


    public void deactivate() {
        this.isUse = false;
    }

    public void activate() {
        this.isUse = true;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    //Test코드 작성
    public void setTitle(String title) {
        this.title = title;
    } 

}
