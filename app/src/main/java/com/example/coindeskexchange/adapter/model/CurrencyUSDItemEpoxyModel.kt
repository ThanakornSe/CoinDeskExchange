package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.local.Currency
import com.example.coindeskexchange.databinding.CurrencyItemLayoutBinding

class CurrencyUSDItemEpoxyModel(private val item:Currency):ViewBindingKotlinModel<CurrencyItemLayoutBinding>(R.layout.currency_item_layout) {

    override fun CurrencyItemLayoutBinding.bind() {
        txtCode.text = item.code
        txtDescription.text = item.description
        txtRate.text = item.rate
        txtSymbol.text = item.symbol
    }

}