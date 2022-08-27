package com.puneet.contextualcards.network

import com.puneet.contextualcards.model.DataResponse
import retrofit2.http.GET

interface NetworkApi {
    @GET("v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad")
    suspend fun getData(): DataResponse
}
