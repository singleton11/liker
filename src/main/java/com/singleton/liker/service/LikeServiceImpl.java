package com.singleton.liker.service;

import com.singleton.liker.repository.CounterLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final CounterLikeRepository counterLikeRepository;

    @Override
    public void like(String playerId) {
        counterLikeRepository.like(playerId);
    }

    @Override
    public long getLikes(String playerId) {
        return counterLikeRepository.get(playerId).getLikes();
    }
}
