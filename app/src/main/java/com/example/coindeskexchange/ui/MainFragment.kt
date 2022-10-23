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
import androidx.core.content.ContextCompat.registerReceiver
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.coindeskexchange.adapter.controller.MainFragmentEpoxyController
import com.example.coindeskexchange.data.remote.Bpi
import com.example.coindeskexchange.data.ui.Currency
import com.example.coindeskexchange.databinding.FragmentMainBinding
import com.example.coindeskexchange.resource.ViewState
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()
    private val epoxyController:MainFragmentEpoxyController by lazy {
        MainFragmentEpoxyController(::onCurrencyClick)
    }
    private lateinit var minuteUpdateReceiver:BroadcastReceiver

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchData()
        binding.epoxyRecView.setController(epoxyController)
    }

    private fun onCurrencyClick(currencyCode:String) {
        this.findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToCoinDetailFragment(currencyCode)
        )
    }

    private fun fetchData() {
        viewModel.getAllCoins()
        viewModel.coinData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is ViewState.Loading -> {

                }
                is ViewState.Success -> {
                    val result = response.data
                    result?.let {
                        val usd = it.bpi.uSD
                        val gbp = it.bpi.gBP
                        val eur = it.bpi.eUR
                        val pairList = arrayListOf(
                            Pair(
                                "USD",
                                Currency(
                                    usd.code,
                                    usd.description,
                                    usd.rate,
                                    usd.rateFloat,
                                    usd.symbol
                                )
                            ),
                            Pair(
                                "GBP",
                                Currency(
                                    gbp.code,
                                    gbp.description,
                                    gbp.rate,
                                    gbp.rateFloat,
                                    gbp.symbol
                                )
                            ),
                            Pair(
                                "EUR",
                                Currency(
                                    eur.code,
                                    eur.description,
                                    eur.rate,
                                    eur.rateFloat,
                                    eur.symbol
                                )
                            )
                        )
                        epoxyController.updateTime = convertDateFormat(it.time.updated)
                        epoxyController.disClaimer = it.disclaimer
                        epoxyController.currencyList = pairList
                    }
                }
                is ViewState.Error -> {

                }
            }
        }
    }

    private fun convertDateFormat(updateTime: String): String {
        val df = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = df.parse(updateTime) as Date
        val newDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH)
        newDateFormat.timeZone = TimeZone.getDefault()
        return newDateFormat.format(date)
    }

    private fun startMinuteUpdater() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        minuteUpdateReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                fetchData()
                epoxyController.requestModelBuild()
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