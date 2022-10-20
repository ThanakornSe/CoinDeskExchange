package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.remote.EUR
import com.example.coindeskexchange.data.remote.GBP
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.databinding.CurrencyItemLayoutBinding

class CurrencyEURItemEpoxyModel(private val eur:EUR):ViewBindingKotlinModel<CurrencyItemLayoutBinding>(R.layout.currency_item_layout) {

    override fun CurrencyItemLayoutBinding.bind() {
        txtCode.text = eur.code
        txtDescription.text = eur.description
        txtRate.text = eur.rate
        txtSymbol.text = eur.symbol
    }

}