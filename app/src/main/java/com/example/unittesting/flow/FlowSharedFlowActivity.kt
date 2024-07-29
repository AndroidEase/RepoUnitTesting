package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FlowSharedFlowActivity : AppCompatActivity() {

    val TAG = "FlowMutableSharedFlowActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
               val producer =  producer()
               delay(4000)
               producer.collect {
                        Log.e(TAG, "Collecting   -  ${it}")
               }
        }
    }

    private fun producer() : Flow<Int> {
      val mutableSharedFlow = MutableSharedFlow<Int>()
        GlobalScope.launch {
            val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            list.forEach {
                delay(1000)
                mutableSharedFlow.emit(it)
            }
        }
        return mutableSharedFlow
    }

}