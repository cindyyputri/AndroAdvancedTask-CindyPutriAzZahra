package com.example.taskadvanced.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.taskadvanced.R

class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_game, container, false)

        val playerOne = view.findViewById<EditText>(R.id.playerOne)
        val playerTwo = view.findViewById<EditText>(R.id.playerTwo)
        val startGameButton = view.findViewById<Button>(R.id.startGameButton)
        startGameButton.setOnClickListener {
            val getPlayerOneName = playerOne.text.toString()
            val getPlayerTwoName = playerTwo.text.toString()
            if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter player name", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(requireContext(), TicTacToeActivity::class.java)
                intent.putExtra("playerOne", getPlayerOneName)
                intent.putExtra("playerTwo", getPlayerTwoName)
                startActivity(intent)
            }
        }
        return view
    }
}