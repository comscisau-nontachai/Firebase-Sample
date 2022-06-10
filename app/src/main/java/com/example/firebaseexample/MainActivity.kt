package com.example.firebaseexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebaseexample.databinding.ActivityMainBinding
import com.example.firebaseexample.firebase_cloud_messaging.CloudMessagingActivity
import com.example.firebaseexample.firebase_remote_config.RemoteConfigActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRemoteConfig.setOnClickListener {
            startActivity(Intent(this,RemoteConfigActivity::class.java))
        }

        binding.btnCloudMessaging.setOnClickListener {
            startActivity(Intent(this,CloudMessagingActivity::class.java))
        }
    }
}