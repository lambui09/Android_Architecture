package com.lambui09.mvvm.data.remote.thread

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutinesDispatchersProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}