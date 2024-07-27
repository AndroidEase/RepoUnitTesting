package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class Flow_Operator_Activity : AppCompatActivity() {

    val TAG = "FlowOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .onStart {
                    Log.e(TAG, "Starting Out")
                }
                .onCompletion {
                    Log.e(TAG, "Complete")
                }
                .onEach {
                    Log.e(TAG, "About to emit - $it")
                }
                .collect {
                    Log.e(TAG, "${it.toString()}")
                }
        }
    }

    private fun producer() : Flow<Int> {
        return flow<Int> {
            val list = listOf(1,2,3,4,5,6,7,8,9,10)
            list.forEach {
                delay(1000)
                emit(it)
            }
        }
    }

}