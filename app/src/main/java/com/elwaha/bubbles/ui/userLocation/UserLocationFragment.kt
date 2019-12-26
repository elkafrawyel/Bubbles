package com.elwaha.bubbles.ui.userLocation

import android.Manifest
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.elwaha.bubbles.R
import com.elwaha.bubbles.ui.home.RC_PERMISSION_LOCATION
import com.elwaha.bubbles.utilies.showLoading
import com.elwaha.bubbles.utilies.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dmax.dialog.SpotsDialog
import kotlinx.android.synthetic.main.user_location_fragment.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class UserLocationFragment : Fragment() {

    companion object {
        fun newInstance() = UserLocationFragment()
    }

    private lateinit var viewModel: UserLocationViewModel
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private var loading: SpotsDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserLocationViewModel::class.java)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)

        backImgv.setOnClickListener{
            findNavController().navigateUp()
        }

        findLocationMbtn.setOnClickListener {
            checkLocation()
        }
    }


    private fun getLastKnownLocation() {
        loading = activity?.showLoading(getString(R.string.gettingLocation))
        loading!!.show()
        mFusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            task.isSuccessful.let {
                val location = task.result
                location?.let {
                    if (loading != null) {
                        loading!!.dismiss()
                    }
                    activity?.toast(getString(R.string.locationSuccess))
//                    LatLng(location.latitude, location.longitude)
                    findNavController().navigate(R.id.action_userLocationFragment_to_homeFragment)
                }
            }
        }
    }

    @AfterPermissionGranted(RC_PERMISSION_LOCATION)
    private fun checkLocation() {
        val perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        if (EasyPermissions.hasPermissions(requireActivity(), *perms)) {
            // Already have permission, do the thing
            getLastKnownLocation()
        } else {
            // Do not have permissions, request them now
            EasyPermissions.requestPermissions(
                this, getString(R.string.requestPermission),
                RC_PERMISSION_LOCATION, *perms
            )
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
