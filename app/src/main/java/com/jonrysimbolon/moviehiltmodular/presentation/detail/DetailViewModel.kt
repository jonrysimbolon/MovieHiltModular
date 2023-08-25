package com.jonrysimbolon.moviehiltmodular.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jonrysimbolon.core.base.BaseViewModel
import com.jonrysimbolon.core.domain.detail.DetailMovieUseCase
import com.jonrysimbolon.core.model.DetailMovieModel
import com.jonrysimbolon.core.utils.Event
import com.jonrysimbolon.core.utils.MovieDispatcher
import com.jonrysimbolon.core.utils.MovieDispatchers
import com.jonrysimbolon.core.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailMovieUseCase: DetailMovieUseCase,
    @MovieDispatcher(MovieDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
) : BaseViewModel() {

    private val _detailMovie = MutableStateFlow<ResultStatus<DetailMovieModel>>(ResultStatus.Loading)
    val detailMovie: StateFlow<ResultStatus<DetailMovieModel>> get() = _detailMovie

    fun getDetailMovie(movieId: Int){
        viewModelScope.launch {
            detailMovieUseCase
                .detailMovie(movieId)
                .flowOn(ioDispatcher)
                .catch { error ->
                    error.printStackTrace()
                    _detailMovie.value = ResultStatus.Error(error.message.toString())
                }
                .collect{ detailMovie ->
                    _detailMovie.value = detailMovie
                }
        }
    }
}