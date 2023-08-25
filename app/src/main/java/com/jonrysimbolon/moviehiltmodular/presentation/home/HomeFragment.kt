package com.jonrysimbolon.moviehiltmodular.presentation.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.paging.LoadState
import com.google.android.material.chip.Chip
import com.jonrysimbolon.core.base.BaseFragment
import com.jonrysimbolon.core.model.CategoryModel
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.utils.CustomNavDirection
import com.jonrysimbolon.core.utils.ResultStatus
import com.jonrysimbolon.moviehiltmodular.R
import com.jonrysimbolon.moviehiltmodular.databinding.FragmentHomeBinding
import com.jonrysimbolon.moviehiltmodular.databinding.ItemCategoryBinding
import com.jonrysimbolon.moviehiltmodular.presentation.adapter.FooterLoadingStateAdapter
import com.jonrysimbolon.moviehiltmodular.presentation.adapter.MovieAdapter
import com.jonrysimbolon.moviehiltmodular.utils.Constant
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
) {

    override val baseViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieAdapter

    private lateinit var categoryBinding: ItemCategoryBinding

    private var chosenIdCategory: Int = 0

    override fun setUpUi() {
        super.setUpUi()
        categoryBinding = ItemCategoryBinding.bind(binding.categoryInc.root)
        binding.apply {
            movieAdapter.onClickItem = ::onClickItem
            movieAdapter.addLoadStateListener { loadState ->
                val mediatorLoadState =
                    loadState.mediator?.refresh
                when (mediatorLoadState) {
                    is LoadState.NotLoading -> {
                        loadingDialog.show(false)
                    }

                    is LoadState.Loading -> {
                        loadingDialog.show(true)
                    }

                    is LoadState.Error -> {
                        loadingDialog.show(false)
                        failureDialog.apply {
                            show(true)
                            setDescription(mediatorLoadState.error.message.toString())
                            setReloadClickListener {
                                movieAdapter.refresh()
                            }
                        }
                    }

                    else -> loadingDialog.show(false)
                }
            }

            movieRv.adapter = movieAdapter.withLoadStateFooter(
                footer = FooterLoadingStateAdapter {
                    movieAdapter.retry()
                }
            )
        }
    }

    override fun setUpVm() {
        super.setUpVm()

        baseViewModel.categoryFlow
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

        baseViewModel.movieFlowPagingData
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { result ->
                result.let {
                    movieAdapter.submitData(lifecycle, it)
                }
            }
            .launchIn(lifecycleScope)

        baseViewModel.idCategoryFlow
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .map { it.toInt() }
            .onEach { result ->
                chosenIdCategory = result
            }
            .launchIn(lifecycleScope)
    }

    private fun populateData(listCategories: List<CategoryModel>) {
        listCategories.forEach { data ->
            Chip(requireContext()).apply {
                id = data.id
                text = data.name
                isCheckable = true
            }.also { chip ->
                categoryBinding.chipGroup.addView(chip)
            }
        }

        categoryBinding.chipGroup.apply {
            check(chosenIdCategory)
            setOnCheckedStateChangeListener { _, checkedIds ->
                checkedIds
                    .map {
                        it.toString()
                    }
                    .forEach { id ->
                        baseViewModel.fetchMovies(id)
                    }
            }
        }
    }

    private fun onClickItem(viewHolder: MovieAdapter.ViewHolder, model: MovieModel) {
        val idMovie = model.id
        val idMovieStr = model.id.toString()
        val titleMovie = model.title

        val ivItemPhoto = viewHolder
            .binding
            .ivItemPhoto

        ivItemPhoto.transitionName = idMovieStr

        val bundle = Bundle()

        bundle.apply {
            putInt(Constant.MOVIE_ID, idMovie)
            putString(Constant.MOVIE_TITLE, titleMovie)
        }

        val homeToDetailNavDirection = CustomNavDirection(
            R.id.action_nav_home_to_nav_detail,
            bundle
        )

        fragmentExtras = FragmentNavigatorExtras(
            ivItemPhoto to idMovieStr
        )

        baseViewModel.navigate(homeToDetailNavDirection)
    }

}

