package com.hackathontcet.attendance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.NameList

class MyAdapter(private val NameList : ArrayList<Name>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.data_list, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentUser = NameList[position]

        holder.textname.text = currentUser.name
        holder.rid.text = currentUser.rid.toString()
        holder.absent.text = currentUser.absent

    }

    override fun getItemCount(): Int {
        return NameList.size

    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textname = itemView.findViewById<TextView>(R.id.name)
        val rid = itemView.findViewById<TextView>(R.id.rollno)
        val absent = itemView.findViewById<TextView>(R.id.absent)

    }
}

