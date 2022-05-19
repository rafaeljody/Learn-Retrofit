package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG: String = "MainActivity"

    lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        setupRecyclerView()
        getDataFromApi()
    }

    private fun setupRecyclerView() {
        mainAdapter = MainAdapter(arrayListOf(), object : MainAdapter.OnAdapterListener{
            override fun onClick(result: MainModel.Results) {
//                Toast.makeText(applicationContext, "muncul : ${result.title}", Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(applicationContext, DetailActivity::class.java)
                        .putExtra("intent_name_title", result.name.title)
                        .putExtra("intent_name_first", result.name.first)
                        .putExtra("intent_name_last", result.name.last)
                        .putExtra("intent_picture", result.picture.large)
                        .putExtra("intent_gender", result.gender)
                        .putExtra("intent_email", result.email)
                        .putExtra("intent_phone", result.phone)
                )
            }

        })
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mainAdapter
        }
    }

    private fun getDataFromApi() {
        progressBar.visibility = View.VISIBLE
        ApiService.endpoint.getData()
            .enqueue(object : Callback<MainModel>{
                override fun onResponse(
                    call: Call<MainModel>,
                    response: Response<MainModel>
                ) {
                    progressBar.visibility = View.GONE
                    if (response.isSuccessful){
                        showData( response.body()!! )
                    }
                }

                override fun onFailure(call: Call<MainModel>, t: Throwable) {
                    progressBar.visibility = View.GONE
                    printLog( t.toString() )
                }

            })
    }

    private fun printLog(message: String) {
        Log.d(TAG, message)
    }

    private fun showData(data: MainModel){

        val results = data.results
        mainAdapter.setData(results)

//        for (result in results ){
//            printLog("title : ${result.title}")
//        }

    }

}