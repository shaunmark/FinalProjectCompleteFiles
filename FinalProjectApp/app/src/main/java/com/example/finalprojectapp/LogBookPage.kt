package com.example.finalprojectapp


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectapp.Api.ApiServices
import com.example.finalprojectapp.Api.ApiUtils
import com.example.finalprojectapp.DataClasses.LogBookData
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LogBookPage : AppCompatActivity() {

    var apiService: ApiServices? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_book_page)

        apiService = ApiUtils.getApiService()


        apiService?.getLogBook()?.enqueue(object : retrofit2.Callback<List<LogBookData>> {
            override fun onResponse(
                call: Call<List<LogBookData>>,
                response: Response<List<LogBookData>>
            ) {
                val logBookList: List<LogBookData> = response.body()!!
                recyclerCol(logBookList)
            }

            override fun onFailure(call: Call<List<LogBookData>>, t: Throwable) {
                Toast.makeText(this@LogBookPage,"Myr", Toast.LENGTH_SHORT).show()
            }

        })


    }

    private fun recyclerCol(logBookList : List<LogBookData>) {
        val recyclerViewLogBook = findViewById<RecyclerView>(R.id.recyclerViewLogBook)
        recyclerViewLogBook.layoutManager = LinearLayoutManager(this@LogBookPage)


        recyclerViewLogBook.adapter = LogBookRecyclerViewAdapter(logBookList)
    }
}

/*
                val logBookList = response.body()!!
                val recyclerViewLogBook = findViewById<RecyclerView>(R.id.recyclerViewLogBook)
                recyclerViewLogBook.layoutManager = LinearLayoutManager(this@LogBookPage)
                recyclerViewLogBook.adapter = LogBookRecyclerViewAdapter(logBookList)*/