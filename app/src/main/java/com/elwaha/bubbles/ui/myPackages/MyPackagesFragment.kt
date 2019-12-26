package com.elwaha.bubbles.ui.myPackages

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.chad.library.adapter.base.BaseQuickAdapter
import com.elkafrawyel.CustomViews

import com.elwaha.bubbles.R
import kotlinx.android.synthetic.main.my_packages_fragment.*

class MyPackagesFragment : Fragment(), BaseQuickAdapter.OnItemChildClickListener {

    companion object {
        fun newInstance() = MyPackagesFragment()
    }

    private lateinit var viewModel: MyPackagesViewModel
    private var adapter = MyPackagesAdapter().also {
        it.onItemChildClickListener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_packages_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MyPackagesViewModel::class.java)
        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }

        rootView.setLayout(myPackagesRv)
        rootView.setVisible(CustomViews.LAYOUT)

        val messages = ArrayList<String>()
        messages.add("A")
        messages.add("A")
        messages.add("A")
        adapter.replaceData(messages)
        adapter.notifyDataSetChanged()

        myPackagesRv.adapter = adapter
        myPackagesRv.setHasFixedSize(true)

    }

    override fun onItemChildClick(adapter: BaseQuickAdapter<*, *>?, view: View?, position: Int) {
        when (view!!.id) {
            R.id.myPackagesItem -> {
//                findNavController().navigate(R.id.action_messagesFragment_to_chatRoomFragment)
            }
        }
    }
}