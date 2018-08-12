package com.singleton.liker.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Counter {

    private String playerId;
    private long likes;
}
