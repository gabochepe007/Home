package com.example.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.home.databinding.ActivityPhoneRegisterBinding
import io.appwrite.Client
import io.appwrite.services.Account
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class PhoneRegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPhoneRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_register)

        binding.registerPhoneBtn.setOnClickListener {
            if(validaNumero()){
                GlobalScope.launch {
                    savePhone()
                }
            }
        }
    }

    private fun validaNumero():Boolean{
        if(binding.registerPhone.text.toString().isEmpty()){
            return false
        }
        return true
    }

    private suspend fun savePhone(){
        val rndmNmbr = Random.nextInt(1, 1000).toString()

        val client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1") // Your API Endpoint
            .setProject("653f4194eb21a8667a2e") // Your project ID

        val account = Account(client)

        val response = account.createPhoneSession(
            rndmNmbr,
            binding.registerPhone.text.toString()
        )
    }
}