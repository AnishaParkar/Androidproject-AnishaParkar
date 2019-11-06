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

    lateinit var ref : DatabaseReference
    lateinit var restaurantlist : MutableList<Restaurantmodel>
    lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        GenericMethods.setFullScreen(window)

        listView = findViewById<ListView>(R.id.home_listview)

        restaurantlist=  mutableListOf()
         ref = FirebaseDatabase.getInstance().getReference("Restaurant")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0!!.exists()){

                    for(h in p0.children){
                        val restaurant = h.getValue(Restaurantmodel::class.java)
                        restaurantlist.add(restaurant!!)
                    }

                    val adapter= HomeAdapter(applicationContext, R.layout.row_home, restaurantlist)
                    listView.adapter= adapter
                }
            }
        })

//        val listView = findViewById<ListView>(R.id.home_listview)
//
//        listView.adapter = HomeAdapter(this)
    }


//    private class HomeAdapter(context: Context) : BaseAdapter() {
//
//        private val mContext: Context
//
//        private val names = arrayListOf<String>(
//            "Chipotle", "Popeyes", "Olive Garden", "Dominos", "Subway","Halal Guys"
//        )
//
//        init {
//            mContext = context
//        }
//
//        //responsible for how many rows in my list
//        override fun getCount(): Int {
//            return names.size
//        }
//
//        override fun getItemId(position: Int): Long {
//            return position.toLong()
//        }
//
//
//        override fun getItem(position: Int): Any {
//            return "TEST STRING"
//        }
//
//
//        // responsible for rendering out each row
//        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
//
//            val layoutInflater= LayoutInflater.from(mContext)
//            val rowHome= layoutInflater.inflate(R.layout.row_home,viewGroup,false)
//
//            val nameTextView= rowHome.findViewById<TextView>(R.id.name_text)
//            nameTextView.text= names.get(position)
//
//            val positionTextView=rowHome.findViewById<TextView>(R.id.position_text)
//            positionTextView.text ="Row Number: $position"
//
//            return  rowHome
//
////            val textView = TextView(mContext)
////            textView.text = "Here is my restaurant"
////            return textView
//
//        }
//    }
}

