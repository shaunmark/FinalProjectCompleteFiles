package com.example.finalprojectapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectapp.Api.ApiServices
import com.example.finalprojectapp.Api.ApiUtils
import com.example.finalprojectapp.DataClasses.LoginSuccess
import com.example.finalprojectapp.DataClasses.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var apiService: ApiServices? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiService = ApiUtils.getApiService()

        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val loginUsernameText = findViewById<EditText>(R.id.loginUsernameText)
            val loginPasswordText = findViewById<EditText>(R.id.loginPasswordText)
            val user = User (loginUsernameText.text.toString(), loginPasswordText.text.toString())

            apiService?.sendUserLogin(user)?.enqueue(object : Callback<LoginSuccess> {
                override fun onResponse(
                    call: Call<LoginSuccess>,
                    response: Response<LoginSuccess>
                ) {
                    val loginSuccess:LoginSuccess = response.body()!!
                    if (loginSuccess.response == "True") {
                        Toast.makeText(this@MainActivity, "Login Success", Toast.LENGTH_SHORT).show()
                        val newActivity = Intent(this@MainActivity, HomePage::class.java)
                        startActivity(newActivity)
                        finish()
                    }
                    else if (loginSuccess.response == "False") {
                        Toast.makeText(this@MainActivity, "User does not exists!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<LoginSuccess>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"error : "+t.stackTrace, Toast.LENGTH_LONG).show()
                }

            })
        }

    }
}