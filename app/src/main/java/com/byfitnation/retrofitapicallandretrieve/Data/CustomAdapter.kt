package com.byfitnation.retrofitapicallandretrieve.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.byfitnation.retrofitapicallandretrieve.R

class CustomAdapter(private  val mList : List<MyDataItem>):RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items_list_recycler_view, parent ,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val MyDataItem = mList[position]

        holder.userId.text = MyDataItem.userId.toString()
        holder.id.text = MyDataItem.id.toString()
        holder.title.text = MyDataItem.title
        holder.body.text = MyDataItem.body
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView : View) : RecyclerView.ViewHolder(ItemView){

        val userId : TextView = itemView.findViewById(R.id.tvUserId)
        val id : TextView = itemView.findViewById(R.id.tvId)
        val title : TextView = itemView.findViewById(R.id.tvTitle)
        val body : TextView = itemView.findViewById(R.id.tvBody)
    }
}