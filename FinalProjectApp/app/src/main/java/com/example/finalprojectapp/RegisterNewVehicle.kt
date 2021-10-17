package com.example.finalprojectapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalprojectapp.Api.ApiServices
import com.example.finalprojectapp.Api.ApiUtils
import com.example.finalprojectapp.DataClasses.LoginSuccess
import com.example.finalprojectapp.DataClasses.RegisterVehicle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterNewVehicle : AppCompatActivity() {

    var apiService: ApiServices? = null

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_new_vehicle)

        apiService = ApiUtils.getApiService()


        val firstName = findViewById<EditText>(R.id.firstNameText)
        val lastName = findViewById<EditText>(R.id.lastNameText)
        val plateNumber = findViewById<EditText>(R.id.plateNumberText)
        val mobileNumber = findViewById<EditText>(R.id.mobileNumberText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val emailId = findViewById<EditText>(R.id.emailText)

        registerButton.setOnClickListener {

            if (firstName.text.toString().isEmpty() or lastName.text.toString().isEmpty() or
                    plateNumber.text.toString().isEmpty() or mobileNumber.text.toString().isEmpty() or emailId.text.toString().isEmpty())
                Toast.makeText(this, "Fill in all the data", Toast.LENGTH_SHORT).show()
            else if (!isValidEmail(emailId.text))
                Toast.makeText(this, "Enter valid Email!", Toast.LENGTH_SHORT).show()
            else if (mobileNumber.text.length!=10)
                Toast.makeText(this, "Enter valid Mobile Number!", Toast.LENGTH_SHORT).show()
            else {
                val registerVehicle = RegisterVehicle( firstName.text.toString(), lastName.text.toString(),
                    plateNumber.text.toString(), mobileNumber.text.toString()
                )

                apiService?.sendVehicleData(registerVehicle)?.enqueue(object : Callback<LoginSuccess> {
                    override fun onResponse(
                        call: Call<LoginSuccess>,
                        response: Response<LoginSuccess>
                    ) {
                        Toast.makeText(this@RegisterNewVehicle,"You have successfully Registered", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    override fun onFailure(call: Call<LoginSuccess>, t: Throwable) {
                        Toast.makeText(this@RegisterNewVehicle,"error : "+t.stackTrace, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }




    }
}