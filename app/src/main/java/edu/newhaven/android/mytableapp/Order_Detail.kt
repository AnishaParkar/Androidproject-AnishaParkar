package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.newhaven.android.mytableapp.Adapter.OrderAdapter
import edu.newhaven.android.mytableapp.Model.OrderModel
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.R

class Order_Detail: GenericMethods(){

    private var orderAdapt: OrderAdapter? = null
    private lateinit var recyclerView: RecyclerView
    var orderlist = mutableListOf<OrderModel>()

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
                    orderlist.add(orderitem!!)
                }


                val adapter = OrderAdapter(orderlist,applicationContext)
                orderListRV.adapter = adapter
            }
        })

    }
}

