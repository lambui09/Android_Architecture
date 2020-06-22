package com.lambui09.mvvm.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.lambui09.mvvm.extensions.convertDpToIntPx

open class DividerItemDecoration(
    context: Context,
    resId: Int,
    paddingLeftDp: Float = 0f,
    paddingRightDp: Float = 0f
) : ItemDecoration() {
    protected var divider: Drawable = ContextCompat.getDrawable(context, resId)!!
    protected var paddingLeft: Int = context.convertDpToIntPx(paddingLeftDp)
    protected var paddingRight: Int = context.convertDpToIntPx(paddingRightDp)

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val left = parent.paddingLeft + paddingLeft
        val right = parent.width - paddingRight
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params =
                child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + divider.intrinsicHeight
            divider.setBounds(left, top, right, bottom)
            divider.draw(canvas)
        }
    }
}