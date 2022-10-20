package com.example.coindeskexchange.adapter.controller

import com.airbnb.epoxy.EpoxyController
import com.example.coindeskexchange.adapter.model.CurrencyUSDItemEpoxyModel
import com.example.coindeskexchange.adapter.model.TitleCurrencyItemEpoxyModel
import com.example.coindeskexchange.data.local.Currency
import com.example.coindeskexchange.data.remote.Bpi
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.databinding.TitleItemLayoutBinding

class MainFragmentEpoxyController:EpoxyController() {

    var currencyList = arrayListOf<Pair<String, Currency>>()
    set(value) {
        field = value
        requestModelBuild()
    }

    override fun buildModels() {
        TitleCurrencyItemEpoxyModel().id("Title").addTo(this)

        currencyList.forEach {
            CurrencyUSDItemEpoxyModel(it.second).id(it.first).addTo(this)
        }
    }
}