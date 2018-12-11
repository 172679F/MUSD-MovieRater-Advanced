package a172679f.sg.edu.nyp.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.RatingBar
import android.widget.TextView
import kotlinx.android.synthetic.main.review_movie.*
import java.io.Serializable

class RateMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.review_movie)

        // Action bar
        val actionbar = supportActionBar
        // Set action bar title
        actionbar!!.title = "MovieRater"

        // Set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Serializable Data
                val movie = getSerializable("movieData") as Movie?
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.review_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val movie = intent.getSerializableExtra("movieData") as Movie?

        val movieReview = etReview.text.toString()
        val movieRating = ratingBar.rating

        // Validate Review
        if (movieReview.isEmpty()) {
            etReview.setError("Field empty")
        }
        else {

            movie?.movieReview = movieReview
            movie?.movieRating = movieRating.toString()

            if (item?.itemId == R.id.miSubmit) {
                val intent = Intent(this, ViewMovie::class.java).apply {
                    putExtra("movieData", movie as Serializable)
                }

                startActivity(intent)
            }
        }

        return super.onOptionsItemSelected(item)
    }
}