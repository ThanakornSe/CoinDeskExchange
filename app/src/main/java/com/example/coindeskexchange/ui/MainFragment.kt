package com.example.coindeskexchange.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coindeskexchange.R
import com.example.coindeskexchange.adapter.controller.MainFragmentEpoxyController
import com.example.coindeskexchange.data.local.Currency
import com.example.coindeskexchange.data.remote.*
import com.example.coindeskexchange.databinding.FragmentMainBinding
import com.example.coindeskexchange.resource.ViewState
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainFragmentViewModel by viewModel()
    private val bpiPair: ArrayList<Pair<String, Currency>> = arrayListOf()
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
                        bpiPair.add(Pair("USD", Currency(usd.code, usd.description, usd.rate, usd.rateFloat, usd.symbol)))
                        bpiPair.add(Pair("GBP", Currency(gbp.code, gbp.description, gbp.rate, gbp.rateFloat, gbp.symbol)))
                        bpiPair.add(Pair("EUR", Currency(eur.code, eur.description, eur.rate, eur.rateFloat, eur.symbol)))
                        epoxyController.currencyList = pairList
                    }
                }
                is ViewState.Error -> {

                }
            }
        }
        binding.epoxyRecView.setController(epoxyController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}