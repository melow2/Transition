package com.jeit.transition

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeit.transition.TestUrls.urls
import com.jeit.transition.databinding.ItemRecyclerBinding


class TestAdapter : RecyclerView.Adapter<TestAdapter.ViewHolder>() {

    private lateinit var callback:Callback

    interface Callback{
        fun onClick(ivTest: ImageView, transitionName: String)
    }

    fun addCallback(callback:Callback){
        this.callback = callback
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(urls[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_recycler, parent, false
            )
        )

    inner class ViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(imageUrl: String){
            Glide.with(binding.root)
                .load(imageUrl)
                .into(binding.ivTest)
            itemView.setOnClickListener { callback.onClick(binding.ivTest,"ivTest#${imageUrl}") }
        }
    }

    override fun getItemCount(): Int {
        return TestUrls.urls.size
    }

}