package com.dxschool.lightme.memory.controller;

import com.dxschool.lightme.caseuser.controller.User;
import com.dxschool.lightme.memory.controller.dto.MemoryRequest;
import com.dxschool.lightme.memory.controller.dto.MemoryResponse;
import com.dxschool.lightme.memory.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/memories")
public class MemoryController {

    private final MemoryService memoryService;

    @GetMapping
    public ResponseEntity<List<MemoryResponse>> getMemories(@SessionAttribute(name = "userId") Long userId) {
        return ResponseEntity.ok(memoryService.findAll(userId));
    }

    @PostMapping
    public ResponseEntity<Void> createMemory(@RequestBody MemoryRequest memoryRequest, @SessionAttribute(name = "userId") Long userId) {
        memoryService.createMemory(userId, memoryRequest);
        return ResponseEntity.noContent().build();
    }
}
