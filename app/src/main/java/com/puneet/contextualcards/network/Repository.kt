package com.puneet.contextualcards.network

import com.puneet.contextualcards.model.DataResponse

interface Repository {
    suspend fun getData(): Result<DataResponse>
}
