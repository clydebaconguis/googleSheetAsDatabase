package com.example.msgshareapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class ReadActivity : AppCompatActivity() {
    private lateinit var recyclerGetData: RecyclerView
    private lateinit var allBooks : ArrayList<Data>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        recyclerGetData = findViewById(R.id.recyclerGetData)
        getBooks()

    }

    private fun getBooks(){

        val url = "https://script.google.com/macros/s/AKfycbxlwqTqqKet-NZhRvKYdVeq7drmzgWgdMHu1uT8WG9XkBvNLM8/exec"
        val request = JsonObjectRequest(Request.Method.GET,url,null, {
                    response -> try {
                val jsonArray = response.getJSONArray("data")
                for (i in 0 until jsonArray.length()){
                    val book = jsonArray.getJSONObject(i)
                    val bookName = book.getString("bookName")
                    val bookAuthor = book.getString("bookAuthor")
                    val bookPrice = book.getString("bookPrice")
                    val bookRating = book.getString("bookRating")

                    allBooks.add(Data(bookName,bookAuthor,bookPrice,bookRating))
                }
            }catch (e: JSONException){
                e.printStackTrace()
            }
            },
        {
            Toast.makeText(this@ReadActivity, it.toString(), Toast.LENGTH_SHORT).show()
        })

        val queue = Volley.newRequestQueue(this@ReadActivity)
        queue.add(request)
    }

    private fun adapter(){

    }

    class Data(val bookName:String, val bookAuthor:String, val bookPrice:String, val bookRating:String)
}

