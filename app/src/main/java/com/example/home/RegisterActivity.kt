package com.example.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.home.databinding.ActivityRegisterBinding
import io.appwrite.Client
import io.appwrite.services.Account
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.registerBtn.setOnClickListener {

            if(validarCampos()){
                GlobalScope.launch {
                    saveForm()
                    println("Función asincrona realizada")
                }
            }

        }
    }

    private fun validarCampos():Boolean{
        val nombre = binding.registerName.text.toString()
        val mail = binding.registerMail.text.toString()
        val password = binding.registerPassword.text.toString()

        if(mail.isEmpty() || password.isEmpty() ){
            Toast.makeText(this, "mail and password inputs are required!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    private suspend fun saveForm(){
        val rndmNmbr = Random.nextInt(1, 1000).toString()

        val client = Client(this)
            .setEndpoint("https://cloud.appwrite.io/v1") // Your API Endpoint
            .setProject("653f4194eb21a8667a2e")

        val account = Account(client)
        val response = account.create(
            rndmNmbr,
            binding.registerMail.text.toString(),
            binding.registerPassword.text.toString(),
            binding.registerName.text.toString()
        )
    }
}