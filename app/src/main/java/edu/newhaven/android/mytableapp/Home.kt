package edu.newhaven.android.mytableappv1

import android.os.Bundle
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class Home : GenericMethods() {

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

    }
}
