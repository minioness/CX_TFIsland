package com.dxschool.lightme.memory.controller.dto;

import com.dxschool.lightme.memory.domain.Memory;

public record MemoryResponse(
        Long memoryId,
        String createdAt,
        String content,
        String title,
        String color,
        String memoryDate
) {
    public static MemoryResponse from(Memory memory) {
        return new MemoryResponse(
                memory.getMemoryId(),
                memory.getCreatedAt().toString(),
                memory.getContent(),
                memory.getTitle(),
                memory.getColor(),
                memory.getMemoryDate().toString()
        );
    }
}
