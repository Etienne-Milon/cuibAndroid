package fr.em.cuibandroid.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import fr.em.cuibandroid.model.Article
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://cuib.sackebandt.fr/api"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CuibApiService{
    @GET("articles")
    suspend fun getArticles(): List<Article>

//    @GET("exemplaire")
//    suspend fun getExemplaires(): List<Exemplaire>
}

object CuibApi{
    val retrofitService: CuibApiService by lazy { retrofit.create(CuibApiService::class.java) }
}