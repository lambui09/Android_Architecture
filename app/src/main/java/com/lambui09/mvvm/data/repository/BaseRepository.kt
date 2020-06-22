package com.lambui09.mvvm.data.repository

import com.lambui09.mvvm.data.common.DataResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class BaseRepository {

    /**
     * Make template code to get DataResult return to ViewModel
     * Support for call api, get data from database
     * Handle exceptions: Convert exception to Result.Error
     * Avoid duplicate code
     *
     * Default CoroutineContext is IO for repository
     */
    protected suspend fun <R> withResultContext(
        context: CoroutineContext = Dispatchers.IO,
        successBlock: suspend CoroutineScope.() -> R,
        errorBlock: (suspend CoroutineScope.(Exception) -> DataResult.Error)? = null
    ): DataResult<R> = withContext(context) {
        return@withContext try {
            val response = successBlock()
            DataResult.Success(response)
        } catch (e: Exception) {
            e.printStackTrace()
            return@withContext errorBlock?.invoke(this, e) ?: DataResult.Error(e)
        }
    }

    protected suspend fun <R> withResultContext(
        context: CoroutineContext = Dispatchers.IO,
        successBlock: suspend CoroutineScope.() -> R
    ): DataResult<R> = withResultContext(context, successBlock, null)

}
