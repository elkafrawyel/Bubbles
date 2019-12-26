package com.elwaha.bubbles.ui.myDeliveredPackages

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elwaha.bubbles.R

class MyDeliveredPackagesAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.my_delivered_packages_item_view) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.addOnClickListener(R.id.myDeliveredPackagesItem)
    }

}