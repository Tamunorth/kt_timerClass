package com.example.counter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.counter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private var countDownTimer: CountDownTimer? = null;


    private var timerDuration: Long = 60000

    private var pauseOffset: Long = 0;


    private var binding: ActivityMainBinding? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding?.root);

//        val timer: TextView = findViewById(R.id.timer);
//        val btnStart: Button = findViewById(R.id.start);

        binding?.timer?.text = (timerDuration / 1000).toString();
        binding?.start?.setOnClickListener {

            Toast.makeText(
                this, "Testing binding", Toast.LENGTH_LONG
            ).show()


            startTimer()
        }


        binding?.pause?.setOnClickListener {

            pauseTimer()
        }

        binding?.reset?.setOnClickListener {

            resetTimer()
        }


    }

    private fun startTimer() {


        countDownTimer = object : CountDownTimer(timerDuration - pauseOffset, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                pauseOffset = timerDuration - millisUntilFinished;

                binding?.timer?.text = (millisUntilFinished / 1000).toString()
            }


            override fun onFinish() {

                resetTimer()
                Toast.makeText(this@MainActivity, "Timer Ended", Toast.LENGTH_LONG).show()
            }
        }.start()

    }

    private fun pauseTimer() {

        if (countDownTimer != null) {


            countDownTimer!!.cancel();
        }
    }

    private fun resetTimer() {


        if (countDownTimer != null) {
            countDownTimer!!.cancel()

            binding?.timer?.text = (timerDuration / 1000).toString()

            countDownTimer = null

            pauseOffset = 0
        }

    }

}