package com.elwaha.bubbles.ui.chatRoom

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.elkafrawyel.CustomViews

import com.elwaha.bubbles.R
import com.elwaha.bubbles.utilies.CustomLoadMoreView
import kotlinx.android.synthetic.main.chat_room_fragment.*

class ChatRoomFragment : Fragment() {

    companion object {
        fun newInstance() = ChatRoomFragment()
    }

    private lateinit var viewModel: ChatRoomViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat_room_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ChatRoomViewModel::class.java)

        rootView.setLayout(messagesRv)
        rootView.setVisible(CustomViews.EMPTY)
        rootView.setEmptyText("لا توجد رسائل")

        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
