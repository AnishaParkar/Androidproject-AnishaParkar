package edu.newhaven.android.mytableapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.newhaven.android.mytableappv1.GenericMethods
import edu.newhaven.android.mytableappv1.R
import kotlinx.android.synthetic.main.activity_review.*
import org.jetbrains.anko.startActivity

class Review : GenericMethods(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        fabShowRatingDialog.setOnClickListener { onAddRatingClicked() }
    }

    private fun onAddRatingClicked() {
        startActivity<AddReview>()
    }
}