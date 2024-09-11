package com.dxschool.lightme.mission.controller;

import com.dxschool.lightme.caseuser.controller.User;
import com.dxschool.lightme.mission.controller.dto.MissionResponse;
import com.dxschool.lightme.mission.service.MissionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/missions")
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ResponseEntity<List<MissionResponse>> getMissions(@User Long userId) {
        return ResponseEntity.ok(missionService.findAll(userId));
    }

    @PostMapping("/{missionId}")
    public ResponseEntity<Void> acceptMission(@PathVariable Long missionId, @User Long userId) {
        missionService.accept(userId, missionId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{missionId}")
    public ResponseEntity<Void> completeMission(@PathVariable Long missionId, @User Long userId) {
        missionService.complete(userId, missionId);
        return ResponseEntity.noContent().build();
    }
}
