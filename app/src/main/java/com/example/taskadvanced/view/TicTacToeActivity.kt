package com.example.taskadvanced.view

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.taskadvanced.R
import com.example.taskadvanced.databinding.ActivityTicTacToeBinding

class TicTacToeActivity() : AppCompatActivity() {
    private lateinit var binding: ActivityTicTacToeBinding
    private val combinationList: MutableList<IntArray> = ArrayList()
    private var boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0) //9 zero
    private var playerTurn = 1
    private var totalSelectedBoxes = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTicTacToeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        combinationList.add(intArrayOf(0, 1, 2))
        combinationList.add(intArrayOf(3, 4, 5))
        combinationList.add(intArrayOf(6, 7, 8))
        combinationList.add(intArrayOf(0, 3, 6))
        combinationList.add(intArrayOf(1, 4, 7))
        combinationList.add(intArrayOf(2, 5, 8))
        combinationList.add(intArrayOf(2, 4, 6))
        combinationList.add(intArrayOf(0, 4, 8))
        val getPlayerOneName = intent.getStringExtra("playerOne")
        val getPlayerTwoName = intent.getStringExtra("playerTwo")
        binding.playerOneName.setText(getPlayerOneName)
        binding.playerTwoName.setText(getPlayerTwoName)
        binding.image1.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(0)) {
                performAction(view as ImageView, 0)
            }
        })
        binding.image2.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(1)) {
                performAction(view as ImageView, 1)
            }
        })
        binding.image3.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(2)) {
                performAction(view as ImageView, 2)
            }
        })
        binding.image4.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(3)) {
                performAction(view as ImageView, 3)
            }
        })
        binding.image5.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(4)) {
                performAction(view as ImageView, 4)
            }
        })
        binding.image6.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(5)) {
                performAction(view as ImageView, 5)
            }
        })
        binding.image7.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(6)) {
                performAction(view as ImageView, 6)
            }
        })
        binding.image8.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(7)) {
                performAction(view as ImageView, 7)
            }
        })
        binding.image9.setOnClickListener(View.OnClickListener { view ->
            if (isBoxSelectable(8)) {
                performAction(view as ImageView, 8)
            }
        })
    }

    private fun performAction(imageView: ImageView, selectedBoxPosition: Int) {
        boxPositions[selectedBoxPosition] = playerTurn
        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.ximage)
            if (checkResults()) {
                val resultDialog = ResultDialog(
                    this@TicTacToeActivity,
                    binding.playerOneName.getText().toString()
                            + " is a Winner!", this@TicTacToeActivity
                )
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else if (totalSelectedBoxes == 9) {
                val resultDialog = ResultDialog(this@TicTacToeActivity, "Match Draw", this@TicTacToeActivity)
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else {
                changePlayerTurn(2)
                totalSelectedBoxes++
            }
        } else {
            imageView.setImageResource(R.drawable.oimage)
            if (checkResults()) {
                val resultDialog = ResultDialog(
                    this@TicTacToeActivity, (binding.playerTwoName.getText().toString()
                            + " is a Winner!"), this@TicTacToeActivity
                )
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else if (totalSelectedBoxes == 9) {
                val resultDialog = ResultDialog(this@TicTacToeActivity, "Match Draw", this@TicTacToeActivity)
                resultDialog.setCancelable(false)
                resultDialog.show()
            } else {
                changePlayerTurn(1)
                totalSelectedBoxes++
            }
        }
    }

    private fun changePlayerTurn(currentPlayerTurn: Int) {
        playerTurn = currentPlayerTurn
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border)
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box)
        } else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border)
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box)
        }
    }

    private fun checkResults(): Boolean {
        var response = false
        for (i in combinationList.indices) {
            val combination = combinationList[i]
            if ((boxPositions[combination[0]] == playerTurn) && (boxPositions[combination[1]] == playerTurn) && (
                        boxPositions[combination[2]] == playerTurn)
            ) {
                response = true
            }
        }
        return response
    }

    private fun isBoxSelectable(boxPosition: Int): Boolean {
        var response = false
        if (boxPositions[boxPosition] == 0) {
            response = true
        }
        return response
    }

    fun restartMatch() {
        boxPositions = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0) //9 zero
        playerTurn = 1
        totalSelectedBoxes = 1
        binding.image1.setImageResource(R.drawable.white_box)
        binding.image2.setImageResource(R.drawable.white_box)
        binding.image3.setImageResource(R.drawable.white_box)
        binding.image4.setImageResource(R.drawable.white_box)
        binding.image5.setImageResource(R.drawable.white_box)
        binding.image6.setImageResource(R.drawable.white_box)
        binding.image7.setImageResource(R.drawable.white_box)
        binding.image8.setImageResource(R.drawable.white_box)
        binding.image9.setImageResource(R.drawable.white_box)
    }
}