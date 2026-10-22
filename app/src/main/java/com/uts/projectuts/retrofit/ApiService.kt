package com.uts.projectuts.retrofit

import com.uts.projectuts.response.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("products")
    suspend fun getProducts(
        @Query("limit") limit: Int = 25,
        @Query("select") select: String =
            "id,title,description,category,price,stock,availabilityStatus,tags,dimensions,reviews,rating,images,thumbnail"
    ): ProductResponse
}