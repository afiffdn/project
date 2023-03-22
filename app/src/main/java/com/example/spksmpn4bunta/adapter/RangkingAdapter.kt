package com.example.spksmpn4bunta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spksmpn4bunta.databinding.RangkingItemBinding
import com.example.spksmpn4bunta.model.rangking.GetNilaiSawRespItem

class RangkingAdapter(private val onItemClick: onClickListener) :
        RecyclerView.Adapter<RangkingAdapter.ViewHolder>(){
        private val diffCallBack = object : DiffUtil.ItemCallback<GetNilaiSawRespItem>(){
            override fun areItemsTheSame(
                oldItem: GetNilaiSawRespItem,
                newItem: GetNilaiSawRespItem
            ): Boolean = oldItem.users_id == newItem.users_id

            override fun areContentsTheSame(
                oldItem: GetNilaiSawRespItem,
                newItem: GetNilaiSawRespItem
            ): Boolean = oldItem.nama == newItem.nama
        }
    private val differ = AsyncListDiffer(this,diffCallBack)
    fun submitData(value: List<GetNilaiSawRespItem>?) = differ.submitList(value)

    interface onClickListener {
        fun onClickItem (data: GetNilaiSawRespItem)
    }
    inner class ViewHolder(private val binding: RangkingItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data:GetNilaiSawRespItem){
            binding.nama.text = data.nama
            binding.nilai.text = data.hasil
            binding.root.setOnClickListener {
                onItemClick.onClickItem(data)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RangkingAdapter.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(RangkingItemBinding.inflate(inflate,parent,false))
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }


        }