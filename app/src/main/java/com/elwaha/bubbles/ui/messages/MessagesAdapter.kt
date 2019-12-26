package com.elwaha.bubbles.ui.messages

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elwaha.bubbles.R

class MessagesAdapter : BaseQuickAdapter<String, BaseViewHolder>(R.layout.messages_item_view) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.addOnClickListener(R.id.messagesItem)
    }

}