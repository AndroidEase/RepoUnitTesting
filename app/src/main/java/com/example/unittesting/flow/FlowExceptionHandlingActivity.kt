package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FlowExceptionHandlingActivity : AppCompatActivity() {

    val TAG = "FlowExceptionHandlingActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            try {
                producer()
                    .collect {
                        Log.e(TAG, "Collector Consumer  -  ${Thread.currentThread().name}")
                        throw Exception("Error in Collector")
                    }
            } catch (e: Exception) {
                Log.e(TAG, "Collector Handle Exception -  ${e.message}")
            }
        }
    }

    private fun producer() : Flow<Int> {
        return flow<Int> {
                val list = listOf(1,2,3,4,5,6,7,8,9,10)
                list.forEach {
                    Log.e(TAG, "Collector  Producer -  ${Thread.currentThread().name}")
                    delay(1000)
                    throw Exception("Error in Emitter")

                    emit(it)
                }
        }.catch {
            Log.e(TAG, "Emitter Catch -  ${it.message}")
            emit(-1)
        }
    }

}