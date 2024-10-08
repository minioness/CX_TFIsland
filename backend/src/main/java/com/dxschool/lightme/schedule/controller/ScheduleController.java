package com.dxschool.lightme.schedule.controller;

import com.dxschool.lightme.schedule.controller.dto.RecentScheduleResponse;
import com.dxschool.lightme.schedule.controller.dto.ScheduleResponse;
import com.dxschool.lightme.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @GetMapping
    public ResponseEntity<ScheduleResponse> getSchedules(@SessionAttribute(name = "userId") Long userId) {
        return ResponseEntity.ok(scheduleService.findAll(userId));
    }

    @GetMapping("/recent")
    public ResponseEntity<RecentScheduleResponse> getRecentSchedule(@SessionAttribute(name = "userId") Long userId) {
        return ResponseEntity.ok(scheduleService.findRecent(userId));
    }
}
