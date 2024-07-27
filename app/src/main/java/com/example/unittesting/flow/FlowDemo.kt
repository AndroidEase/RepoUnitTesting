package com.example.unittesting.flow

import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.delay

class FlowDemo {

    fun getFlow() = flow<Int> {
        emit(1)
        delay(2000)
        emit(2)
        delay(2000)
    }
}