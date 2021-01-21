package org.zerock.mreview.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MovieServiceTests {

    @Autowired
    private MovieService movieService;

    @Test
    public void testRemove(){
        Long mno = 128L;
        movieService.removeWithReplies(mno);
    }
}
