package com.example.coindeskexchange.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.local.Currency
import com.example.coindeskexchange.data.remote.*
import com.example.coindeskexchange.databinding.FragmentMainBinding
import com.example.coindeskexchange.resource.ViewState
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {
    private var _binding:FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel:MainFragmentViewModel by viewModel()
    private val bpiPair:Pair<String,Currency>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater,container,false)
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

                }
                is ViewState.Error -> {

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}