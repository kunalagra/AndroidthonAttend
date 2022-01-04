package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class RecyclerAdapter(val subjectList : ArrayList<Subjects>, val clickListener: ClickListener) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(i: Int){
        /* It deletes the particular item in the list */
        subjectList.removeAt(i)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(i: Int, subjects: Subjects){
        /* It add the particular item in the list */
        subjectList.add(i,subjects)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.subjects,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        /* Setting the Image and Name/Title of the item in the list */
        val currentItem = subjectList[position]
        holder.titleTextView.text = currentItem.subjectName
        holder.imageView.setImageResource(currentItem.subjectImage)
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(currentItem.subjectName)
        }
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        /* Declaring the variables for the ImageView and TextView for the items in the list */
        var titleTextView : TextView
        var imageView : ImageView = view.findViewById(R.id.subject_icon)
        init {
            titleTextView = view.findViewById(R.id.subject_name)
        }
    }

    interface ClickListener {
        fun onItemClick(subjects: String)
    }
}
