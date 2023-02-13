package fr.em.cuibandroid.model

import com.squareup.moshi.Json
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val ean13: String,
    val titre: String,
    val genre: Int,
    @SerialName("article_src") val articleSrcUrl: String
)