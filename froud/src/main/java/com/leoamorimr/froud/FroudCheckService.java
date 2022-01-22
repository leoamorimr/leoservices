package com.leoamorimr.froud;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FroudCheckService {

    private final FroudCheckHistoryRepository froudCheckHistoryRepository;

    public boolean isFroudulentCustomer(Integer customerId) {
        froudCheckHistoryRepository.save(
                FroudCheckHistory.builder()
                        .customerId(customerId)
                        .isFrouster(false)
                        .createdAt(LocalDateTime.now())
                        .build()
        );
        return false;
    }
}
