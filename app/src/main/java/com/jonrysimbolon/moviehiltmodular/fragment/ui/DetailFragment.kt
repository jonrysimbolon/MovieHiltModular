package com.jonrysimbolon.moviehiltmodular.fragment.ui

import android.os.Bundle
import android.view.View
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentDetailBinding
import com.jonrysimbolon.moviehiltmodular.fragment.viewmodel.DetailViewModel

class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    override fun getViewModel() = DetailViewModel()

    override fun FragmentDetailBinding.initialize() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}