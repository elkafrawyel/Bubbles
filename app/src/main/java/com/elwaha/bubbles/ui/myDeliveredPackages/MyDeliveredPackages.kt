package com.elwaha.bubbles.ui.myDeliveredPackages

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.elkafrawyel.CustomViews

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.my_delivered_packages_fragment.*

class MyDeliveredPackages : Fragment() {

    companion object {
        fun newInstance() = MyDeliveredPackages()
    }

    private lateinit var viewModel: MyDeliveredPackagesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_delivered_packages_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyDeliveredPackagesViewModel::class.java)
        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }

        rootView.setLayout(text)
        rootView.setVisible(CustomViews.EMPTY)
        rootView.setEmptyText("لا توجد لديك توصبلات حتي الان")
    }

}
