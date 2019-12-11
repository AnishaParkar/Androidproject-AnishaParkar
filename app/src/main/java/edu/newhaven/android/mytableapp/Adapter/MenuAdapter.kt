package edu.newhaven.android.mytableapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import edu.newhaven.android.mytableappv1.MenuModel
import edu.newhaven.android.mytableappv1.R

class MenuAdapter(val mContext: Context,val layoutResId:Int, val MenuList:List<MenuModel>)
    :ArrayAdapter<MenuModel>(mContext,layoutResId,MenuList)
{

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(mContext);
        val view: View = layoutInflater.inflate(layoutResId,null)

        val menu_description = view.findViewById<TextView>(R.id.menu_description)
        val menu_calories = view.findViewById<TextView>(R.id.menu_calories)
        val menu_cost = view.findViewById<TextView>(R.id.menu_cost)
        val menu_itemName = view.findViewById<TextView>(R.id.menu_itemName)


        val menu = MenuList[position]

        menu_itemName.text = menu.ItemName
        menu_description.text = menu.Description
        menu_calories.text = menu.Calories
        menu_cost.text = menu.Cost


        return view

    }
}