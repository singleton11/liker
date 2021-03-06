package com.singleton.liker.service;

import static org.junit.Assert.assertEquals;

import com.singleton.liker.domain.Counter;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.singleton.liker.helper.repository.TestCounterRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LikeServiceImplTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private TestCounterRepository testCounterRepository;

    /**
     * Since transactions for mongo db is not supported by @DataJpaTest, we have to clear
     * collection each time we run the test case
     */
    @After
    public void tearDown() {
        testCounterRepository.deleteAll();
    }

    @Test
    public void getLikesIfUserWasNotExist() {
        assertEquals(0, likeService.getLikes("Nagibator666"));
    }

    @Test
    public void getLikesIfUserExists() {
        testCounterRepository.save(Counter.builder().playerId("Ivan").likes(3).build());
        assertEquals(3, likeService.getLikes("Ivan"));
    }

    @Test
    public void likeIfUserWasNotExist() {
        likeService.like("Newbie");
        assertEquals(1, likeService.getLikes("Newbie"));
    }

    @Test
    public void likeTwice() {
        IntStream.range(0, 2).forEach(value -> likeService.like("TwiceLikedPlayer"));
        assertEquals(2, likeService.getLikes("TwiceLikedPlayer"));
    }
}