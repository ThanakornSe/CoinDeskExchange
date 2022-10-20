package com.example.coindeskexchange.adapter.controller

import com.airbnb.epoxy.EpoxyController
import com.example.coindeskexchange.adapter.model.TitleCurrencyItemEpoxyModel
import com.example.coindeskexchange.data.remote.Bpi
import com.example.coindeskexchange.data.remote.USD
import com.example.coindeskexchange.databinding.TitleItemLayoutBinding

class MainFragmentEpoxyController:EpoxyController() {

//    var usd:USD = USD()
//    set(value) {
//        field = value
//        requestModelBuild()
//    }

    override fun buildModels() {
        TitleCurrencyItemEpoxyModel().id("Title").addTo(this)

    }
}