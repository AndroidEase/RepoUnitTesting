package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowFlowOnActivity : AppCompatActivity() {

    val TAG = "FlowExceptionHandlingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
           val result =  producer()
               .map {
                   delay(1000)
                   it * 2
                   Log.e(TAG, "Collector Map Thread -  ${Thread.currentThread().name}")
               }
               .flowOn(Dispatchers.IO)
               .filter {
                   delay(500)
                   Log.e(TAG, "Collector Filter Thread -  ${Thread.currentThread().name}")
                   it < 8
               }
               .collect {
                   Log.e(TAG, "Collector Collect Thread -  ${Thread.currentThread().name}")
               }
        }
    }

    private fun producer() : Flow<Int> {
        return flow<Int> {
                val list = listOf(1,2,3,4,5,6,7,8,9,10)
                list.forEach {
                    Log.e(TAG, "Collector Thread Producer -  ${Thread.currentThread().name}")
                    delay(1000)
                    emit(it)
                }

        }
    }

}