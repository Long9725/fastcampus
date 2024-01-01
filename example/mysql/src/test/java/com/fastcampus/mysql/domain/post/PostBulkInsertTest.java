package com.fastcampus.mysql.domain.post;

import com.fastcampus.mysql.utils.PostFixtureFactory;
import com.fastcampus.mysql.domain.post.entity.Post;
import com.fastcampus.mysql.domain.post.repository.PostRepository;
import jakarta.transaction.Transactional;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @DisplayName("123")
    @Test
    public void bulkInsert() {
        EasyRandom easyRandom = PostFixtureFactory.get(
                4L,
                LocalDate.of(1970, 1, 1),
                LocalDate.of(2022, 2, 1)
        );

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();

        List<Post> posts = IntStream.range(0, 10000 * 100)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();

        stopWatch.stop();

        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());

        StopWatch queryStopWatch = new StopWatch();

        queryStopWatch.start();

        postRepository.bulkInsert(posts);

        queryStopWatch.stop();

        System.out.println("DB 저장 시간 : " + queryStopWatch.getTotalTimeSeconds());
    }
}
