package com.elwaha.bubbles.ui.terms

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.terms_fragment.*

class TermsFragment : Fragment() {

    companion object {
        fun newInstance() = TermsFragment()
    }

    private lateinit var viewModel: TermsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.terms_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TermsViewModel::class.java)

        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}
