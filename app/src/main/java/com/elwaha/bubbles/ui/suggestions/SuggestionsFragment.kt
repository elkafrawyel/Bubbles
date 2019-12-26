package com.elwaha.bubbles.ui.suggestions

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.elwaha.bubbles.R

class SuggestionsFragment : Fragment() {

    companion object {
        fun newInstance() = SuggestionsFragment()
    }

    private lateinit var viewModel: SuggestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.suggestions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SuggestionsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
