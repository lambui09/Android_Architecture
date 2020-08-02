package com.lambui09.mvvm.data.remote.thread

import kotlinx.coroutines.CoroutineDispatcher

class CoroutinesDispatchersProviderImpl() : CoroutinesDispatchersProvider {
    override val main: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val io: CoroutineDispatcher
        get() = TODO("Not yet implemented")
}