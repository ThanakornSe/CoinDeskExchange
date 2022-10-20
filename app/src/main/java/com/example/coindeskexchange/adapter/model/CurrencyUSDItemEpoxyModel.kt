package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.databinding.CurrencyItemLayoutBinding

class CurrencyUSDItemEpoxyModel(private val usd:USD):ViewBindingKotlinModel<CurrencyItemLayoutBinding>(R.layout.currency_item_layout) {

    override fun CurrencyItemLayoutBinding.bind() {
        txtCode.text = usd.code
        txtDescription.text = usd.description
        txtRate.text = usd.rate
        txtSymbol.text = usd.symbol
    }

}