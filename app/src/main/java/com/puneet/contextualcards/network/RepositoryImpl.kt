package com.puneet.contextualcards.network

import com.puneet.contextualcards.model.DataResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkApi: NetworkApi
) : Repository {
    override suspend fun getData(): Result<DataResponse> {
        return try {
            val response = networkApi.getData()
            Result.success(response)
        } catch (e: Exception) {
            val errorMessage = getErrorMessage(e)
            Result.failure(Exception(errorMessage))
        }
    }

    private fun getErrorMessage(exception: Exception): String {
        return when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    401 -> "You are not logged in. Please log in."
                    404 -> "Server is down. Please try again later!"
                    else -> "Something went wrong. Please try again later!"
                }
            }
            is IOException -> {
                "Please connect to the internet"
            }
            else -> {
                "Something went wrong. Please try again!"
            }
        }
    }
}
