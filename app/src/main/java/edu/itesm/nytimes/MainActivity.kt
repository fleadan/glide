package edu.itesm.nytimes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val BASE_URL = "https://api.nytimes.com/svc/books/v3/lists/current/"
    private lateinit var recyclerView: RecyclerView
    private lateinit var manager: RecyclerView.LayoutManager
    private lateinit var myAdapter: RecyclerView.Adapter<*>
    private lateinit var results: Results

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        manager = LinearLayoutManager(this)
        getAllData()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    fun getAllData(){

        val callToService = getRetrofit().create(APIService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val responseFromService = callToService.getBooks()
            runOnUiThread {
                results = responseFromService.body() as Results

                if (responseFromService.isSuccessful) {
                    Log.i("Books", results.results?.books.toString())

                    recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {

                        layoutManager = manager
                        myAdapter = BooksAdapter(results.results?.books)
                        adapter = myAdapter


                    }

                } else {
                    Toast.makeText(applicationContext, "Error!", Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    override fun onStart() {
        super.onStart()

        val toast = Toast.makeText(applicationContext, "onStart", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onResume() {
        super.onResume()

        val toast = Toast.makeText(applicationContext, "onResume", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onPause() {
        super.onPause()

        val toast = Toast.makeText(applicationContext, "onPause", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onStop() {
        super.onStop()

        val toast = Toast.makeText(applicationContext, "onStop", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onDestroy() {
        super.onDestroy()

        val toast = Toast.makeText(applicationContext, "onDestroy", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onRestart() {
        super.onRestart()
        val toast = Toast.makeText(applicationContext, "onRestart", Toast.LENGTH_SHORT)
        toast.show()
    }
}