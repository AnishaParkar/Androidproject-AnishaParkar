package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.newhaven.android.mytableapp.Adapter.OrderAdapter
import edu.newhaven.android.mytableapp.Model.OrderModel
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.R
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_order__detail.*
import kotlinx.android.synthetic.main.activity_order__detail.adView
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class Order_Detail: GenericMethods(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val REQUEST_LOCATION_PERMISSION = 1

    private var orderAdapt: OrderAdapter? = null
    private lateinit var recyclerView: RecyclerView
    var orderlist = mutableListOf<OrderModel>()
    var totalprice :Float? = 0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order__detail)

        val mLayoutManager = LinearLayoutManager(applicationContext!!)
        val orderListRV = findViewById<View>(R.id.order_rv) as RecyclerView
        orderListRV.layoutManager = mLayoutManager


        val ref = database.reference.child("Restaurant").child("Olive Garden").child("Order Cart")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                orderlist = mutableListOf<OrderModel>()

                for(data in p0.children) {

                    val item = p0.children.elementAt(0)
                    val orderitem = data.getValue(OrderModel::class.java)

                    // Calculating total price of the item in the cart
                    val num3 = orderitem?.cost?.replace("$", "")
                    val num4 = num3!!.toFloat()

                    totalprice = totalprice?.plus(num4)
                    total_amount.text = "$"+totalprice.toString()

                    orderlist.add(orderitem!!)
                }


                val adapter = OrderAdapter(orderlist,applicationContext)
                orderListRV.adapter = adapter
            }
        })

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.maps) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        MobileAds.initialize(this, "ca-app-pub-3940256099942544/6300978111")
        adView.loadAd(AdRequest.Builder().build())

        orderplaced.onClick {
            Toast.makeText(baseContext, "Your Order is placed", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        val zoomLevel = 15f
        val sydney = LatLng(41.258980, -73.011750)
        map.addMarker(MarkerOptions().position(sydney).title("Olive Garden"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel))
    }


}

