package com.example.coindeskexchange.adapter.model

import com.androidfactory.fakestore.epoxy.ViewBindingKotlinModel
import com.example.coindeskexchange.R
import com.example.coindeskexchange.databinding.DisclaimerItemLayoutBinding

class DisclaimerItemEpoxyModel(private val disclaimerText:String):ViewBindingKotlinModel<DisclaimerItemLayoutBinding>(R.layout.disclaimer_item_layout) {

    override fun DisclaimerItemLayoutBinding.bind() {
        txtDisclaimer.text = disclaimerText
    }

}