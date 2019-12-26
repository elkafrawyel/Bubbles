package com.elwaha.bubbles.utilies

import kotlinx.coroutines.CoroutineDispatcher

data class CoroutinesDispatcherProvider(
    val main: CoroutineDispatcher,
    val computation: CoroutineDispatcher,
    val io: CoroutineDispatcher
)