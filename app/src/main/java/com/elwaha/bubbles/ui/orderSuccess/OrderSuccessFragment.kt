package com.elwaha.bubbles.ui.orderSuccess

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.order_success_fragment.*

class OrderSuccessFragment : Fragment() {

    companion object {
        fun newInstance() = OrderSuccessFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.order_success_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        close.setOnClickListener {
            goHome()
        }

        returnToHomeMbtn.setOnClickListener {
            goHome()
        }
    }
    private fun goHome(){
        findNavController().navigate(R.id.action_orderSuccessFragment_to_homeFragment)
    }

}
