package com.uc.week4retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.week4retrofit.model.MovieDetails
import com.uc.week4retrofit.repository.NowPlayingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: NowPlayingRepository):ViewModel(){

    //Get Now PLaying Data
    val _nowPLaying: MutableLiveData<ArrayList<com.uc.week4retrofit.model.Result>> by lazy {
        MutableLiveData<ArrayList<com.uc.week4retrofit.model.Result>>()
    }
    val nowPlaying: LiveData<ArrayList<com.uc.week4retrofit.model.Result>>
    get() = _nowPLaying

    fun getNowPlaying(apiKey: String, language: String, page: Int)
    =viewModelScope.launch {
        repository.getNowPlayingResults(apiKey, language,page).let{
        response ->
        if(response.isSuccessful){
            _nowPLaying.postValue(response.body()?.results as ArrayList<com.uc.week4retrofit.model.Result>?)
        }else{
            Log.e("Get Data", "Failed")
        } }
    }
    //Get Movie Details

    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }
    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    fun getMovieDetailResults(apiKey: String,MovieId: Int  )
            =viewModelScope.launch {
        repository.getMovieDetailResults(apiKey, MovieId).let{
                response ->
            if(response.isSuccessful){
                _movieDetails.postValue(response.body() as MovieDetails)
            }else{
                Log.e("Get Movie Details Data", "Failed")
             }
    }}

}