package edu.newhaven.android.mytableappv1

import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Home : GenericMethods(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this@Home, R.layout.activity_home)

        order_food.onClick {
            startActivity<Menu>()
//            startActivity<DecoderActivity>()
        }
        val ref = database.getReference("Restaurant")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                for(data in p0.children) {
                    home_address.text = "Address : ${data.child("Address").value}"
                    home_category.text = "Category : ${data.child("Category").value}"
                    home_costForTwo.text = "Cost For Two : ${data.child("Cost_for_two").value}"
                    home_phone.text = "Phone Number : ${data.child("Phone Number").value}"
                }
            }
        })

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111")
        adView.loadAd(AdRequest.Builder().build())

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val zoomLevel = 15f
        val sydney = LatLng(41.258980, -73.011750)
        map.addMarker(MarkerOptions().position(sydney).title("Olive Garden"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))
    }
}
