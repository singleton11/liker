package com.singleton.liker.service;

import com.singleton.liker.repository.CounterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final CounterRepository counterRepository;

    @Override
    public void like(String playerId) {
        counterRepository.like(playerId);
    }

    @Override
    public long getLikes(String playerId) {
        return counterRepository.get(playerId).getLikes();
    }
}
