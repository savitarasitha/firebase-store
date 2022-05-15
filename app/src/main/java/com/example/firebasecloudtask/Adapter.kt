package com.example.firebasecloudtask

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebasecloudtask.databinding.ListViewDesignBinding
import java.util.ArrayList

class Adapter(val list: ArrayList<User>, val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    var binding: ListViewDesignBinding? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ListViewDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        binding!!.tvFirstName.setText(list[position].firstName)
        binding!!.tvLastName.setText(list[position].lastName)

        holder.setIsRecyclable(false)
    }


    override fun getItemCount(): Int {
        return list.size
    }

    class ViewHolder @SuppressLint("NotifyDataSetChanged")
    constructor(itemView: ListViewDesignBinding) :
        RecyclerView.ViewHolder(itemView.getRoot()), View.OnClickListener {
        override fun onClick(v: View) {}

    }


}

