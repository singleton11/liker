package com.singleton.liker.service;

import com.singleton.liker.domain.Counter;
import com.singleton.liker.repository.CounterLikeRepository;
import com.singleton.liker.repository.CounterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final CounterRepository counterRepository;
    private final CounterLikeRepository counterLikeRepository;

    @Override
    public void like(String playerId) {
        if (counterRepository.findById(playerId).isPresent()) {
            counterLikeRepository.like(playerId);
        } else {
            counterRepository.save(Counter.builder().playerId(playerId).likes(1).build());
        }
    }

    @Override
    public long getLikes(String playerId) {
        return counterRepository.findById(playerId)
                                .map(Counter::getLikes)
                                .orElseGet(() -> counterRepository.save(Counter.builder()
                                                                               .playerId(playerId)
                                                                               .build())
                                                                  .getLikes());

    }
}
