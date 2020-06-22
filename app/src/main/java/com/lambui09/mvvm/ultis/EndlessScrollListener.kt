package com.lambui09.mvvm.ultis

import android.view.View
import androidx.annotation.Px
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView

/**
 * Support for load more if RecyclerView inside NestedScrollView
 */
abstract class EndlessScrollListener(layoutManager: RecyclerView.LayoutManager?) :
    NestedScrollView.OnScrollChangeListener {
    // The current offset index of data you have loaded
    private var currentPage = 1

    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0

    // True if we are still waiting for the last set of data to load.
    private var loading = true

    // Sets the starting page index
    private val startingPageIndex = 1

    // The minimum amount of pixels to have below your current scroll position
    // before loading more.
    private var visibleThresholdDistance = 300

    var mLayoutManager: RecyclerView.LayoutManager? = layoutManager

    override fun onScrollChange(
        scrollView: NestedScrollView,
        x: Int,
        y: Int,
        oldx: Int,
        oldy: Int
    ) { // We take the last son in the scrollview
        val view: View = scrollView.getChildAt(scrollView.childCount - 1)
        val distanceToEnd: Int = view.bottom - (scrollView.height + scrollView.scrollY)
        val totalItemCount: Int = mLayoutManager?.itemCount ?: 0
        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            currentPage = startingPageIndex
            previousTotalItemCount = totalItemCount
            if (totalItemCount == 0) {
                loading = true
            }
        }
        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }
        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && distanceToEnd <= visibleThresholdDistance) {
            currentPage++
            onLoadMore(currentPage, totalItemCount)
            loading = true
        }
    }

    fun resetTotalItemCount() {
        previousTotalItemCount = 0
        loading = true
        currentPage = 1
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int, totalItemsCount: Int)

    fun setVisibleThresholdDistance(@Px visibleThresholdDistance: Int) {
        this.visibleThresholdDistance = visibleThresholdDistance
    }
}