package com.mihenze.mscurse.deliveryservice.util;

import com.mihenze.mscurse.deliveryservice.repository.ShipmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class TrackingNumberGenerator {
    private final Random random = new Random();
    private final ShipmentRepository shipmentRepository;

    private char randomLetter() {
        return (char) ('A' + random.nextInt(26));
    }

    private char randomNumber() {
        return (char) ('0' + random.nextInt(9));
    }

    /**
     * Генерация последовательности вида LLL-dddd
     */
    private String generateKey() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(randomLetter());
        }
        sb.append('-');
        for (int i = 0; i < 5; i++) {
            sb.append(randomNumber());
        }
        return sb.toString();
    }

    public String generate() {
        String trackingNumber = generateKey();
        while (shipmentRepository.existsByTrackingNumber(trackingNumber)) {
            trackingNumber = generateKey();
        }
        return trackingNumber;
    }
}
