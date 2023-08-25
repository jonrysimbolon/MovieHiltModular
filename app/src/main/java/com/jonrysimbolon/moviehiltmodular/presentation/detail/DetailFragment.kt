package com.jonrysimbolon.moviehiltmodular.presentation.detail

import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.DetailContentScrollingBinding
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentDetailBinding
import com.jonrysimbolon.moviehiltmodular.utils.Constant
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate,
) {

    override val baseViewModel: DetailViewModel by viewModels()
    private lateinit var detContScroll: DetailContentScrollingBinding

    override fun setUpUi() {
        super.setUpUi()
        detContScroll = DetailContentScrollingBinding.bind(binding.detContScroll.root)

        val movieId = arguments?.getString(Constant.MOVIE_ID)
        val movieTitle = arguments?.getString(Constant.MOVIE_TITLE)

        binding.apply {
            toolbar.apply {
                title = movieTitle
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.reviewMenu -> {
                            baseViewModel.showToast(getString(R.string.review_label))
                            true
                        }

                        R.id.trailerMenu -> {
                            baseViewModel.showToast(getString(R.string.trailer_label))
                            true
                        }

                        else -> {
                            false
                        }
                    }
                }
            }
        }

        detContScroll.apply {
            posterIv.transitionName = movieId
        }

    }

    override fun setUpVm() {
        super.setUpVm()

        binding.apply {

        }

        detContScroll.apply {

        }
    }
}
