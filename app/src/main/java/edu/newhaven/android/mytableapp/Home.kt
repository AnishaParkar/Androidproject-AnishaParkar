package edu.newhaven.android.mytableapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import com.google.firebase.database.*

class Home : AppCompatActivity() {

    lateinit var ref: DatabaseReference
    lateinit var restaurantlist: MutableList<Restaurantmodel>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        GenericMethods.setFullScreen(window)

        listView = findViewById<ListView>(R.id.home_listview)

        restaurantlist = mutableListOf()
        ref = FirebaseDatabase.getInstance().getReference("Restaurant")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()) {

                    for (h in p0.children) {
                        val restaurant = h.getValue(Restaurantmodel::class.java)
                        restaurantlist.add(restaurant!!)
                    }

                    val adapter = HomeAdapter(applicationContext, R.layout.row_home, restaurantlist)
                    listView.adapter = adapter
                }
            }
        })
    }
}
