package com.sampam.networking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.useradap.view.*

class useradapter() : RecyclerView.Adapter<useradapter.userviewholder>() {
    var list: List<User> = ArrayList()
    var onitemclick: ((login: String) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userviewholder {
        return userviewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.useradap, parent, false)
        )
    }

    override fun onBindViewHolder(holder: userviewholder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun swapdata(data:List<User>){
        list=data
        notifyDataSetChanged()
    }


    inner class userviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun bind(userModel: User) {
            with(itemView) {
                t1.text = userModel.name
                t2.text = userModel.login
                Picasso.get().load(userModel.avatarUrl).into(imageView)
                setOnClickListener {
                    onitemclick?.invoke(userModel.login!!)

                }
            }
        }

    }
}

