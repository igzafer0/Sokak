package com.igzafer.sokak.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.igzafer.sokak.data.model.Article
import com.igzafer.sokak.databinding.NewsRowBinding


class NewsAdapter : RecyclerView.Adapter<NewsAdapter.newsViewHolder>() {

    private val callback = object : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.description == newItem.description
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
     var article = ArrayList<Article>()
    val differ = AsyncListDiffer(this, callback)
    fun setList(articles: List<Article>) {
        article.addAll(articles)

        notifyDataSetChanged()

    }

    inner class newsViewHolder(val binding: NewsRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.newsTitleTw.text = article.title

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(16))

            binding.newsSourceTw.text = article.source?.name
            binding.newsPublishedTw.text = article.publishedAt?.substring(0, 10)
            Glide.with(binding.newsIm.context).load(article.urlToImage)
                .apply(requestOptions)
                .into(binding.newsIm)

            binding.root.setOnClickListener{
                onItemClickListener?.let {
                    it(article)
                }
            }
            binding.shareIm.setOnClickListener{
                onShareClickListener?.let {
                    it(article)
                }
            }
        }

    }

    private var onItemClickListener: ((Article) -> Unit)? = null
    private var onShareClickListener: ((Article) -> Unit)? = null
    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }
    fun setOnShareClickListener(listener: (Article) -> Unit){
        onShareClickListener=listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsViewHolder {
        val binding = NewsRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return newsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: newsViewHolder, position: Int) {
        val article = article[position]
        holder.bind(article)

    }

    override fun getItemCount(): Int {
        Log.d("winter",article.size.toString())
        return article.size
    }
}