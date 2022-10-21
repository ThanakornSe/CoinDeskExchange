package com.example.coindeskexchange.adapter.controller

import com.airbnb.epoxy.EpoxyController
import com.example.coindeskexchange.adapter.model.CurrencyItemEpoxyModel
import com.example.coindeskexchange.adapter.model.DisclaimerItemEpoxyModel
import com.example.coindeskexchange.adapter.model.TitleCurrencyItemEpoxyModel
import com.example.coindeskexchange.data.local.Currency

class MainFragmentEpoxyController : EpoxyController() {

    var currencyList = arrayListOf<Pair<String, Currency>>()
        set(value) {
            field = value
            requestModelBuild()
        }

    var updateTime: String? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    var disClaimer: String? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        updateTime?.let {
            TitleCurrencyItemEpoxyModel(it).id("Title").addTo(this)
        }

        currencyList.forEach {
            CurrencyItemEpoxyModel(it.second).id(it.first).addTo(this)
        }

        disClaimer?.let {
            DisclaimerItemEpoxyModel(it).id(it).addTo(this)
        }


    }
}