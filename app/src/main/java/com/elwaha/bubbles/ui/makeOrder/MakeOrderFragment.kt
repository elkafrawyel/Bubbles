package com.elwaha.bubbles.ui.makeOrder

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elwaha.bubbles.R

class MakeOrderFragment : Fragment() {

    companion object {
        fun newInstance() = MakeOrderFragment()
    }

    private lateinit var viewModel: MakeOrderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.make_order_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MakeOrderViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
