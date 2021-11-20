package com.speerthomas.fetchrewardscodingexercise.networking

import com.hoonstudio.fetchrewardscodingexercise.Constants
import com.hoonstudio.fetchrewardscodingexercise.model.FetchRewardsItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FetchRewardsApi {

    @GET("hiring.json")
    suspend fun fetchItems(): List<FetchRewardsItem>

    companion object {
        operator fun invoke(

        ): FetchRewardsAPI{

            val okHttpClient = OkHttpClient.Builder().build()

            return Retrofit.
                    Builder()
                .client(okHttpClient)
                .baseUrl(Constants.FETCH_REWARDS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FetchRewardsAPI::class.java)

        }
    }
}