package com.singleton.liker.service;

public interface LikeService {

    void like(String playerId);

    long getLikes(String playerId);
}
