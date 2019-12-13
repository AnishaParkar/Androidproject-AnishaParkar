package edu.newhaven.android.mytableapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.newhaven.android.mytableapp.Model.AddReviewModel
import edu.newhaven.android.mytableappv1.R

class ReviewAdapter(private val reviewlist: MutableList<AddReviewModel>, private val context: Context):
    RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rating, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ratereview= reviewlist[position]

        holder.ratingText.text = ratereview.reviewtext


    }

    override fun getItemCount(): Int {
        return reviewlist.size;
    }

    inner class ViewHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var ratingText: TextView

        init {
            ratingText = view.findViewById(R.id.ratingText)
        }
    }

}