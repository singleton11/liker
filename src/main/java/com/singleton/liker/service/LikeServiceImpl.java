package com.singleton.liker.service;

import com.singleton.liker.domain.Counter;
import com.singleton.liker.repository.CounterLikeRepository;
import com.singleton.liker.repository.CounterRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final CounterRepository counterRepository;
    private final CounterLikeRepository counterLikeRepository;

    @Override
    public void like(String playerId) {
        counterLikeRepository.like(playerId);
    }

    @Override
    public long getLikes(String playerId) {
        Optional<Counter> playerLikes = counterRepository.findById(playerId);
        return playerLikes.map(Counter::getLikes)
                          .orElseGet(() -> counterRepository.save(Counter.builder()
                                                                         .playerId(playerId)
                                                                         .build()).getLikes());

    }
}
