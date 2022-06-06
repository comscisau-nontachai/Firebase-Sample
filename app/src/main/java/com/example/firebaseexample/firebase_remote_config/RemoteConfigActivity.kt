package com.example.firebaseexample.firebase_remote_config

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebaseexample.R
import com.example.firebaseexample.databinding.ActivityMainBinding
import com.example.firebaseexample.databinding.ActivityRemoteConfigBinding
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class RemoteConfigActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRemoteConfigBinding
    private val remoteConfig : FirebaseRemoteConfig = Firebase.remoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemoteConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //fetch and active params
        fetchData()

        binding.btnFetch.setOnClickListener {
            fetchData()
        }
    }

    private fun fetchData(){
        val configSetting = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSetting)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
            if(task.isSuccessful){
                val updated = task.result
                Toast.makeText(this,"Config params updated : $updated", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Fetch failed", Toast.LENGTH_SHORT).show()
            }
            displayPrice()
        }
    }

    private fun displayPrice(){
        val initPrice = remoteConfig.getLong("price")
        val isPromotion = remoteConfig.getBoolean("is_promotion_on")
        val discount = remoteConfig.getLong("discount")
        var finalPrice = initPrice

        if(isPromotion){
            finalPrice = initPrice - discount
        }

        binding.tvPrice.text = "Your price is $finalPrice"
        binding.tvDiscount.text = "Your discount is $discount"
    }
}