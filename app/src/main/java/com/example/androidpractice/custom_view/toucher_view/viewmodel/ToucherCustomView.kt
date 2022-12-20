package com.example.androidpractice.custom_view.toucher_view.viewmodel

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import androidx.core.view.children
import java.lang.Float.max
import java.lang.Float.min


class ToucherCustomView(context: Context, attrs: AttributeSet) : ViewGroup(context, attrs) {
    private var onTouchHappened = false
    private val childrenCount = 3

    private val childPossibleColors = mutableListOf(
        Color.WHITE,
        Color.GREEN,
        Color.YELLOW,
        Color.MAGENTA
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val onTouchListener = OnTouchListener { p0, p1 ->

            children.forEach {
                val lambdaY = p1.y - (it.top + it.bottom) / 2
                val lambdaX = p1.x - (it.left + it.right) / 2

                it.animate().translationY(lambdaY)
                it.animate().translationX(lambdaX)
            }


            onTouchHappened = true
            p0?.performClick()
            invalidate()
            true
        }

        setOnTouchListener(onTouchListener)
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
        removeAllViews()
        setupChildren()

        children.forEach {
            val castedChild = it as? ChildView
            castedChild?.let { child ->
                it.setBackgroundColor(castedChild.color)
                it.layout(
                    child.startX.toInt(),
                    child.startY.toInt(),
                    child.endX.toInt(),
                    child.endY.toInt()
                )
            }
        }
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        if (!onTouchHappened) {
            children.forEach {
                (it as? ChildView)?.let { child ->
                    drawFrame(child, canvas)
                }
            }
        }
    }

    fun resetView() {
        onTouchHappened = false
        requestLayout()
    }

    private fun setupChildren() {
        childPossibleColors.shuffle()

        for (i in 0 until childrenCount) {
            val childColor = childPossibleColors[i]
            val childStartX = (10..measuredWidth / 2).random()
            val childEndX = (childStartX + 1..measuredWidth - 10).random()
            val childStartY = (10..measuredHeight / 2).random()
            val childEndY = (childStartY + 1..measuredHeight - 10).random()

            val child = ChildView(
                childStartX.toFloat(),
                childEndX.toFloat(),
                childStartY.toFloat(),
                childEndY.toFloat(),
                childColor
            )

            addView(child)
        }
    }

    private fun drawFrame(view: ChildView, canvas: Canvas?) {
        val paint = Paint()
        paint.strokeWidth = 10F
        paint.color = Color.BLACK

        canvas?.drawLine(
            view.x,
            view.y,
            view.x,
            view.y + view.height,
            paint
        )

        canvas?.drawLine(
            view.x,
            view.y,
            view.x + view.width,
            view.y,
            paint
        )

        canvas?.drawLine(
            view.x + view.width,
            view.y,
            view.x + view.width,
            view.y + view.height,
            paint
        )

        canvas?.drawLine(
            view.x,
            view.y + view.height,
            view.x + view.width,
            view.y + view.height,
            paint
        )
    }

    inner class ChildView(
        val startX: Float,
        val endX: Float,
        val startY: Float,
        val endY: Float,
        val color: Int,
    ) : View(context)
}