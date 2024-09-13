import android.graphics.Canvas
import android.graphics.Paint
import android.text.style.LineBackgroundSpan

class CustomCircleSpan(private val color: Int) : LineBackgroundSpan {
    override fun drawBackground(
        canvas: Canvas, paint: Paint, left: Int, right: Int,
        top: Int, baseline: Int, bottom: Int, charSequence: CharSequence,
        start: Int, end: Int, lineNumber: Int
    ) {
        val oldColor = paint.color
        paint.color = color

        // 원의 위치와 크기를 설정
        val radius = (bottom - top) / 1f
        val cx = (left + right) / 2f
        val cy = (top + bottom) / 2f

        // 날짜 배경으로 원을 그린다
        canvas.drawCircle(cx, cy, radius, paint)

        paint.color = oldColor
    }
}
