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

class Home : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        GenericMethods.setFullScreen(window)

        val listView = findViewById<ListView>(R.id.home_listview)

        listView.adapter = HomeAdapter(this)
    }


    private class HomeAdapter(context: Context) : BaseAdapter() {

        private val mContext: Context

        private val names = arrayListOf<String>(
            "Chipotle", "Popeyes", "Olive Garden", "Dominos", "Subway","Halal Guys"
        )

        init {
            mContext = context
        }

        //responsible for how many rows in my list
        override fun getCount(): Int {
            return names.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }


        override fun getItem(position: Int): Any {
            return "TEST STRING"
        }


        // responsible for rendering out each row
        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {

            val layoutInflater= LayoutInflater.from(mContext)
            val rowHome= layoutInflater.inflate(R.layout.row_home,viewGroup,false)

            val nameTextView= rowHome.findViewById<TextView>(R.id.name_text)
            nameTextView.text= names.get(position)

            val positionTextView=rowHome.findViewById<TextView>(R.id.position_text)
            positionTextView.text ="Row Number: $position"

            return  rowHome

//            val textView = TextView(mContext)
//            textView.text = "Here is my restaurant"
//            return textView




        }


    }
}

