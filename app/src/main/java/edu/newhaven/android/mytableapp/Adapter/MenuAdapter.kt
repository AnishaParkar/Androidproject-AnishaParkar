package edu.newhaven.android.mytableapp.Adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.newhaven.android.mytableappv1.MenuModel
import com.google.firebase.database.FirebaseDatabase
import edu.newhaven.android.mytableappv1.Menu
import edu.newhaven.android.mytableappv1.R

class MenuAdapter(private val menulist: MutableList<MenuModel>, private val context: Context):RecyclerView.Adapter<MenuAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val menu= menulist[position]

        holder.menu_itemName.text = menu.ItemName
        holder.menu_calories.text = menu.Calories
        holder.menu_cost.text = menu.Cost
        holder.menu_description.text = menu.Description

        holder.add_menu.setOnClickListener { addtoCart(menu) }

    }

    override fun getItemCount(): Int {
        return menulist.size;
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var menu_itemName: TextView
        internal var menu_cost: TextView
        internal var menu_calories: TextView
        internal var menu_description: TextView
        internal var add_menu:Button

        init {
            menu_itemName = view.findViewById(R.id.menu_itemName)
            menu_cost = view.findViewById(R.id.menu_cost)
            menu_calories = view.findViewById(R.id.menu_calories)
            menu_description = view.findViewById(R.id.menu_description)
            add_menu = view.findViewById(R.id.Add_menu)
        }
    }

    private fun addtoCart(menu:MenuModel){

//        val db = GenericMethods.database.getReference("Orders")
//        db.addListenerForSingleValueEvent(object: ValueEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                GenericMethods.pd.dismiss()
//            }
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                val pos = dataSnapshot.children.count()+1
//                info(pos)
//                val map = HashMap<String, String>()
//                map["Itemname"] = menu.ItemName
//                map["cost"] = menu.Cost
//                db.child("Orders$pos").push()
//                db.child("Orders$pos").setValue(map)
//            }
//        })

    }

}