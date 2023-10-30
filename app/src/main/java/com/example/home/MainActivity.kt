package com.example.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.home.databinding.ActivityMainBinding
import io.appwrite.Client
import io.appwrite.services.Account
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1")
            .setProject("653f4194eb21a8667a2e")
            .setSelfSigned(true)

        binding.register.setOnClickListener{
            val cambio = Intent(this, RegisterActivity::class.java)
            startActivity(cambio)
        }

        binding.regPhoneBtn.setOnClickListener{
            val change = Intent(this, PhoneRegisterActivity::class.java)
            startActivity(change)
        }

        binding.loginFb.setOnClickListener{
            GlobalScope.launch {
                oAuth()
            }

        }

    }

    private suspend fun oAuth(){
        val client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1") // Your API Endpoint
            .setProject("653f4194eb21a8667a2e") // Your project ID

        val account = Account(client)

        account.createOAuth2Session(this@MainActivity, "facebook")

        Toast.makeText(this, "Iniciando sesión con proveedor", Toast.LENGTH_SHORT).show()

    }
}