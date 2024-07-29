package com.example.unittesting.flow

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unittesting.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class FormattedNotesMapActivity : AppCompatActivity() {

    val TAG = "FlowFormattedNotesMapActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.Main) {
            getNotes()
                .map {
                    FormattedNote(it.isActive, it.title.uppercase(), it.description)
                }
                .filter { it.isActive }
                .collect {
                    println(TAG + it)
                }
        }
    }

    private fun getNotes() : Flow<Note> {
        val list = listOf(
            Note(1, true, "First", "First Description"),
            Note(2, false, "Second", "Second Description"),
            Note(3, true, "First", "Third Description")
        )
        return list.asFlow()
    }

    data class Note(val id: Int, val isActive : Boolean, val title : String, val description : String)
    data class FormattedNote(val isActive: Boolean, val title: String, val description: String)
}