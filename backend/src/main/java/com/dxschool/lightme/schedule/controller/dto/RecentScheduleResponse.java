package com.dxschool.lightme.schedule.controller.dto;

public record RecentScheduleResponse(
        int dDay,
        String content
) {
    public static RecentScheduleResponse of(int dDay, String content) {
        return new RecentScheduleResponse(dDay, content);
    }
}
