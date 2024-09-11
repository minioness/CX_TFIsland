package com.dxschool.lightme.memory.service;

import com.dxschool.lightme.caseuser.domain.CaseUser;
import com.dxschool.lightme.caseuser.domain.repository.CaseUserRepository;
import com.dxschool.lightme.memory.controller.dto.MemoryRequest;
import com.dxschool.lightme.memory.controller.dto.MemoryResponse;
import com.dxschool.lightme.memory.domain.Memory;
import com.dxschool.lightme.memory.domain.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;
    private final CaseUserRepository caseUserRepository;

    public List<MemoryResponse> findAll(Long userId) {
        return memoryRepository.findByCreatedBy_CaseUserId(userId).stream()
                .map(MemoryResponse::from)
                .toList();
    }

    public void createMemory(Long userId, MemoryRequest request) {
        CaseUser caseUser = caseUserRepository.findById(userId)
                .orElseThrow(NoSuchElementException::new);

        String color = analyzeMemory(request.content());

        Memory memory = Memory.builder()
                .createdBy(caseUser)
                .memoryDate(request.memoryDate())
                .title(request.title())
                .content(request.content())
                .color(color)
                .build();

        memoryRepository.save(memory);
    }

    private String analyzeMemory(String content) {
        //TODO: 텍스트 분석 후 감정을 색상으로 변환하기
        return "FFFFFF";
    }
}
