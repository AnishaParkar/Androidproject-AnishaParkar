package edu.newhaven.android.mytableapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import edu.newhaven.android.mytableapp.Model.AddReviewModel
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.R

class AddReview: GenericMethods() {

    lateinit var editreviewtext: EditText
    lateinit var cancel_review: Button
    lateinit var submit_review: Button
    lateinit var reviewrating: RatingBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_rating)

        editreviewtext = findViewById(R.id.editreviewtext)
        cancel_review = findViewById(R.id.cancel_review)
        submit_review = findViewById(R.id.submit_review)
        reviewrating = findViewById(R.id.reviewrating)

        submit_review.setOnClickListener { saveReview() }

        cancel_review.setOnClickListener { onCancelClicked()}
    }

    private fun saveReview(){

        val reviewtext = editreviewtext.text.toString()

        if(reviewtext.isEmpty()){
            editreviewtext.error = "Please enter a review"
            return
        }

        val ref = database.reference.child("Restaurant").child("Olive Garden").child("Reviews")
        val revid = ref.push().key

        val rev = AddReviewModel(reviewtext,reviewrating.numStars)

        ref.child(revid!!).setValue(rev).addOnCompleteListener {
            Toast.makeText(applicationContext, "Review added successfully", Toast.LENGTH_SHORT).show()
        }
    }

    private fun onCancelClicked() {
        finish()
    }

}