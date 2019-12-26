package com.elwaha.bubbles.ui.accountType

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.account_type_fragment.*


class AccountTypeFragment : Fragment() {
   override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.account_type_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        driverImg.setOnClickListener{
            findNavController().navigate(R.id.registerDriverFragment)
        }

        userImg.setOnClickListener{
            findNavController().navigate(R.id.registerUserFragment)
        }
    }

}
