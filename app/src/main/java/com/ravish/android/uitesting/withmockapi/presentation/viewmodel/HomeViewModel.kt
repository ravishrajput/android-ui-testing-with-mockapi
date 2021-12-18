package com.ravish.android.uitesting.withmockapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravish.android.uitesting.withmockapi.data.model.User
import com.ravish.android.uitesting.withmockapi.data.network.ApiRepository
import com.ravish.android.uitesting.withmockapi.presentation.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _userFlow = MutableStateFlow<UIState<List<User>>>(UIState.Loading())
    val userFlow: StateFlow<UIState<List<User>>>
        get() = _userFlow

    fun loadUserData() {
        viewModelScope.launch {
            apiRepository.getUser()
                .catch { exception ->
                    _userFlow.value = UIState.Error(exception.message)
                }
                .collect {
                    _userFlow.value = UIState.Success(it)
                }
        }
    }
}