package com.example.spksmpn4bunta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.spksmpn4bunta.databinding.AktivasiItemBinding
import com.example.spksmpn4bunta.model.aktivasi.GetUsersRespItem

class ActivatedAdapater(private val onItemClick: onClickListener) :
    RecyclerView.Adapter<ActivatedAdapater.ViewHolder>() {
    private val diffCallBack = object : DiffUtil.ItemCallback<GetUsersRespItem>() {
        override fun areItemsTheSame(oldItem: GetUsersRespItem, newItem: GetUsersRespItem): Boolean =
            oldItem.id_users == newItem.id_users
        override fun areContentsTheSame(oldItem: GetUsersRespItem, newItem: GetUsersRespItem): Boolean =
            oldItem.username == newItem.username

    }
    private val differ = AsyncListDiffer(this,diffCallBack)
    fun submitData(value: List<GetUsersRespItem>?) {
        value?.let {
            val sortedList = it.sortedByDescending { it.id_users }
            differ.submitList(sortedList.toList())
            notifyDataSetChanged()
        }
    }

    interface onClickListener {
        fun onClickItem (data: GetUsersRespItem)
    }
    inner class ViewHolder(private val binding: AktivasiItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (data: GetUsersRespItem){
            binding.userId.text = data.id_users
            binding.usersName.text = data.username
            binding.isActivated.text = data.is_active
            binding.root.setOnClickListener {
                onItemClick.onClickItem(data)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivatedAdapater.ViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ViewHolder(AktivasiItemBinding.inflate(inflate,parent,false))
    }
    override fun onBindViewHolder(holder: ActivatedAdapater.ViewHolder, position: Int) {
        val data = differ.currentList[position]
        data.let {
            holder.bind(data)
        }
    }
    override fun getItemCount(): Int {
        return differ.currentList.size
    }



}