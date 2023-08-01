package com.jonrysimbolon.moviehiltmodular.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate,
    viewModelClass = DetailViewModel::class.java
) {
    override fun FragmentDetailBinding.initialize() {}
    override val baseViewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseViewModel.showToast(R.string.detailmovie)

        val toReviewsDirection = DetailFragmentDirections.actionNavDetailToNavReviews()

        binding.reviews.setOnClickListener {
            baseViewModel.navigate(toReviewsDirection)
        }
    }
}