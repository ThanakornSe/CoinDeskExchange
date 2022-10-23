package com.example.coindeskexchange.adapter.model

import android.text.Html
import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.data.ui.Currency
import com.example.coindeskexchange.databinding.CurrencyItemLayoutBinding

class CurrencyItemEpoxyModel(
    private val item:Currency,
    private val currencyClick:(code:String) -> Unit
    ):ViewBindingKotlinModel<CurrencyItemLayoutBinding>(R.layout.currency_item_layout) {

    override fun CurrencyItemLayoutBinding.bind() {
        txtCode.text = item.code
        txtDescription.text = item.description
        txtRate.text = item.rate
        txtSymbol.text = Html.fromHtml(item.symbol, Html.FROM_HTML_MODE_COMPACT)
        root.setOnClickListener {
            currencyClick(item.code)
        }

    }

}