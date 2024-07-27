package com.example.unittesting.flow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChannelActivity : AppCompatActivity() {

    val channel = Channel<Int>()

    val TAG = "ChannelActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        println("skhhfk")

        CoroutineScope(Dispatchers.Main).launch {
            producer()
            consumer()
        }
    }

    private suspend fun producer() {
       CoroutineScope(Dispatchers.Main).launch {
           channel.send(1)
           channel.send(2)
       }
    }

    private suspend fun consumer() {
        CoroutineScope(Dispatchers.Main).launch {
            Log.d(TAG, channel.receive().toString())
            Log.d(TAG, channel.receive().toString())
            channel.receive()
        }
    }

    private suspend fun getUser(id: Int) : String {
        delay(1000)
        return "User$id"
    }
}