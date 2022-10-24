package com.example.coindeskexchange.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.coindeskexchange.adapter.controller.MainFragmentEpoxyController
import com.example.coindeskexchange.data.local.PriceHistory
import com.example.coindeskexchange.data.remote.Coins
import com.example.coindeskexchange.data.ui.Currency
import com.example.coindeskexchange.databinding.FragmentMainBinding
import com.example.coindeskexchange.resource.ViewState
import com.example.coindeskexchange.utils.AppConst.convertDateFormat
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()
    private val epoxyController: MainFragmentEpoxyController by lazy {
        MainFragmentEpoxyController(::onCurrencyClick)
    }
    private lateinit var minuteUpdateReceiver: BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCoins()
        observeLiveData()
        binding.epoxyRecView.setController(epoxyController)
    }

    private fun onCurrencyClick(currencyCode: String) {
        this.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToCoinDetailFragment(currencyCode)
        )
    }

    private fun observeLiveData() {
        viewModel.coinData.observe(viewLifecycleOwner) { response ->
            if ( response is ViewState.Success) {
                val result = response.data
                result?.let {
                    val pairList = arrayListOf(
                        Pair(
                            it.bpi.uSD.code,
                            Currency(
                                it.bpi.uSD.code,
                                it.bpi.uSD.description,
                                it.bpi.uSD.rate,
                                it.bpi.uSD.rateFloat,
                                it.bpi.uSD.symbol
                            )
                        ),
                        Pair(
                            it.bpi.gBP.code,
                            Currency(
                                it.bpi.gBP.code,
                                it.bpi.gBP.description,
                                it.bpi.gBP.rate,
                                it.bpi.gBP.rateFloat,
                                it.bpi.gBP.symbol
                            )
                        ),
                        Pair(
                            it.bpi.eUR.code,
                            Currency(
                                it.bpi.eUR.code,
                                it.bpi.eUR.description,
                                it.bpi.eUR.rate,
                                it.bpi.eUR.rateFloat,
                                it.bpi.eUR.symbol
                            )
                        )
                    )
                    epoxyController.updateTime = convertDateFormat(it.time.updated)
                    epoxyController.disClaimer = it.disclaimer
                    epoxyController.currencyList = pairList

                    viewModel.recordPriceHistory(
                        PriceHistory(
                            convertDateFormat(it.time.updated),
                            RateUSD = it.bpi.uSD.rate,
                            RateEUR = it.bpi.eUR.rate,
                            RateGBP = it.bpi.gBP.rate
                        )
                    )
                }
            }
        }
    }

    private fun startMinuteUpdater() {
        val intentFilter = IntentFilter(Intent.ACTION_TIME_TICK)
        minuteUpdateReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                viewModel.getAllCoins()
            }
        }
        requireActivity().registerReceiver(minuteUpdateReceiver, intentFilter)
    }

    override fun onResume() {
        super.onResume()
        startMinuteUpdater()
    }

    override fun onPause() {
        super.onPause()
        requireActivity().unregisterReceiver(minuteUpdateReceiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
