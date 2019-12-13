package edu.newhaven.android.mytableappv1

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.newhaven.android.mytableapp.Adapter.MenuAdapter
import edu.newhaven.android.mytableapp.Order_Detail
import edu.newhaven.android.mytableapp.Review

import kotlinx.android.synthetic.main.activity_menu.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity


class Menu : GenericMethods() {

    private var menuAdapt: MenuAdapter? = null
    private lateinit var recyclerView: RecyclerView
    var menulist = mutableListOf<MenuModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val mLayoutManager = LinearLayoutManager(applicationContext!!)
        val tenderListRV = findViewById<View>(R.id.menu_rv) as RecyclerView
        tenderListRV.layoutManager = mLayoutManager


        val ref = database.reference.child("Restaurant").child("Olive Garden").child("Menu")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                menulist = mutableListOf<MenuModel>()

                for(data in p0.children) {

                    val item = p0.children.elementAt(0)
                    val menu = data.getValue(MenuModel::class.java)
                    menulist.add(menu!!)
                }


                val adapter = MenuAdapter(menulist,applicationContext)
                tenderListRV.adapter = adapter
            }
        })

        order_info.onClick {
            startActivity<Order_Detail>()
        }

        add_review.onClick {
            startActivity<Review>()
        }
    }
}


