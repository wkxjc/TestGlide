package com.example.glidetest

import android.graphics.BitmapFactory
import android.os.Bundle
import android.transition.TransitionManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.glidetest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var ivTopNextScale = 2f
    private var ivBottomNextScale = 2f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnTopRed.setOnClickListener {
            TopGlideUtils.load(this, BitmapFactory.decodeResource(resources, R.mipmap.flower_red), binding.ivTop)
        }
        binding.btnTopBlue.setOnClickListener {
            TopGlideUtils.load(this, BitmapFactory.decodeResource(resources, R.mipmap.flower_blue), binding.ivTop)
        }
        binding.btnTopAnim.setOnClickListener {
            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.cl)
            constraintSet.constrainWidth(binding.ivTop.id, (binding.ivTop.width * ivTopNextScale).toInt())
            constraintSet.constrainHeight(binding.ivTop.id, (binding.ivTop.height * ivTopNextScale).toInt())
            ivTopNextScale = if (ivTopNextScale == 2f) 0.5f else 2f
            TransitionManager.beginDelayedTransition(binding.cl)
            constraintSet.applyTo(binding.cl)
        }

        binding.btnBottomRed.setOnClickListener {
            BottomGlideUtils.load(this, BitmapFactory.decodeResource(resources, R.mipmap.flower_red), binding.ivBottom)
        }
        binding.btnBottomBlue.setOnClickListener {
            BottomGlideUtils.load(this, BitmapFactory.decodeResource(resources, R.mipmap.flower_blue), binding.ivBottom)
        }
        binding.btnBottomAnim.setOnClickListener {
            val constraintSet = ConstraintSet()
            constraintSet.clone(binding.cl)
            constraintSet.constrainWidth(binding.ivBottom.id, (binding.ivBottom.width * ivBottomNextScale).toInt())
            constraintSet.constrainHeight(binding.ivBottom.id, (binding.ivBottom.height * ivBottomNextScale).toInt())
            ivBottomNextScale = if (ivBottomNextScale == 2f) 0.5f else 2f
            TransitionManager.beginDelayedTransition(binding.cl)
            constraintSet.applyTo(binding.cl)
        }
    }
}