package com.jonrysimbolon.moviehiltmodular.fragment.ui

import android.os.Bundle
import android.view.View
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentHomeBinding
import com.jonrysimbolon.moviehiltmodular.fragment.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    viewModelClass = HomeViewModel::class.java
) {
    override fun FragmentHomeBinding.initialize() {}

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