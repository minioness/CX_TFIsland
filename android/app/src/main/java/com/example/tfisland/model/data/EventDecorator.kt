package com.example.tfisland.model.data

import android.graphics.Color
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class EventDecorator(private val dates: Set<CalendarDay>) : DayViewDecorator {

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return dates.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        Log.d("", "")
        // 원하는 데코레이션을 적용 (예: 날짜 배경 색상 변경)
        view.addSpan(DotSpan(15f, Color.RED)) // 예시로 빨간색 점 추가
    }
}
