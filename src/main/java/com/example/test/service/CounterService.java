package com.example.test.service;

import com.example.test.model.Counter;
import com.example.test.repository.CounterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CounterService {
    private final CounterRepository counterRepository;
    public Counter addCount(Counter value) {
        return counterRepository.save(value);
    }

    public Counter getLast() {
        var s = counterRepository.findFirstByOrderByIdDesc()
                .orElse(new Counter(0L));
        return s;
    }
}
