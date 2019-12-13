package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import edu.newhaven.android.mytableapp.Adapter.ReviewAdapter
import edu.newhaven.android.mytableapp.Model.AddReviewModel
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.R
import kotlinx.android.synthetic.main.activity_review.*
import org.jetbrains.anko.startActivity

class Review : GenericMethods(){

    private var reviewAdapt: ReviewAdapter? = null
    private lateinit var recyclerView: RecyclerView
    var reviewlist = mutableListOf<AddReviewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        fabShowRatingDialog.setOnClickListener { onAddRatingClicked() }

        val mLayoutManager = LinearLayoutManager(applicationContext!!)
        val reviewListRV = findViewById<View>(R.id.recyclerReview) as RecyclerView
        reviewListRV.layoutManager = mLayoutManager


        val ref = database.reference.child("Restaurant").child("Olive Garden").child("Reviews")

        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                reviewlist = mutableListOf<AddReviewModel>()

                for(data in p0.children) {

                    val item = p0.children.elementAt(0)
                    val rev = data.getValue(AddReviewModel::class.java)
                    reviewlist.add(rev!!)
                }


                val adapter = ReviewAdapter(reviewlist,applicationContext)
                reviewListRV.adapter = adapter
            }
        })

    }

    private fun onAddRatingClicked() {
        startActivity<AddReview>()
    }
}
