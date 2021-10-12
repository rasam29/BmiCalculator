package com.github.rasam29.bmicalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.github.rasam29.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutView:ActivityMainBinding  = DataBindingUtil.setContentView(this,R.layout.activity_main)
        layoutView.aboutUs.setOnClickListener {
            //startActivity(
            //    Intent(this,AboutUsActivity::class.java)
            //)
        }
        layoutView.calculateBmiButton.setOnClickListener {
            startActivity(
                Intent(this,CalculateBmiActivity::class.java)
            )
        }
    }
}