package com.example.potascan.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.potascan.data.remote.article.DataItem
import com.example.potascan.databinding.ItemListArticleBinding

class ArticleAdapter (private val listArticle: ArrayList<DataItem>) : RecyclerView.Adapter<ArticleAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: DataItem    )
    }

    class ListViewHolder(val binding: ItemListArticleBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemListArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun getItemCount(): Int = listArticle.size

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        Glide.with(viewHolder.itemView.context)
            .load(listArticle[position].imageUrlArticle)
            .into(viewHolder.binding.ivArticle)
        viewHolder.binding.tvTitleCard.text = listArticle[position].title
        viewHolder.binding.tvDescriptionCard.text = listArticle[position].mainContent

        viewHolder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listArticle[position])
        }

    }

}
