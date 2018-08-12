package com.singleton.liker.repository;

import com.singleton.liker.domain.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, String> {

}
