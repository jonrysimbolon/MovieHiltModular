package com.jonrysimbolon.feature_reviews

import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.feature_reviews.databinding.FragmentReviewsBinding

class ReviewsFragment : BaseFragment<FragmentReviewsBinding>(FragmentReviewsBinding::inflate) {
    override fun FragmentReviewsBinding.initialize() {}
    override fun getViewModel() = ReviewsViewModel()

}