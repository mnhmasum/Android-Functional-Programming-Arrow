package com.artifactslab.newspan.features.main

import com.artifactslab.newspan.dto.ArticlesItem


interface MainActivityView {
    fun showMessage(string: String)
    fun showLoading()
    fun hideLoading()
    fun showList(list: List<ArticlesItem>)
}
