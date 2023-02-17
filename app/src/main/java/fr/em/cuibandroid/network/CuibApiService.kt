package fr.em.cuibandroid.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

//private const val BASE_URL = "https://cuib.sackebandt.fr/api/"
private const val BASE_URL = "cuib.localhost/api/"
private const val BASE_URL2 = ""
private const val CUIB = "articles"
private const val articleSample = "articles/5007614480792"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CuibApiService{
    @GET(CUIB)
    suspend fun getArticles(): List<Article>
}

object CuibApi{
    val retrofitService: CuibApiService by lazy {
        retrofit.create(CuibApiService::class.java) }
}