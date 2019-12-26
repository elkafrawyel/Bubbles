package com.elwaha.bubbles.ui.editDriverInfo

import android.Manifest
import android.app.Activity
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import com.elwaha.bubbles.ui.auth.registerDriver.RC_AVATAR
import com.elwaha.bubbles.ui.auth.registerDriver.RC_LICENCE
import com.elwaha.bubbles.ui.auth.registerDriver.RC_PERMISSION_STORAGE
import com.elwaha.bubbles.utilies.toast
import kotlinx.android.synthetic.main.edit_driver_info_fragment.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class EditDriverInfoFragment : Fragment() {

    companion object {
        fun newInstance() = EditDriverInfoFragment()
    }

    private lateinit var viewModel: EditDriverInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.edit_driver_info_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditDriverInfoViewModel::class.java)
        backImgv.setOnClickListener {
            findNavController().navigateUp()
        }

        addImage.setOnClickListener {
            chooseAvatar()
        }

        userImage.setOnClickListener {
            chooseAvatar()
        }

        licenceImage.setOnClickListener {
            chooseLicence()
        }

        addLicenceImage.setOnClickListener {
            chooseLicence()
        }
    }


    private fun chooseAvatar() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/*"
        startActivityForResult(intent, RC_AVATAR)
    }

    private fun chooseLicence() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "image/*"
        startActivityForResult(intent, RC_LICENCE)

    }


    @AfterPermissionGranted(RC_PERMISSION_STORAGE)
    private fun checkStorage() {
        val perms = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        if (EasyPermissions.hasPermissions(requireActivity(), *perms)) {
            register()
        } else {
            EasyPermissions.requestPermissions(
                this, getString(R.string.requestPermission),
                RC_PERMISSION_STORAGE, *perms
            )
        }
    }

    private fun register() {

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {

                RC_AVATAR -> {
                    val uri = data?.data
                    if (uri != null) {
                        addImage.visibility = View.GONE
                        userImage.setImageURI(uri)
                    } else {
                        activity?.toast(getString(R.string.errorSelectImage))
                    }
                }

                RC_LICENCE-> {
                    val uri = data?.data
                    if (uri != null) {
                        addLicenceImage.visibility = View.GONE
                        licenceImage.setImageURI(uri)
                    } else {
                        activity?.toast(getString(R.string.errorSelectImage))
                    }
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }
}
