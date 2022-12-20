package com.example.androidpractice.vector_views.businesslogic

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class PathView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val paint = Paint()
        paint.color = Color.BLACK
        paint.strokeWidth = 10f

        val path = Path()
        path.moveTo(width / 2f, 0f)
        path.lineTo(width / 4 * 1f, height.toFloat())
        path.lineTo(width / 2f, height / 2f)
        path.lineTo(width / 4 * 3f, height.toFloat())
        path.lineTo(width / 2f, 0f)


        canvas?.drawPath(path, paint)
    }
}