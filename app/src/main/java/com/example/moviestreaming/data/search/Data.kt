package com.example.moviestreaming.data.search

import com.example.moviestreaming.data.Item
import java.io.Serializable

data class Data(
    val APP_DOMAIN_CDN_IMAGE: String,
    val APP_DOMAIN_FRONTEND: String,
    val breadCrumb: List<BreadCrumb>,
    val items: List<Item>,
    val params: Params,
    val seoOnPage: SeoOnPage,
    val titlePage: String,
    val type_list: String
): Serializable