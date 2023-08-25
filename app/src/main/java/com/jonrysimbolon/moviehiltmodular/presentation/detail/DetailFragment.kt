package com.jonrysimbolon.moviehiltmodular.presentation.detail

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.utils.CustomNavDirection
import com.jonrysimbolon.core.utils.ResultStatus
import com.jonrysimbolon.core.utils.withDateSimpleFormat
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.DetailContentScrollingBinding
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentDetailBinding
import com.jonrysimbolon.moviehiltmodular.utils.Constant.MOVIE_ID
import com.jonrysimbolon.moviehiltmodular.utils.Constant.MOVIE_TITLE
import com.jonrysimbolon.moviehiltmodular.utils.setImageDefaultWithRadius
import com.jonrysimbolon.moviehiltmodular.utils.setImageUrlDefault
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>(
    FragmentDetailBinding::inflate,
) {

    override val baseViewModel: DetailViewModel by viewModels()
    private lateinit var detContScroll: DetailContentScrollingBinding
    private val mBundle: Bundle = Bundle()

    override fun setUpUi() {
        super.setUpUi()
        detContScroll = DetailContentScrollingBinding.bind(binding.detContScroll.root)

        val movieId = arguments?.getInt(MOVIE_ID) ?: 0
        val movieTitle = arguments?.getString(MOVIE_TITLE) ?: ""

        mBundle.putInt(MOVIE_ID, movieId)
        mBundle.putString(MOVIE_TITLE, movieTitle)

        baseViewModel.getDetailMovie(movieId)

        binding.apply {
            toolbar.apply {
                title = movieTitle
                setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                setOnMenuItemClickListener {
                    when (it.itemId) {
                        R.id.reviewMenu -> {
                            val detailToReviewNavDirection = CustomNavDirection(
                                R.id.action_nav_detail_to_nav_reviews,
                                mBundle
                            )
                            baseViewModel.navigate(detailToReviewNavDirection)
                            true
                        }

                        R.id.trailerMenu -> {
                            val detailToTrailerNavDirection = CustomNavDirection(
                                R.id.action_nav_detail_to_nav_reviews,
                                mBundle
                            )
                            baseViewModel.navigate(detailToTrailerNavDirection)
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
            posterIv.transitionName = movieId.toString()
        }
    }

    private fun populateData(data: DetailMovieModel) {
        binding.apply {
            setImageUrlDefault(
                data.backdropPath,
                ivItemPhoto
            )
        }

        detContScroll.apply {
            setImageDefaultWithRadius(
                data.posterPath,
                posterIv,
                radius = RADIUS
            )
            releaseDateTv.text = data.releaseDate.withDateSimpleFormat()
            runTimeTv.text = getString(R.string.run_time_value, data.runtime.toString())
            taglineTv.text = data.tagline
            overViewTv.text = data.overview
        }
    }

    override fun setUpVm() {
        super.setUpVm()
        baseViewModel.detailMovie
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { result ->
                when (result) {
                    ResultStatus.Loading -> {
                        loadingDialog.show(true)
                    }

                    is ResultStatus.Error -> {
                        loadingDialog.show(false)
                        failureDialog.show(true)
                    }

                    is ResultStatus.Success -> {
                        loadingDialog.show(false)
                        val data = result.data
                        populateData(data)
                    }
                }
            }
            .launchIn(lifecycleScope)
    }

    companion object {
        const val RADIUS = 12f
    }
}
