package com.example.coindeskexchange.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.repository.CoinsRepository
import com.example.coindeskexchange.resource.ViewState
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(private val repository: CoinsRepository):ViewModel() {
    private val _mCoinData = MutableLiveData<ViewState<Coins>>()
    val coinData:LiveData<ViewState<Coins>> get() = _mCoinData


    fun getAllCoins() = viewModelScope.launch {
        _mCoinData.postValue(ViewState.Loading())
        try {
            val response = repository.getAllCoin()
            _mCoinData.postValue(ViewState.Success(response))
        }catch (e:Exception){
            _mCoinData.postValue(ViewState.Error(e.message))
        }
    }

}