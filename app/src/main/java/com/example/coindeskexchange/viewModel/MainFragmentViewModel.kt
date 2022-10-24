package com.example.coindeskexchange.viewModel

import androidx.lifecycle.*
import com.example.coindeskexchange.data.local.PriceHistory
import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.data.remote.EUR
import com.example.coindeskexchange.data.remote.GBP
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.repository.CoinsRepository
import com.example.coindeskexchange.resource.ViewState
import kotlinx.coroutines.launch

class MainFragmentViewModel(private val repository: CoinsRepository):ViewModel() {
    private val _mCoinData = MutableLiveData<ViewState<Coins>>()
    val coinData:LiveData<ViewState<Coins>> get() = _mCoinData

    private val _mCurrencyUSD = MutableLiveData<ViewState<USD>>()
    val currencyUSD:LiveData<ViewState<USD>> get() = _mCurrencyUSD

    private val _mCurrencyEUR = MutableLiveData<ViewState<EUR>>()
    val currencyEUR:LiveData<ViewState<EUR>> get() = _mCurrencyEUR

    private val _mCurrencyGBP = MutableLiveData<ViewState<GBP>>()
    val currencyGBP:LiveData<ViewState<GBP>> get() = _mCurrencyGBP

    fun getAllCoins() = viewModelScope.launch {
        _mCoinData.postValue(ViewState.Loading())
        try {
            val response = repository.getAllCoin()
            _mCoinData.postValue(ViewState.Success(response))
            _mCurrencyUSD.postValue(ViewState.Success(response.bpi.uSD))
            _mCurrencyEUR.postValue(ViewState.Success(response.bpi.eUR))
            _mCurrencyGBP.postValue(ViewState.Success(response.bpi.gBP))
        }catch (e:Exception){
            _mCoinData.postValue(ViewState.Error(e.message))
        }
    }

    fun getUSDHistory():LiveData<List<PriceHistory>> = repository.getUSDHistoryRate().asLiveData()
    fun getEURHistory():LiveData<List<PriceHistory>> = repository.getEURHistoryRate().asLiveData()
    fun getGBPHistory():LiveData<List<PriceHistory>> = repository.getGBPHistoryRate().asLiveData()

    fun recordPriceHistory(priceHistory: PriceHistory) = viewModelScope.launch {
        repository.insertPriceHistoryRate(priceHistory)
    }

    fun deleteHistory() = viewModelScope.launch {
        repository.deleteHistory()
    }
}