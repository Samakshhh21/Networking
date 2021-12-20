package com.sampam.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.useradap.*
import kotlinx.android.synthetic.main.useradap.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class useractivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_useractivity)
        val id=intent.getStringExtra("ID")
        GlobalScope.launch(Dispatchers.Main) {
            val response= withContext(Dispatchers.IO){client.api.getuser(id!!)}
            if(response.isSuccessful){
                response.body().let {
                    t1.text = it?.name
                    t2.text = it?.login
                    Picasso.get().load(it?.avatarUrl).into(imageView)

                }
            }
        }
    }
}