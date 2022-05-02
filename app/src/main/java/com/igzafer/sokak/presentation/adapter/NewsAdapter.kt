package com.igzafer.sokak.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.igzafer.sokak.data.model.Article
import com.igzafer.sokak.databinding.NewsRowBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.newsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
    val differ = AsyncListDiffer(this, callback)

    inner class newsViewHolder(val binding: NewsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source.name
            Glide.with(binding.ivArticleImage.context).load(article.urlToImage)
                .into(binding.ivArticleImage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val binding=NewsRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return newsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}