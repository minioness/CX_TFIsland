package com.dxschool.lightme.schedule.domain;

import java.time.LocalDateTime;

public interface Schedule {
    LocalDateTime scheduledAt();
    String title();
}
