package edu.newhaven.android.mytableapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.newhaven.android.mytableapp.Model.OrderModel
import edu.newhaven.android.mytableappv1.R

class OrderAdapter(private val orderlist: MutableList<OrderModel>, private val context: Context):
    RecyclerView.Adapter<edu.newhaven.android.mytableapp.Adapter.OrderAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_detail_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val order= orderlist[position]

        holder.order_itemName.text = order.itemName
        holder.order_cost.text = order.cost

    }

    override fun getItemCount(): Int {
        return orderlist.size;
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var order_itemName: TextView
        internal var order_cost: TextView

        init {
            order_cost= view.findViewById(R.id.order_cost)
            order_itemName = view.findViewById(R.id.order_itemName)

        }
    }

}