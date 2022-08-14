package com.example.passwordmanager

import android.os.CountDownTimer

fun startCountDownTimer(
    countDownTime: Long,
    onTick: (millisUntilFinished: Long) -> Unit,
    onFinish: () -> Unit
) {
    object : CountDownTimer(countDownTime, 1000) {

        override fun onTick(millisUntilFinished: Long) {
            onTick(millisUntilFinished)
        }

        override fun onFinish() {
            onFinish()
        }
    }.start()
}
