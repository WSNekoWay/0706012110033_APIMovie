package com.uc.week4retrofit.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4retrofit.adapter.MovieCompanyAdapter
import com.uc.week4retrofit.adapter.MovieDubAdapter
import com.uc.week4retrofit.adapter.MovieGenreAdapter
    import com.uc.week4retrofit.databinding.ActivityMovieDetailBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetail : AppCompatActivity() {
    private lateinit var Gadapter: MovieGenreAdapter
    private lateinit var Dadapter: MovieDubAdapter
    private lateinit var Cadapter: MovieCompanyAdapter

    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var ViewModel: MoviesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
        val movieId = intent.getIntExtra("movie_id",0)
        Toast.makeText(applicationContext, "movieId: ${movieId}", Toast.LENGTH_SHORT)

        ViewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        ViewModel.getMovieDetailResults(Const.API_KEY, movieId )



        ViewModel.movieDetails.observe(this,{
            response->
            binding.loadingBack.setVisibility(View.VISIBLE)
            binding.loadingBar.setVisibility(View.VISIBLE)


            binding.tvTitleMoviedetail.apply {
                text=response.title
            }

            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.backdrop_path)
                .into(binding.imgDropMoviedetail)
            Glide.with(applicationContext)
                .load(Const.IMG_URL + response.poster_path)
                .into(binding.imgPosterMoviedetail)
            binding.tvPopularity.apply{
                text = response.popularity.toInt().toString()

            }
            binding.tvRelease.apply {
                text=response.release_date
            }
            binding.tvOriginLang.apply {
                text=response.original_language
            }
            binding.tvStatus.apply {
                text=response.status
            }
            binding.tvDesc.apply {
                text=response.overview
            }
            binding.tvRate.apply{
                text=String.format("%.1f", response.vote_average)
            }
            binding.rvGenre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            Gadapter = MovieGenreAdapter(response.genres)
            binding.rvGenre.adapter = Gadapter

            binding.rvDub.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            Dadapter = MovieDubAdapter(response.spoken_languages)
            binding.rvDub.adapter = Dadapter

            binding.rvProduction.layoutManager= LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            Cadapter = MovieCompanyAdapter(response.production_companies)
            binding.rvProduction.adapter = Cadapter
            binding.loadingBack.setVisibility(View.INVISIBLE)
            binding.loadingBar.setVisibility(View.INVISIBLE)


        })


    }
}