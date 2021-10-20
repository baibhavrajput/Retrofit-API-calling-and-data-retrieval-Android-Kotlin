package com.byfitnation.retrofitapicallandretrieve.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import com.byfitnation.retrofitapicallandretrieve.Data.ApiInterface
import com.byfitnation.retrofitapicallandretrieve.Data.CustomAdapter
import com.byfitnation.retrofitapicallandretrieve.Data.MyDataItem
import com.byfitnation.retrofitapicallandretrieve.R
import com.byfitnation.retrofitapicallandretrieve.databinding.ActivityMainBinding
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var customAdapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding

    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        getMyData()
    }

    private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
            .create(ApiInterface::class.java)
        
        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(
                call: Call<List<MyDataItem>?>,
                response: Response<List<MyDataItem>?>) {

                val responseBody = response.body()!!

                customAdapter = CustomAdapter(responseBody)
                customAdapter.notifyDataSetChanged()

                binding.recyclerView.adapter = customAdapter

            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {

                d("main activity" , "on failure: " +  t.message  )

            }
        })
    }
}