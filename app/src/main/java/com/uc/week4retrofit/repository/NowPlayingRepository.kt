package com.uc.week4retrofit.repository


import com.uc.week4retrofit.retrofit.EndPointAPI
import javax.inject.Inject

class NowPlayingRepository @Inject constructor(private val api: EndPointAPI){

    suspend fun  getNowPlayingResults
                (
        apiKey: String,
        language: String,
        page:Int
    ) = api.getNowPlaying(apiKey, language,page)

    suspend fun  getMovieDetailResults
                (
        apiKey: String,
        MovieId: Int
    ) = api.getMovieDetails(MovieId,apiKey)


}