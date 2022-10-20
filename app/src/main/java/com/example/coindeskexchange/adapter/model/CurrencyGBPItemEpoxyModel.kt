package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.remote.GBP
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.databinding.CurrencyItemLayoutBinding

class CurrencyGBPItemEpoxyModel(private val gbp:GBP):ViewBindingKotlinModel<CurrencyItemLayoutBinding>(R.layout.currency_item_layout) {

    override fun CurrencyItemLayoutBinding.bind() {
        txtCode.text = gbp.code
        txtDescription.text = gbp.description
        txtRate.text = gbp.rate
        txtSymbol.text = gbp.symbol
    }

}