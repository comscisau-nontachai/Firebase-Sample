package com.example.firebaseexample.firebase_cloud_messaging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.firebaseexample.R
import com.example.firebaseexample.databinding.ActivityCloudMessagingBinding
import com.google.firebase.messaging.FirebaseMessaging

class CloudMessagingActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityCloudMessagingBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCloudMessagingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnGetToken.setOnClickListener { 
            getToken()
        }
    }
    
    private fun getToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task -> 
            if(!task.isSuccessful){
                Log.d("LOG_TAG", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result
            Log.d("LOG_TAG", token)
            Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
        }
    }
}