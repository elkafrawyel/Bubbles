package com.elwaha.bubbles.ui.packageDetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.package_details_fragment.*

class PackageDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = PackageDetailsFragment()
    }

    private lateinit var viewModel: PackageDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.package_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PackageDetailsViewModel::class.java)
        oKMbtn.setOnClickListener {
            findNavController().navigateUp()
        }

        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
