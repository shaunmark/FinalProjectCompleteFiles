package com.example.finalprojectapp


import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectapp.Api.ApiServices
import com.example.finalprojectapp.Api.ApiUtils
import com.example.finalprojectapp.DataClasses.SearchData
import com.example.finalprojectapp.DataClasses.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchVehiclePage : AppCompatActivity() {

    var apiService: ApiServices? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seach_vehicle_page)

        apiService = ApiUtils.getApiService()

        val searchPlateNumberText = findViewById<EditText>(R.id.deleteVehicleText)
        /*
        searchPlateNumberText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val searchData = SearchData(s.toString())
                val str = s.toString()
                Toast.makeText(this@SearchVehiclePage, "HEY: $str", Toast.LENGTH_SHORT).show()

            }

        })*/

        searchPlateNumberText.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                val searchData = SearchData(searchPlateNumberText.text.toString())
                apiService?.searchVehicle(searchData)?.enqueue(object : Callback<List<SearchResult>> {
                    override fun onResponse(
                        call: Call<List<SearchResult>>,
                        response: Response<List<SearchResult>>
                    ) {
                        val searchResult = response.body()!!
                        recyclerSearch(searchResult)
                    }

                    override fun onFailure(call: Call<List<SearchResult>>, t: Throwable) {
                        Toast.makeText(this@SearchVehiclePage,"Myr", Toast.LENGTH_SHORT).show()
                    }
                })
                return@OnKeyListener true
            }
            false
        })



    }

    private fun recyclerSearch(searchResult: List<SearchResult>) {
        val recyclerViewSearch = findViewById<RecyclerView>(R.id.recyclerViewSearch)
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)
        recyclerViewSearch.adapter = SearchRecyclerViewAdapter(searchResult)
    }
}