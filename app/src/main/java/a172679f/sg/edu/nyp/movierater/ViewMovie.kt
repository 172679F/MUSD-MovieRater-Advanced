package a172679f.sg.edu.nyp.movierater

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import android.widget.TextView
import kotlinx.android.synthetic.main.view_movie_details.*
import java.io.Serializable

class ViewMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_movie_details)

        // Action bar
        val actionbar = supportActionBar
        // Set action bar title
        actionbar!!.title = "MovieRater"

        // Set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

//        data class ViewMovie(val title: String, val oveview: String, val language: String, val releaseDate: String, val suitable: String)
//        val movieDetails = ViewMovie("Venom", "When Eddie Brock acquires the powers of a symbiote, he will have to release his alter-ego Venom to save his life.", "English", "03-10-2018", "Yes")
//
//        val movieTitle = movieDetails.title
//        val movieOverview = movieDetails.oveview
//        val movieLanguage = movieDetails.language
//        val movieReleaseDate = movieDetails.releaseDate
//        val movieNotSuitable = movieDetails.suitable
//
//        movietitle.text = movieDetails.title
//        overview.text = movieDetails.oveview
//        language.text = movieDetails.language
//        date.text = movieDetails.releaseDate
//        suitable.text = movieDetails.suitable

        registerForContextMenu(review)

        // get data from intent
//        var intent = intent
//        val movieName = intent.getStringExtra("Name")
//        val movieDesc = intent.getStringExtra("Desc")
//        val movieDate = intent.getStringExtra("Date")
//        val movieLang = intent.getStringExtra("Language")
//        val movieSuitable = intent.getStringExtra("Suitable")
//        val movieReview = intent.getStringExtra("Review")
//        val tvReview = findViewById<TextView>(R.id.review)
//        tvReview.text = movieReview


        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Serializable Data
                val movie = getSerializable("movieData") as Movie?
                if (movie != null) {
                    // display on textview
                    val tvTitle =  findViewById<TextView>(R.id.movietitle)
                    tvTitle.text = movie?.movieName

                    val tvDesc =  findViewById<TextView>(R.id.overview)
                    tvDesc.text = movie?.movieDesc

                    val tvDate =  findViewById<TextView>(R.id.date)
                    tvDate.text = movie?.movieDate

                    val tvLang =  findViewById<TextView>(R.id.language)
                    tvLang.text = movie?.movieLang

                    val tvSuitable = findViewById<TextView>(R.id.suitable)
                    tvSuitable.text = movie?.movieSuitable

                    val tvReview = findViewById<TextView>(R.id.review)
                    tvReview.text = movie?.movieReview

                    val ratingBar = findViewById<RatingBar>(R.id.ratingBar)
                    ratingBar.rating = movie?.movieRating.toFloat()

                    if (movie?.movieReview != "No Reviews yet. \n" +
                        "Long Press here to add your review.")
                    ratingBar.setVisibility(View.VISIBLE)

                }
            }
        }

        // display on textview
//        val tvTitle =  findViewById<TextView>(R.id.movietitle)
//        tvTitle.text = movieName
//
//        val tvDesc =  findViewById<TextView>(R.id.overview)
//        tvDesc.text = movieDesc
//
//        val tvDate =  findViewById<TextView>(R.id.date)
//        tvDate.text = movieDate
//
//        val tvLang =  findViewById<TextView>(R.id.language)
//        tvLang.text = movieLang
//
//        val tvSuitable = findViewById<TextView>(R.id.suitable)
//        tvSuitable.text = movieSuitable
//
//        val tvReview = findViewById<TextView>(R.id.review)
//        tvReview.text = movieReview

    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v?.id == R.id.review) {

            menu?.add(1, 1001, 1, "Add Review")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {

        val movie = intent.getSerializableExtra("movieData") as Movie?

        if (item?.itemId == 1001) {
            val intent = Intent(this, RateMovie::class.java).apply {
                putExtra("movieData", movie as Serializable)
            }

            startActivity(intent)
        }

        return super.onContextItemSelected(item)
    }
}

