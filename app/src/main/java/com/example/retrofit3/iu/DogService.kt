package com.example.retrofit3.iu


import com.example.retrofit3.DogsResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



interface DogService {
    @GET("images/search")
    suspend fun getDogsByBreeds(
        @Query("limit") limit:Int = 5
    ):List<DogsResponse>
}

object RetrofitServiceFactory{
    fun makeRetrofitService(): DogService {
        return Retrofit.Builder()
            .baseUrl("https://api.thedogapi.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogService::class.java)
    }
}