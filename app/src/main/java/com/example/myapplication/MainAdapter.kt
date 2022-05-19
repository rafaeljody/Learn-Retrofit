package com.example.myapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MethodCallsLogger
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_main.view.*

class MainAdapter(val results : ArrayList<MainModel.Results>, val listener: OnAdapterListener)
    : RecyclerView.Adapter<MainAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate( R.layout.adapter_main, parent, false )

    )

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        val result = results[position]
        holder.view.tvNama.text = result.name.first
        holder.view.tvEmail.text = result.email
        Log.d("MainAdapter", "resultImage  ${result.picture.large} ")
        Glide.with( holder.view )
            .load(result.picture.large)
            .placeholder(R.drawable.img_placeholder) //memberikan placeholder
            .error(R.drawable.img_placeholder) //gambar pengganti jika eror atau corrupt
            .centerCrop()
            .into(holder.view.imageView)

        holder.view.setOnClickListener{
            listener.onClick( result ) // datanya ada di interface bawah
        }

    }

    override fun getItemCount(): Int = results.size

    class ViewHolder(val view : View) : RecyclerView.ViewHolder(view)

    fun setData(data: List<MainModel.Results>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun onClick( result: MainModel.Results )
    }

}