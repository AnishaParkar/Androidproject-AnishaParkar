package edu.newhaven.android.mytableapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HomeAdapter (val mCtx: Context, val layoutResId: Int, val restaurantlist: List<Restaurantmodel>)
    :ArrayAdapter<Restaurantmodel>(mCtx,layoutResId,restaurantlist)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater= LayoutInflater.from(mCtx);
       val view= layoutInflater.inflate(layoutResId,null)

        val nameTextView= view.findViewById<TextView>(R.id.name_text)
        val category = view.findViewById<TextView>(R.id.category_text)
        val cost= view.findViewById<TextView>(R.id.cost_text)
        val phone= view.findViewById<TextView>(R.id.phonenum_text)

        val restaurant= restaurantlist[position]
        nameTextView.text= restaurant.Address
        category.text=restaurant.Category
        cost.text=restaurant.CostForTwo
        phone.text= restaurant.PhoneNumber

        return view
    }
}