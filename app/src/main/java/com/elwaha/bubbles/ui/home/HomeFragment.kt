package com.elwaha.bubbles.ui.home

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.elwaha.bubbles.R
import com.elwaha.bubbles.utilies.Constants
import com.elwaha.bubbles.utilies.Constants.MAPVIEW_BUNDLE_KEY
import com.elwaha.bubbles.utilies.Injector
import com.elwaha.bubbles.utilies.shareApp
import com.elwaha.bubbles.utilies.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.home_fragment.*
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions


const val LOGOUT_ID = 9
const val RC_PERMISSION_LOCATION = 1995

class HomeFragment : Fragment(), NavigationView.OnNavigationItemSelectedListener,
    OnMapReadyCallback {

    private lateinit var viewModel: HomeViewModel
    private lateinit var mMapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var myLatLng: LatLng
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        mMapView = view.findViewById(R.id.mMapView)
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context!!)
        initGoogleMap(savedInstanceState)
        return view
    }

    private fun getLastKnownLocation() {
        mFusedLocationProviderClient.lastLocation.addOnCompleteListener { task ->
            task.isSuccessful.let {
                val location = task.result
                location?.let {
                    myLatLng = LatLng(location.latitude, location.longitude)
                    addMyLocationOnMap(myLatLng)
                    addPackageOnMap("Mobile Iphone 11", LatLng((-34).toDouble(), 151.toDouble()))
                }
            }
        }
    }

    private fun addMyLocationOnMap(myLatLng: LatLng) {
        googleMap.addMarker(
            MarkerOptions().position(myLatLng)
                .title(getString(R.string.myLoaction))
        )
            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation))
        moveCamera(myLatLng)
    }

    private fun addPackageOnMap(title: String, latLng: LatLng) {
        googleMap.addMarker(
            MarkerOptions().position(latLng)
                .title(title)
        ).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.packageonmap))
    }

    private fun moveCamera(latLng: LatLng) {
        //move the camera
        googleMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                latLng, 12.0f
            )
        )
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {
        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY)
        }
        mMapView.onCreate(mapViewBundle)
        mMapView.getMapAsync(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        navigationView.setNavigationItemSelectedListener(this)

        drawerToggleImgv.setOnClickListener {
            rootView.openDrawer(GravityCompat.START)
        }

        notificationImg.setOnClickListener {
            findNavController().navigate(R.id.notificationsFragment)
        }

        val logoutView = navigationView.menu.getItem(LOGOUT_ID).actionView

        logoutView.findViewById<LinearLayout>(R.id.logoutView).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            rootView.closeDrawer(GravityCompat.START)
        }

        pickToLocationMbtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_makeOrderFragment)
        }

        setUserType()

    }

    private fun setUserType() {
        if (Injector.getPreferenceHelper().userType == Constants.UserType.DRIVER.value) {
            navigationView.menu.getItem(0).title = getString(R.string.MyDeliveredPackages)
            navigationView.menu.getItem(0).icon = getDrawable(activity!!, R.drawable.mydeliverd)
        } else {
            navigationView.menu.getItem(0).title = getString(R.string.myPackages)
            navigationView.menu.getItem(0).icon = getDrawable(activity!!, R.drawable.mypackages)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_myPackages -> {
                if (Injector.getPreferenceHelper().userType == Constants.UserType.DRIVER.value) {
                    findNavController().navigate(R.id.myDeliveredPackages)
                } else {
                    findNavController().navigate(R.id.myPackagesFragment)
                }
            }

            R.id.nav_Massages -> {
                findNavController().navigate(R.id.messagesFragment)
            }

            R.id.nav_MyInfo -> {
                if (Injector.getPreferenceHelper().userType == Constants.UserType.DRIVER.value) {
                    findNavController().navigate(R.id.action_homeFragment_to_editDriverInfoFragment)
                } else {
                    findNavController().navigate(R.id.action_homeFragment_to_editUserInfoFragment)
                }
            }

            R.id.nav_ChangePassword -> {
                findNavController().navigate(R.id.changePasswordFragment)
            }

            R.id.nav_terms -> {
                findNavController().navigate(R.id.termsFragment)
            }

            R.id.nav_about -> {
                findNavController().navigate(R.id.aboutFragment)
            }

            R.id.nav_suggestion -> {
                findNavController().navigate(R.id.suggestionsFragment)
            }

            R.id.nav_settings -> {
                findNavController().navigate(R.id.settingsFragment)
            }

            R.id.nav_share -> {
                activity?.shareApp()
            }

        }
        rootView.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onMapReady(map: GoogleMap) {

        googleMap = map

        checkLocation()
    }

    @AfterPermissionGranted(RC_PERMISSION_LOCATION)
    private fun checkLocation() {
        val perms = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
        if (EasyPermissions.hasPermissions(requireActivity(), *perms)) {
            // Already have permission, do the thing
            getLastKnownLocation()
            googleMap.isMyLocationEnabled = true


            googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(p0: Marker?): View? {
                    return null
                }

                override fun getInfoWindow(marker: Marker?): View {
                    val markerItemView = layoutInflater.inflate(R.layout.package_details_dialog, null)

                    val locationTv = markerItemView!!.findViewById<TextView>(R.id.locationTv)
                    locationTv.text ="القاهرة"

                    val dateTv = markerItemView.findViewById<TextView>(R.id.dateTv)
                    dateTv.text ="22-12-2020"

                    val timeTv = markerItemView.findViewById<TextView>(R.id.timeTv)
                    timeTv.text ="05:55 PM"


                    return markerItemView
                }
            })

            googleMap.setOnInfoWindowClickListener { marker: Marker ->
                marker.hideInfoWindow()
                val action = HomeFragmentDirections.actionHomeFragmentToPackageDetailsFragment()
                findNavController().navigate(action)
            }
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


    override fun onResume() {
        super.onResume()
        mMapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mMapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mMapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView.onLowMemory()
    }

}
