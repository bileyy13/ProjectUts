package com.uts.projectuts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uts.projectuts.databinding.ItemProductBinding
import com.uts.projectuts.model.Product

class ProductAdapter(
    private val list: List<Product>,
    private val onClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(val b: ItemProductBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val b = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(b)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = list[position]
        holder.b.tvTitle.text = p.title
        holder.b.tvPrice.text = "Harga: $${p.price}"
        holder.b.tvStock.text = "Stok: ${p.stock}"
        holder.b.tvRating.text = "Rating: ${p.rating}"
        Glide.with(holder.itemView).load(p.thumbnail).into(holder.b.imgThumbnail)
        holder.itemView.setOnClickListener { onClick(p) }
    }

    override fun getItemCount() = list.size
}