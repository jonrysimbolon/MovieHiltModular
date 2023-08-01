package com.jonrysimbolon.moviehiltmodular.fragment.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentHomeBinding
import com.jonrysimbolon.moviehiltmodular.fragment.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
) {
    override fun FragmentHomeBinding.initialize() {}

    override val baseViewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.showToast(R.string.movies)
        val toDetailDirection = HomeFragmentDirections.actionNavHomeToNavDetail()
        val toReviewsDirection = HomeFragmentDirections.actionNavHomeToNavReviews()

        binding.detail.setOnClickListener {
            baseViewModel.navigate(toDetailDirection)
        }

        binding.reviews.setOnClickListener {
            baseViewModel.navigate(toReviewsDirection)
        }
    }
}