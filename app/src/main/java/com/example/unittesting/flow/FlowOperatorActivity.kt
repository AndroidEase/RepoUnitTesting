package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class FlowOperatorActivity : AppCompatActivity() {

    val TAG = "FlowOperatorActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
//           val result =  producer().first()
//            Log.e(TAG, "Result is ${result.toString()}")
        }

        GlobalScope.launch(Dispatchers.Main) {
//            val result =  producer().toList()
//            Log.e(TAG, "Result iss ${result.toString()}")
        }

        GlobalScope.launch(Dispatchers.Main) {
//            producer()
//                .onStart {
//                    Log.e(TAG, "Starting Out")
//                }
//                .onCompletion {
//                    Log.e(TAG, "Complete")
//                }
//                .onEach {
//                    Log.e(TAG, "About to emit - $it")
//                }
//                .collect {
//                    Log.e(TAG, "${it.toString()}")
//                }
        }

        GlobalScope.launch(Dispatchers.Main) {
            producer()
                .map { it * 2 }
                .filter { it < 8 }
                .collect() {
                    Log.d(TAG, it.toString())
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