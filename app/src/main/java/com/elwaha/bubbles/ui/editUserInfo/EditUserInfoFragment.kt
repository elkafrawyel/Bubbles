package com.elwaha.bubbles.ui.editUserInfo

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.edit_user_info_fragment.*

class EditUserInfoFragment : Fragment() {

    companion object {
        fun newInstance() = EditUserInfoFragment()
    }

    private lateinit var viewModelUser: EditUserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_user_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelUser = ViewModelProviders.of(this).get(EditUserInfoViewModel::class.java)

        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }

    }

}
