package com.sampam.networking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
      val originallist=ArrayList<User>()
    val adapter=useradapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter.onitemclick={
         val intent=Intent(this,useractivity::class.java)
            intent.putExtra("ID",it)
            startActivity(intent)
        }
       userrv.apply {
           adapter=this@MainActivity.adapter
           layoutManager=LinearLayoutManager(this@MainActivity)
       }
        search.isSubmitButtonEnabled=true
        search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchuser(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchuser(newText)
                }
                return true
            }
        })
        search.setOnCloseListener {
            adapter.swapdata(originallist)
            return@setOnCloseListener true
        }

        GlobalScope.launch(Dispatchers.Main) {
            val response= withContext(Dispatchers.IO){client.api.getusers()}
            if(response.isSuccessful){
                response.body()?.let {
                    originallist.addAll(it)
                    adapter.swapdata(it)

                }
            }
        }


    }
    fun searchuser(query:String){
        GlobalScope.launch(Dispatchers.Main) {
            val response= withContext(Dispatchers.IO){client.api.searchusers(query)}
            if(response.isSuccessful){
                response.body()?.let {
                    it.items?.let {
                        it->adapter.swapdata(it)
                    }
                }
            }
        }
    }
}
