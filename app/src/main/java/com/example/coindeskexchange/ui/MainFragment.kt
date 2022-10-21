package com.example.coindeskexchange.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.coindeskexchange.adapter.controller.MainFragmentEpoxyController
import com.example.coindeskexchange.data.local.Currency
import com.example.coindeskexchange.databinding.FragmentMainBinding
import com.example.coindeskexchange.resource.ViewState
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()
    private val epoxyController:MainFragmentEpoxyController by lazy {
        MainFragmentEpoxyController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        Log.d("GGGG", "onCreateView: ")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                        val pairList = arrayListOf(Pair("USD", Currency(usd.code, usd.description, usd.rate, usd.rateFloat, usd.symbol)),
                            Pair("GBP", Currency(gbp.code, gbp.description, gbp.rate, gbp.rateFloat, gbp.symbol)),
                            Pair("EUR", Currency(eur.code, eur.description, eur.rate, eur.rateFloat, eur.symbol))
                        )
                        epoxyController.currencyList = pairList
                        epoxyController.updateTime = convertDateFormat(it.time.updated)
                        epoxyController.disClaimer = it.disclaimer
                    }
                }
                is ViewState.Error -> {

                }
            }
        }
        binding.epoxyRecView.setController(epoxyController)
    }

    private fun convertDateFormat(updateTime: String): String {
        val df = SimpleDateFormat("MMM dd, yyyy HH:mm:ss", Locale.ENGLISH)
        df.timeZone = TimeZone.getTimeZone("UTC")
        val date: Date = df.parse(updateTime) as Date
        val newDateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH)
        newDateFormat.timeZone = TimeZone.getDefault()
        return newDateFormat.format(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}