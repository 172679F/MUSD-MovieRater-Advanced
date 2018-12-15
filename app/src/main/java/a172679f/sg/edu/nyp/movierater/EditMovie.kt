package a172679f.sg.edu.nyp.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_edit_movie.*
import java.io.Serializable

class EditMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_movie)

        val bundle: Bundle? = intent.extras

        bundle?.let {
            bundle.apply {
                //Serializable Data
                val movie = getSerializable("movieData") as Movie?
                if (movie != null) {
                    // display on textview

                    etMovieName.setText(movie?.movieName)

                    etMovieDesc.setText(movie?.movieDesc)

                    etReleaseDate.setText(movie?.movieDate)

                    if (movie?.movieLang == "English") {
                        rbEnglish.isChecked = true
                    }
                    else if (movie?.movieLang == "Chinese") {
                        rbChinese.isChecked = true
                    }
                    else if (movie?.movieLang == "Malay") {
                        rbMalay.isChecked = true
                    }
                    else if (movie?.movieLang == "Tamil") {
                        rbTamil.isChecked = true
                    }

                    if (movie?.movieSuitable == "Yes") {
                        cbNotSuitable.isChecked = false
                        cbLanguageUsed.isChecked = false
                        cbViolence.isChecked = false
                    }
                    else {
                        if (movie?.movieSuitable == "No") {
                            cbNotSuitable.isChecked = true
                            cbLanguageUsed.isChecked = false
                            cbViolence.isChecked = false
                        }
                        else if (movie?.movieSuitable == "No (Violence") {
                            cbNotSuitable.isChecked = true
                            cbViolence.isChecked = true
                        }
                        else if (movie?.movieSuitable == "No (Language") {
                            cbNotSuitable.isChecked = true
                            cbLanguageUsed.isChecked = true
                        }
                        else {
                            cbNotSuitable.isChecked = true
                            cbLanguageUsed.isChecked = true
                            cbViolence.isChecked = true
                        }
                    }

                }
            }
        }
    }

    fun btnHide (v: View) {
        if (cbNotSuitable.isChecked) {
            cbViolence.setVisibility(View.VISIBLE)
            cbLanguageUsed.setVisibility(View.VISIBLE)
        }
        else {
            cbViolence.setVisibility(View.INVISIBLE)
            cbLanguageUsed.setVisibility(View.INVISIBLE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.edit_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        var movie = intent.getSerializableExtra("movieData") as Movie?

        var movieName = etMovieName.text.toString()
        var movieDesc = etMovieDesc.text.toString()
        var movieDate = etReleaseDate.text.toString()
        var movieLang = ""
        var movieSuitable = ""
        val movieReview = movie?.movieReview
        val movieRating = movie?.movieRating

        // Validate CheckBox
        if (cbNotSuitable.isChecked) {
            movieSuitable = "No"

            // Validate reason
            if ((cbLanguageUsed.isChecked) && (cbViolence.isChecked)) {
                movieSuitable += " (Language and Violence)"
            }
            else if (cbLanguageUsed.isChecked) {
                movieSuitable += " (Language)"
            }
            else if (cbViolence.isChecked) {
                movieSuitable += " (Violence)"
            }
        }
        else {
            movieSuitable = "Yes"
            cbLanguageUsed.isChecked = false
            cbViolence.isChecked = false
        }

        // Validate Radio Buttons
        if (rbEnglish.isChecked) {
            movieLang = "English"
        }
        else if(rbChinese.isChecked) {
            movieLang = "Chinese"
        }
        else if(rbMalay.isChecked) {
            movieLang = "Malay"
        }
        else {
            movieLang = "Tamil"
        }

        // Validate TextFields
        if(movieName.isEmpty()) {
            etMovieName.setError("Field empty")
        }
        else if(movieDesc.isEmpty()) {
            etMovieDesc.setError("Field empty")
        }
        else if(movieDate.isEmpty()) {
            etReleaseDate.setError("Field empty")
        }
        else {

            val movies = MovieList.movies
            for (i in movies) {

                if (i.movieReview == movieReview) {
                    i.movieName = movieName
                    i.movieDesc = movieDesc
                    i.movieLang = movieLang
                    i.movieDate = movieDate
                    i.movieSuitable = movieSuitable

                    movie = i
                }
            }


            if (item?.itemId == R.id.miSaveEdit) {
                val intent = Intent(this, ViewMovie::class.java).apply {
                    putExtra("movieData", movie as Serializable)
                }
                startActivity(intent)
            }

            if (item?.itemId == R.id.miCancel) {
                finish()
            }

        }

        return super.onOptionsItemSelected(item)
    }
}
