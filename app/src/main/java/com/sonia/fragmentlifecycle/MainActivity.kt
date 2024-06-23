package com.sonia.fragmentlifecycle

import activityinterface.ActivityInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sonia.fragmentlifecycle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding?=null
    var activityInterface: ActivityInterface?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        Toast.makeText(this, "in activity", Toast.LENGTH_SHORT).show()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding?.btnchange?.setOnClickListener {
            activityInterface?.changeColor(2)
        }
        binding?.btnred?.setOnClickListener {
            activityInterface?.changeColor(1)
        }
        binding?.btnblue?.setOnClickListener {
            activityInterface?.changeColor(3)
        }
        binding?.btnpass?.setOnClickListener {
            if(binding?.etvalue?.text?.toString()?.trim().isNullOrEmpty()){
                binding?.etvalue?.error = "Enter Value"
            }else{
                activityInterface?.changeBtnText("${binding?.etvalue?.text?.toString()}")
            }
        }
    }
    fun changeBtnText(text:String){
        binding?.btncounter?.setText(text)
    }
    fun changeText(text:String){
        binding?.etvalue?.setText(text)
    }
}