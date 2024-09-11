package com.dxschool.lightme.memory.controller.dto;

import java.time.LocalDate;

public record MemoryRequest(
        String content,
        String title,
        LocalDate memoryDate
) {
}
