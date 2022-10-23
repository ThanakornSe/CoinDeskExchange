package com.example.coindeskexchange.viewModel

import android.util.Log
import androidx.lifecycle.*
import com.example.coindeskexchange.data.local.EURPriceHistory
import com.example.coindeskexchange.data.local.GBPPriceHistory
import com.example.coindeskexchange.data.local.USDPriceHistory
import com.example.coindeskexchange.data.remote.*
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

    fun getUSDHistory():LiveData<List<USDPriceHistory>> = repository.getUSDHistoryRate().asLiveData()
    fun getEURHistory():LiveData<List<EURPriceHistory>> = repository.getEURHistoryRate().asLiveData()
    fun getGBPHistory():LiveData<List<GBPPriceHistory>> = repository.getGBPHistoryRate().asLiveData()

    fun recordUSDHistory(usd: USDPriceHistory) = viewModelScope.launch {
        repository.insertUSDRate(usd)
    }

    fun recordEURHistory(eur: EURPriceHistory) = viewModelScope.launch {
        repository.insertEURRate(eur)
    }

    fun recordGBPHistory(gbp: GBPPriceHistory) = viewModelScope.launch {
        repository.insertGBPRate(gbp)
    }
}