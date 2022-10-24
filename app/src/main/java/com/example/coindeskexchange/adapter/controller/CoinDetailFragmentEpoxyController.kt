package com.example.coindeskexchange.adapter.controller

import com.airbnb.epoxy.EpoxyController
import com.example.coindeskexchange.adapter.model.HistoryCurrencyItemEpoxyModel
import com.example.coindeskexchange.adapter.model.HistoryHeaderEpoxyModel
import com.example.coindeskexchange.data.local.PriceHistory

class CoinDetailFragmentEpoxyController:EpoxyController() {

    var historyRate = listOf<PriceHistory>()
    set(value) {
        field = value
        requestModelBuild()
    }

    var currencyCode:String? = null

    override fun buildModels() {
        HistoryHeaderEpoxyModel().id("Header").addTo(this)

        currencyCode?.let { code ->
            historyRate.forEach { history ->
                HistoryCurrencyItemEpoxyModel(history, code).id(history.timeStamp).addTo(this)
            }
        }

    }

}