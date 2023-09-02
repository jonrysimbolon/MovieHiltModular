package com.jonrysimbolon.moviehiltmodular.presentation.home

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jonrysimbolon.core.base.BaseViewModel
import com.jonrysimbolon.core.domain.movie.MovieUseCase
import com.jonrysimbolon.core.model.CategoryModel
import com.jonrysimbolon.core.model.MovieModel
import com.jonrysimbolon.core.utils.MovieDispatcher
import com.jonrysimbolon.core.utils.MovieDispatchers
import com.jonrysimbolon.core.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    @MovieDispatcher(MovieDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    /**
     * Can make those 3 variable to one custom data state
     */

    private val _idCategoryFlow = MutableStateFlow("0")
    val idCategoryFlow: StateFlow<String> get() = _idCategoryFlow

    private val _categoryFlow =
        MutableStateFlow<ResultStatus<List<CategoryModel>>>(ResultStatus.Loading)
    val categoryFlow: StateFlow<ResultStatus<List<CategoryModel>>> get() = _categoryFlow

    private val _movieFlowPagingData = MutableStateFlow<PagingData<MovieModel>>(PagingData.empty())
    val movieFlowPagingData: StateFlow<PagingData<MovieModel>> get() = _movieFlowPagingData

    init {
        fetchCategories()
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            movieUseCase.getCategories()
                .flowOn(ioDispatcher)
                .map { listCategories ->
                    val idFirst = listCategories
                        .first()
                        .id
                        .toString()
                    fetchMovies(idFirst)
                    listCategories
                }
                .flowOn(ioDispatcher)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { listCategories ->
                    _categoryFlow.value = ResultStatus.Success(listCategories)
                }
        }
    }

    fun fetchMovies(idCategory: String) {
        _idCategoryFlow.value = idCategory
        viewModelScope.launch {
            movieUseCase.getMovies(idCategory)
                .flowOn(ioDispatcher)
                .cachedIn(viewModelScope)
                .catch { e ->
                    e.printStackTrace()
                }
                .collect { data ->
                    _movieFlowPagingData.value = data
                }
        }
    }
}