package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.databinding.TitleItemLayoutBinding

class TitleCurrencyItemEpoxyModel(val time:String):ViewBindingKotlinModel<TitleItemLayoutBinding>(R.layout.title_item_layout) {
    override fun TitleItemLayoutBinding.bind() {
        txtLastUpdatedTime.text = "Rate updated on $time"
    }
}