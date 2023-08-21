package com.jonrysimbolon.moviehiltmodular.presentation.detail

import androidx.fragment.app.viewModels
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentDetailBinding
import com.jonrysimbolon.moviehiltmodular.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate,
) {

    override val baseViewModel: DetailViewModel by viewModels()

    override fun setUpUi() {
        super.setUpUi()

        val movieId = arguments?.getString(Constant.MOVIE_ID)
        val movieTitle = arguments?.getString(Constant.MOVIE_TITLE)

        binding.apply {
            posterIv.transitionName = movieId
            //requireActivity().title = movieTitle
        }
    }

    override fun setUpVm() {
        super.setUpVm()

    }
}
