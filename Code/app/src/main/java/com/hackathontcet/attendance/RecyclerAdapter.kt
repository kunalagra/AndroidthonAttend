package com.hackathontcet.attendance

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(val subjectList : ArrayList<Subjects>, val clickListener:  ClickListener) : RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>(){

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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
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
        var titleTextView : TextView = view.findViewById(R.id.subject_name)
        var imageView : ImageView = view.findViewById(R.id.subject_icon)
    }

    interface ClickListener {
        fun onItemClick(subjects: String)
    }
}


class AboutAdapter(val dnameList : ArrayList<Subjects>, val clickListener:  ClickListener) : RecyclerView.Adapter<AboutAdapter.MyViewHolder>(){

    @SuppressLint("NotifyDataSetChanged")
    fun deleteItem(i: Int){
        /* It deletes the particular item in the list */
        dnameList.removeAt(i)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addItem(i: Int, subjects: Subjects){
        /* It add the particular item in the list */
        dnameList.add(i,subjects)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.namelist,parent,false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        /* Setting the Image and Name/Title of the item in the list */
        val currentItem = dnameList[position]
        holder.titleTextView.text = currentItem.subjectName
        holder.imageView.setImageResource(currentItem.subjectImage)
        holder.itemView.setOnClickListener{
            clickListener.onItemClick(currentItem.subjectName)
        }
    }

    override fun getItemCount(): Int {
        return dnameList.size
    }

    class MyViewHolder(view : View): RecyclerView.ViewHolder(view){
        /* Declaring the variables for the ImageView and TextView for the items in the list */
        var titleTextView : TextView = view.findViewById(R.id.name_data)
        var imageView : ImageView = view.findViewById(R.id.icon_data)
    }

    interface ClickListener {
        fun onItemClick(subjects: String)
    }
}


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
        val textname: TextView = itemView.findViewById(R.id.name)
        val rid: TextView = itemView.findViewById(R.id.rollno)
        val absent: TextView = itemView.findViewById(R.id.absent)

    }
}


