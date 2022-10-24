package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.local.PriceHistory
import com.example.coindeskexchange.databinding.HistoryRateItemLayoutBinding
import com.example.coindeskexchange.utils.AppConst

class HistoryCurrencyItemEpoxyModel(private val priceHistory: PriceHistory, private val code:String?):ViewBindingKotlinModel<HistoryRateItemLayoutBinding>(R.layout.history_rate_item_layout) {
    override fun HistoryRateItemLayoutBinding.bind() {

        txtDateTime.text = priceHistory.timeStamp

        code?.let {
            when (it) {
                "USD" -> {
                    txtRate.text = priceHistory.RateUSD
                }
                "EUR" -> {
                    txtRate.text = priceHistory.RateEUR
                }
                "GBP" -> {
                    txtRate.text = priceHistory.RateGBP
                }
            }
        }

    }
}