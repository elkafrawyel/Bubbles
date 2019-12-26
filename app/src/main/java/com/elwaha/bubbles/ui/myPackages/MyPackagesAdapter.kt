package com.elwaha.bubbles.ui.myPackages

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elwaha.bubbles.R

class MyPackagesAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.my_packages_item_view) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.addOnClickListener(R.id.myPackagesItem)
    }

}