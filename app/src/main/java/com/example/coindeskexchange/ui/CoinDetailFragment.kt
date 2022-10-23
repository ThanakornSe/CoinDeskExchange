package com.example.coindeskexchange.ui

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.remote.Bpi
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.data.ui.Currency
import com.example.coindeskexchange.databinding.FragmentCoinDetailBinding
import com.example.coindeskexchange.resource.ViewState
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat

class CoinDetailFragment : Fragment() {
    private var _binding: FragmentCoinDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()
    private lateinit var minuteUpdateReceiver: BroadcastReceiver

    private lateinit var currencyCode:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoinDetailBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllCoins()
        currencyCode = CoinDetailFragmentArgs.fromBundle(requireArguments()).currencyCode
        fetchData(currencyCode)
    }

    private fun fetchData(code:String) {
        when (code) {
            "USD" -> {
                viewModel.currencyUSD.observe(viewLifecycleOwner) { response ->
                    if (response is ViewState.Success) {
                        val usd = response.data
                        usd?.let {
                            binding.apply {
                                txtCode.text = "${usd.code}/BTC"
                                txtRate.text = usd.rate
                                edtEnterCurrencyAmount.hint = "Enter ${usd.code} amount"
                                txtSymbol.text = Html.fromHtml(usd.symbol, Html.FROM_HTML_MODE_COMPACT)
                                calculateCurrencyExchange(usd.rateFloat)
                            }
                        }
                    }
                }
            }
            "EUR" -> {
                viewModel.currencyEUR.observe(viewLifecycleOwner) { response ->
                    if (response is ViewState.Success) {
                        val eur = response.data
                        eur?.let {
                            binding.apply {
                                txtCode.text = "${eur.code}/BTC"
                                txtRate.text = eur.rate
                                edtEnterCurrencyAmount.hint = "Enter ${eur.code} amount"
                                txtSymbol.text = Html.fromHtml(eur.symbol,Html.FROM_HTML_MODE_COMPACT)
                                calculateCurrencyExchange(eur.rateFloat)
                            }
                        }
                    }
                }
            }
            "GBP" -> {
                viewModel.currencyGBP.observe(viewLifecycleOwner) { response ->
                    if (response is ViewState.Success) {
                        val gbp = response.data
                        gbp?.let {
                            binding.apply {
                                txtCode.text = "${gbp.code}/BTC"
                                txtRate.text = gbp.rate
                                edtEnterCurrencyAmount.hint = "Enter ${gbp.code} amount"
                                txtSymbol.text = Html.fromHtml(gbp.symbol,Html.FROM_HTML_MODE_COMPACT)
                                calculateCurrencyExchange(gbp.rateFloat)
                            }
                        }
                    }
                }
            }
        }
        viewModel.coinData.observe(viewLifecycleOwner) { response ->
            val result = response.data?.bpi
            result?.let { bpi ->
                when (response) {
                    is ViewState.Loading -> {

                    }
                    is ViewState.Success -> {

                    }
                    is ViewState.Error -> {

                    }
                }
            }

        }
    }

    private fun calculateCurrencyExchange(currencyRate: Double) {
        binding.apply {
            edtEnterCurrencyAmount.doAfterTextChanged {
                btnCalculateRate.isEnabled = !it.isNullOrEmpty()
            }
            btnCalculateRate.setOnClickListener {
                val value = edtEnterCurrencyAmount.text.toString().toDouble()
                val decimalFloat = DecimalFormat("##.####")
                val result = value / currencyRate
                txtExchangedNumber.text = decimalFloat.format(result).toString()
            }
        }

    }

    private fun startMinuteUpdater() {
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_TIME_TICK)
        minuteUpdateReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
//                fetchData()
//                epoxyController.requestModelBuild()
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