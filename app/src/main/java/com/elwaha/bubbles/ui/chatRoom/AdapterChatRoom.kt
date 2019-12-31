package com.elwaha.bubbles.ui.chatRoom

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.elwaha.bubbles.R
import com.elwaha.bubbles.data.models.ChatRoomMessageModel
import com.elwaha.bubbles.utilies.loadWithPlaceHolder
import de.hdodenhof.circleimageview.CircleImageView

class AdapterChatRoom(data: MutableList<ChatRoomMessageModel>?) :
    BaseMultiItemQuickAdapter<ChatRoomMessageModel, BaseViewHolder>(data) {

    init {
        addItemType(0, R.layout.message_from_item_view)
        addItemType(1, R.layout.message_to_item_view)
    }

    override fun convert(helper: BaseViewHolder?, item: ChatRoomMessageModel) {

        when (helper?.itemViewType) {
            0 -> {
                helper.getView<CircleImageView>(R.id.from_image).loadWithPlaceHolder(item.avatarFrom!!)
                helper.setText(R.id.from_message,item.message)
//                helper.setText(R.id.from_time,item.createdAt)

            }

            1 -> {
                helper.getView<CircleImageView>(R.id.to_image).loadWithPlaceHolder(item.avatarTo!!)
                helper.setText(R.id.to_message,item.message)
//                helper.setText(R.id.to_time,item.createdAt)
            }
            else -> {
            }
        }
    }

}