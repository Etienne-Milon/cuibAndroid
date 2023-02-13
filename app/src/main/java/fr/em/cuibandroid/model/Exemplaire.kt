package fr.em.cuibandroid.model

import com.squareup.moshi.Json

data class Exemplaire(
    val id: String,
    @Json(name = "exemplaire_src") val exemplaireSrcUrl: String
)