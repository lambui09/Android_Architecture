package com.lambui09.mvvm.ui.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lambui09.mvvm.extensions.hasFlag

class LinearOffsetDecoration(
    private val itemOffset: Int,
    private val edgeOffset: Int
) : RecyclerView.ItemDecoration() {

    private var flags: Int = FLAG_OFFSET_ITEM or FLAG_OFFSET_EDGE

    var startOffset = itemOffset
    var endOffset = itemOffset

    companion object {
        const val FLAG_OFFSET_ITEM = 0x00000001
        const val FLAG_OFFSET_EDGE = 0x00000002
    }

    fun setFlags(flags: Int) {
        this.flags = flags
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val layoutManager = parent.layoutManager
        if (layoutManager !is LinearLayoutManager) return
        val childPos = parent.getChildAdapterPosition(view)

        if (layoutManager.orientation == RecyclerView.VERTICAL) {
            if (flags hasFlag FLAG_OFFSET_EDGE) {
                outRect.left = edgeOffset; outRect.right = edgeOffset
            }

            if (flags hasFlag FLAG_OFFSET_ITEM) {
                outRect.top = itemOffset; outRect.bottom = itemOffset
            }
            if (childPos == 0)
                outRect.top += startOffset
            else if (childPos + 1 == parent.adapter?.itemCount) {
                outRect.bottom = endOffset
            }
        } else {
            if (flags hasFlag FLAG_OFFSET_EDGE) {
                outRect.top = edgeOffset; outRect.bottom = edgeOffset
            }

            if (flags hasFlag FLAG_OFFSET_ITEM) {
                outRect.left = itemOffset; outRect.right = itemOffset
            }
            if (childPos == 0)
                outRect.left += startOffset
            else if (childPos + 1 == parent.adapter?.itemCount) {
                outRect.right = endOffset
            }
        }

    }
}