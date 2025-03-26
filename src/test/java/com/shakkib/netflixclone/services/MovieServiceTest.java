package com.shakkib.netflixclone.services;

import com.shakkib.netflixclone.dtoes.MovieCreateDTO;
import com.shakkib.netflixclone.dtoes.MovieListDTO;
import com.shakkib.netflixclone.entity.Genre;
import com.shakkib.netflixclone.entity.Movie;
import com.shakkib.netflixclone.repository.GenreRepository;
import com.shakkib.netflixclone.repository.MovieRepository;
import com.shakkib.netflixclone.services.impl.MovieServiceImpl;
import org.checkerframework.checker.interning.qual.InternedDistinct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    // @InjectMocks : MovieServiceImpl 객체에 Mock 객체들을 자동으로 주입합니다.
    @InjectMocks
    private MovieServiceImpl movieService;

    // @Mock : 의존성인 MovieRepository와 GenreRepository를 가짜로 생성하여 테스트에 사용합니다.
    @Mock
    private MovieRepository movieRepository;

    @Mock
    private GenreRepository genreRepository;

    // @BeforeEach : 각 테스트 메소드가 실행되기 전에 먼저 호출되어 초기화 작업을 수행
    @BeforeEach
    public void setUp() {
//        MockitoAnnotations.openMocks(this) : @Mock, @InjectMocks 어노테이션을 사용하여
//        Mock 객체들을 초기화합니다.
        MockitoAnnotations.openMocks(this);
    }

    // saveMovie() 메소드에 대한 텍스트
    @Test
    @DisplayName("영화 저장")
    public void testSaveMovie() {
        // given : 테스트를 위한 가짜 Movie 객체 생성 및 동작 정의
        Movie movie = new Movie();
        movie.setTitle("Test Movie");

        // movieRepository의 save 메소드 호출 시 가짜 Movie 객체를 반환하도록 설정
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        // when : 테스트 대상 메소드 호출
        Movie savedMovie = movieService.saveMovie(movie);

        // then : 결과 검증 - 저장된 Movie 객체의 제목이 동일한지 확인
        assertThat(savedMovie.getTitle()).isEqualTo("Test Movie");
        //save 메소드 호출 횟수 검증
        verify(movieRepository, times(1)).save(movie);
    }

    // convertMovieDTOToMovieEntity() 메소드에 대한 테스트
    @Test
    @DisplayName("영화컨버터")
    public void testConvertMovieDTOToMovieEntity() {
        // given: 가짜 Genre 객체와 MovieCreateDTO 객체 생성
        Genre genre = new Genre();
        genre.setId(1L);
        genre.setGenre("Action");

        MovieCreateDTO movieCreateDTO = new MovieCreateDTO();
        movieCreateDTO.setGenreId(1L);
        movieCreateDTO.setTitle("Converted Movie");

        // genreRepository의 findById 메소드 호출 시 가짜 Genre 객체를 반환하도록 설정
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));

        // when : 테스트 대상 메소드 호출
        Movie movie = movieService.convertMovieDTOToMovieEntity(movieCreateDTO);

        //then: 변환된 Movie 객체의 제목과 장르가 기대한 대로 설정되었는지 확인
        assertThat(movie.getTitle()).isEqualTo("Converted Movie");

        // 장르의 ID와 이름이 잘 설정 되었는지 검증
        assertThat(movie.getGenre()).isNotNull(); //Genre가 null이 아닌지 확인
        assertThat(movie.getGenre().getId()).isEqualTo(1L); // Genre ID 확인
        assertThat(movie.getGenre().getGenre()).isEqualTo("Action"); // Genre 이름 확인
        // findById 메소드 호출 횟수 검증
        verify(genreRepository, times(1)).findById(1L);
    }
}
