package com.artifactslab.newspan.features.details

import com.artifactslab.newspan.dto.ArticlesItem


interface DetailsActivityView {
    fun showMessage(string: String)
    fun showLoading()
    fun hideLoading()
    fun showList(list: List<ArticlesItem>)
}
