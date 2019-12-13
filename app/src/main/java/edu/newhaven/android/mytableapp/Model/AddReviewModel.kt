package edu.newhaven.android.mytableapp.Model

class AddReviewModel{
    var reviewtext: String? = null
    var reviewrating:Int = 0

    constructor(){}

    constructor(reviewtext:String, reviewrating:Int){
        this.reviewtext = reviewtext
        this.reviewrating = reviewrating

    }

}