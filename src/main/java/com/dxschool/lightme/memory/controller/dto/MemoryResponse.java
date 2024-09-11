package com.dxschool.lightme.memory.controller.dto;

import com.dxschool.lightme.memory.domain.Memory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemoryResponse(
        Long memoryId,
        LocalDateTime createdAt,
        String content,
        String title,
        String color,
        LocalDate memoryDate
) {
    public static MemoryResponse from(Memory memory) {
        return new MemoryResponse(
                memory.getMemoryId(),
                memory.getCreatedAt(),
                memory.getContent(),
                memory.getTitle(),
                memory.getColor(),
                memory.getMemoryDate()
        );
    }
}
