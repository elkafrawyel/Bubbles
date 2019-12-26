package com.elwaha.bubbles.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import com.elwaha.bubbles.utilies.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }
        when (Injector.getPreferenceHelper().language) {
            "ar" -> {
                arRadio.isChecked = true
            }

            "en" -> {
                enRadio.isChecked = true
            }
        }

        languageGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.arRadio -> {
                    activity?.saveLanguage(Constants.Language.ARABIC)
                }

                R.id.enRadio -> {
                    activity?.saveLanguage(Constants.Language.ENGLISH)
                }
            }
            activity?.changeLanguage()
            activity?.finish()
            activity?.restartApplication()
        }

    }
}
