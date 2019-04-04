package com.navegam.fepsfrete

import androidx.appcompat.app.AppCompatActivity

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView

import com.daimajia.numberprogressbar.NumberProgressBar
import com.daimajia.numberprogressbar.OnProgressBarListener
import com.navegam.fepsfrete.Activity.LoginActivity
import com.race604.drawable.wave.WaveDrawable

import java.util.Timer
import java.util.TimerTask

class SplashScreen : AppCompatActivity(), OnProgressBarListener {

    private var img_loading: ImageView? = null

    private var timer: Timer? = null

    private var progressBar: NumberProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val i = Intent(this, LoginActivity::class.java)

        img_loading = findViewById(R.id.img_loading)
        val navegamWave = WaveDrawable(this, R.drawable.navegam_loading)
        img_loading!!.setImageDrawable(navegamWave)

        // Set customised animator here
        val animator = ValueAnimator.ofFloat(0, 1)

        navegamWave.setWaveAmplitude(5)
        animator.repeatCount = 0
        animator.duration = 3000
        animator.interpolator = AccelerateDecelerateInterpolator()
        navegamWave.setIndeterminateAnimator(animator)
        navegamWave.isIndeterminate = true

        //barrar de progresso personalizado com numeracao
        progressBar = findViewById<View>(R.id.progress_bar)
        progressBar!!.setOnProgressBarListener(this)
        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread { progressBar!!.incrementProgressBy(1) }
            }
        }, 2, 22)

        val timer = object : Thread() {
            override fun run() {
                try {
                    Thread.sleep(3000)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(i)
                    finish()
                }
            }
        }

        timer.start()
    }


    override fun onProgressChange(current: Int, max: Int) {
        if (current == max) {

            //para nao ficar repetindo e trava no 100%
            progressBar!!.progress = max
        }
    }
}
