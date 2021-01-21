package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.MovieImage;

import java.util.List;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

    @Query("select mi.imgName, mi.path, mi.uuid from MovieImage mi where mi.movie.mno = :mno")
    List<Object> getMovieImageByMovie(Long mno);

    @Modifying
    @Query("delete from MovieImage mi where mi.movie.mno = :mno")
    void deleteByMovie(Long mno);

}
