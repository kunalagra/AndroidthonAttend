package com.hackathontcet.attendance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class RecyclerAdapter(private val subjectList : ArrayList<Subjects>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.subjects,parent,false)
        return ViewHolder(itemView,mListener)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {

        val currentItem = subjectList[position]
        holder.subjectImage.setImageResource(currentItem.subjectImage)
        holder.subjectName.text = currentItem.subjectName
    }

    override fun getItemCount(): Int {
        return subjectList.size
    }

    inner class ViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {

        val subjectImage : ShapeableImageView = itemView.findViewById(R.id.subject_icon)
        val subjectName : TextView = itemView.findViewById(R.id.subject_name)

        init{
            itemView.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }
}
