package com.artifactslab.newspan.features.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.artifactslab.helloarrow.R
import com.artifactslab.newspan.dto.ArticlesItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_news.view.*

class MainAdapter(
        private val characters: List<ArticlesItem>,
        private val itemClick: (ArticlesItem) -> Unit) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {
        holder.show(characters[pos])
    }

    override fun getItemCount() = characters.size

    class ViewHolder(val view: View, private val itemClick: (ArticlesItem) -> Unit) : RecyclerView.ViewHolder(view) {
        fun show(article: ArticlesItem) {
            Glide
                    .with(view.context)
                    .load(article.urlToImage)
                    .centerCrop()
                    .placeholder(R.drawable.ic_thumbnails)
                    .into(view.imageView)

            view.setOnClickListener { itemClick(article) }
            view.textTitle.text = article.title
            view.textPublishedAt.text = article.publishedAt
        }
    }
}
