package com.example.taskadvanced.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.taskadvanced.R

class ResultDialog(
    context: Context,
    private val message: String,
    private val ticTacToeActivity: TicTacToeActivity
) : Dialog(context) {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_dialog)

        val messageText = findViewById<TextView>(R.id.messageText)
        val startAgainButton = findViewById<Button>(R.id.startAgainButton)

        messageText.text = message

        startAgainButton.setOnClickListener {
            ticTacToeActivity.restartMatch()
            dismiss()
        }
    }
}