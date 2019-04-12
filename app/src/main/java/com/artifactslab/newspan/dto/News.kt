package com.artifactslab.newspan.dto

import com.google.gson.annotations.SerializedName

data class News(@SerializedName("totalResults")
                          val totalResults: Int = 0,
                @SerializedName("articles")
                          val articles: List<ArticlesItem>?,
                @SerializedName("status")
                          val status: String = "")