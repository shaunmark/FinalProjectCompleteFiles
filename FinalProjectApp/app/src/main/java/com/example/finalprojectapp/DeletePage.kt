package com.example.finalprojectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.finalprojectapp.Api.ApiServices
import com.example.finalprojectapp.Api.ApiUtils
import com.example.finalprojectapp.DataClasses.DeleteSuccess
import com.example.finalprojectapp.DataClasses.DeleteVehicle
import retrofit2.Call
import retrofit2.Response

class DeletePage : AppCompatActivity() {

    var apiService: ApiServices? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_page)

        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val deleteVehicleText = findViewById<EditText>(R.id.deleteVehicleText)

        deleteButton.setOnClickListener {
            if (deleteVehicleText.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Plate Number", Toast.LENGTH_SHORT).show()
            }
            else {
                apiService = ApiUtils.getApiService()
                val deleteVehicle = DeleteVehicle(deleteVehicleText.text.toString())
                apiService?.deleteVehicle(deleteVehicle)?.enqueue(object : retrofit2.Callback<DeleteSuccess>{
                    override fun onResponse(
                        call: Call<DeleteSuccess>,
                        response: Response<DeleteSuccess>
                    ) {
                        if (response.body()!!.response == "True") {
                            Toast.makeText(this@DeletePage, "Vehicle Deleted!", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                        else {
                            Toast.makeText(this@DeletePage, "No such vehicle in database!", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<DeleteSuccess>, t: Throwable) {
                        Toast.makeText(this@DeletePage, "Error: $t", Toast.LENGTH_SHORT).show()
                    }

                })

            }
        }

    }
}