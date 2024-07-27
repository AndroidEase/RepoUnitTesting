package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class FlowActivity : AppCompatActivity() {

    val channel = Channel<Int>()

    val TAG = "FlowActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.Main).launch {
            val data : Flow<Int> = producer()
            data.collect {
                Log.d("FlowActivity - 1", it.toString())
            }
        }

        // This example is of cold flow, so If this consumer receives data after 2500 seconds, still it will receive data from beginning
        CoroutineScope(Dispatchers.Main).launch {
            val data : Flow<Int> = producer()
            delay(2500)
            data.collect {
                Log.d("FlowActivity - 2", it.toString())
            }
        }

//        GlobalScope.launch {
//            delay(3500)
//            job.cancel()
//        }
    }

    private fun producer() = flow<Int> {
       val list = listOf(1,2,3,4,5,6,7,8,9,10)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }
}