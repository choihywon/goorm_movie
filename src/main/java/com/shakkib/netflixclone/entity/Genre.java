package com.shakkib.netflixclone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genre")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String genre;

    public Genre(String genre) {
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }


    //Test코드 작성
    public void setGenre(String genre) {
        this.genre = genre;
    }
}