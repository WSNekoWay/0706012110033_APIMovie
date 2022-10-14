package com.uc.week4retrofit.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.uc.week4retrofit.adapter.NowPlayingAdapter
import com.uc.week4retrofit.databinding.ActivityHomeBinding
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: NowPlayingAdapter
    private lateinit var viewModel: MoviesViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)
        viewModel.getNowPlaying(Const.API_KEY, "en-US", 1)

        viewModel.nowPlaying.observe(this, Observer { response->
            binding.rvMovie.layoutManager = GridLayoutManager(this, 2)
            adapter = NowPlayingAdapter(response)
            binding.rvMovie.adapter = adapter
        })
    }
}