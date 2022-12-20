package com.example.androidpractice.custom_view.watch_view.viewmodel

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.example.androidpractice.services.utils.TimeUtils
import com.example.androidpractice.services.utils.TimeUtils.getCurrentHour
import com.example.androidpractice.services.utils.TimeUtils.getCurrentMilliSecond
import com.example.androidpractice.services.utils.TimeUtils.getCurrentMinute
import com.example.androidpractice.services.utils.TimeUtils.getCurrentSecond
import java.util.*
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds


class WatchCustomView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    var smoothAnimation: Boolean = true

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l, t, r, b)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        val paint = Paint()

        val viewCenterX = (x + measuredWidth) / 2
        val viewCenterY = (y + measuredHeight) / 2
        val watchRadius = (x + measuredWidth) / 2.5F

        drawCorps(canvas, paint, viewCenterX, viewCenterY)
        drawMarks(canvas, paint, viewCenterX, viewCenterY, watchRadius)
        drawArrows(canvas, paint, viewCenterX, viewCenterY, watchRadius)
    }

    private fun drawMarks(canvas: Canvas?, paint: Paint, centerX: Float, centerY: Float, watchRadius: Float) {
        canvas?.save()
        val degreesStep = 360 / 24F

        for (i in 0..24) {
            if (i % 2 == 0) {
                paint.strokeWidth = 20F
            } else {
                paint.strokeWidth = 10F
            }
            canvas?.drawLine(centerX + watchRadius - 50, centerY, centerX + watchRadius, centerY, paint)
            canvas?.rotate(degreesStep, centerX, centerY)
        }

        canvas?.restore()
    }

    private fun drawArrows(canvas: Canvas?, paint: Paint, centerX: Float, centerY: Float, watchRadius: Float) {
        val secondArrowAngle = if (smoothAnimation) {
            getCurrentMilliSecond() * 6F / 1000F + getCurrentSecond() * 6F
        } else {
            getCurrentSecond() * 6F
        }

        val minuteArrowAngle = if (smoothAnimation) {
            getCurrentMinute() * 6F + getCurrentSecond() * 6F / 60F
        } else {
            getCurrentMinute() * 6F
        }

        val hourArrowAngle = if (smoothAnimation) {
            getCurrentHour() * 6F + getCurrentMinute() * 6F / 60F
        } else {
            getCurrentHour() * 6F
        }

        canvas?.save()
        canvas?.rotate(secondArrowAngle, centerX, centerY)
        paint.strokeWidth = 10F
        canvas?.drawLine(centerX, centerY, centerX, centerY - watchRadius + 50, paint)
        canvas?.restore()


        canvas?.save()
        canvas?.rotate(minuteArrowAngle, centerX, centerY)
        paint.strokeWidth = 20F
        canvas?.drawLine(centerX, centerY, centerX, centerY - watchRadius + 100, paint)
        canvas?.restore()


        canvas?.save()
        canvas?.rotate(hourArrowAngle, centerX, centerY)
        paint.strokeWidth = 30F
        canvas?.drawLine(centerX, centerY, centerX, centerY - watchRadius + 300, paint)
        canvas?.restore()
    }

    private fun drawCorps(canvas: Canvas?, paint: Paint, centerX: Float, centerY: Float) {
        val watchCenterRadius = 20F
        val watchInnerRadius = centerX * 2 / 2.5F
        val watchOuterRadius = centerX * 2 / 2.25F

        paint.color = Color.BLACK
        canvas?.drawCircle(centerX, centerY, watchOuterRadius, paint)

        paint.color = Color.WHITE
        canvas?.drawCircle(centerX, centerY, watchInnerRadius, paint)

        paint.color = Color.BLACK
        canvas?.drawCircle(centerX, centerY, watchCenterRadius, paint)
    }
}