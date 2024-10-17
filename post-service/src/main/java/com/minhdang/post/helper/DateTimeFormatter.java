package com.minhdang.post.helper;

import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * This is a Map/Dictionary-based design pattern.
 * Use this when there are many condition.
 * Key is the condition.
 * Value is the Function that expected to happen when meet the condition
 */

@Component
public class DateTimeFormatter {

    Map<Long, Function<Instant, String>> strategyMap = new LinkedHashMap<>();

    public DateTimeFormatter() {
        strategyMap.put(60L, this::formatInSeconds);
        strategyMap.put(3600L, this::formatInMinutes);
        strategyMap.put(86400L, this::formatInHours);
        strategyMap.put(Long.MAX_VALUE, this::formatInDay);
    }

    public String format(Instant instant) {
        long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());

        var strategy = strategyMap.entrySet().stream()
                .filter(longFunctionEntry ->  elapseSeconds < longFunctionEntry.getKey())
                .findFirst().get();

        return strategy.getValue().apply(instant);
    }

    private String formatInSeconds(Instant instant) {
         long elapseSeconds = ChronoUnit.SECONDS.between(instant, Instant.now());
         return String.format("%d seconds ago", elapseSeconds);
     }

    private String formatInMinutes(Instant instant) {
        long elapseMinutes = ChronoUnit.MINUTES.between(instant, Instant.now());
        return String.format("%d minutes ago", elapseMinutes);
    }

    private String formatInHours(Instant instant) {
        long elapseHours = ChronoUnit.HOURS.between(instant, Instant.now());
        return String.format("%d hours ago", elapseHours);
    }

    private String formatInDay(Instant instant) {
        LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
        java.time.format.DateTimeFormatter dateTimeFormatter = java.time.format.DateTimeFormatter.ISO_DATE;
        return localDateTime.format(dateTimeFormatter);
    }
}
