package a172679f.sg.edu.nyp.movierater

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var rgLanguage : RadioGroup
    lateinit var rbEnglish : RadioButton
    lateinit var rbChinese : RadioButton
    lateinit var rbMalay : RadioButton
    lateinit var rbTamil : RadioButton
    lateinit var cbNotSuitable : CheckBox
    lateinit var cbViolence : CheckBox
    lateinit var cbLanguageUsed : CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rbEnglish = findViewById(R.id.rbEnglish) as RadioButton
        rbChinese = findViewById(R.id.rbChinese) as RadioButton
        rbMalay = findViewById(R.id.rbMalay) as RadioButton
        rbTamil = findViewById(R.id.rbTamil) as RadioButton
        cbNotSuitable = findViewById(R.id.cbNotSuitable) as CheckBox
        cbViolence = findViewById(R.id.cbViolence) as CheckBox
        cbLanguageUsed = findViewById(R.id.cbLanguageUsed) as CheckBox

        // Action bar
        val actionbar = supportActionBar
        // Set action bar title
        actionbar!!.title = "MovieRater"

        // Set back button
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
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

    fun btnValidateInput(v: View) {
        val movieName: String = etMovieName.text.toString()
        val movieDesc: String = etMovieDesc.text.toString()
        var movieLanguage: String = ""
        val releaseDate: String = etReleaseDate.text.toString()
        var movieSuitable: String = ""

        // Validate CheckBox
        if (cbNotSuitable.isChecked) {
            movieSuitable = "false"
        }
        else {
            movieSuitable = "true"
            cbLanguageUsed.isChecked = false
            cbViolence.isChecked = false
        }


        // Validate Radio Buttons
        if (rbEnglish.isChecked) {
            movieLanguage = "English"
        }
        else if(rbChinese.isChecked) {
            movieLanguage = "Chinese"
        }
        else if(rbMalay.isChecked) {
            movieLanguage = "Malay"
        }
        else {
            movieLanguage = "Tamil"
        }


        var s: String = "Title = " + movieName + "\nOverview = " + movieDesc + "\nRelease Date = " + releaseDate + "\nLanguage = " + movieLanguage + "\nSuitable for all ages = " + movieSuitable

        // Validate reason
        if ((cbLanguageUsed.isChecked) && (cbViolence.isChecked)) {
            s += "\nReason: \nLanguage \nViolence"
        }
        else if (cbLanguageUsed.isChecked) {
            s += "\nReason: \nLanguage"
        }
        else if (cbViolence.isChecked) {
            s += "\nReason: \nViolence"
        }


        // Validate TextFields
        if(movieName.isEmpty()) {
            etMovieName.setError("Field empty")
        }
        else if(movieDesc.isEmpty()) {
            etMovieDesc.setError("Field empty")
        }
        else if(releaseDate.isEmpty()) {
            etReleaseDate.setError("Field empty")
        }
        else {
            // Display information
            Toast.makeText(this, s, Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val movieName = etMovieName.text.toString()
        val movieDesc = etMovieDesc.text.toString()
        val movieDate = etReleaseDate.text.toString()
        var movieLang = ""
        var movieSuitable = ""
        var movieReview = "No Reviews yet. \nLong Press here to add your review."
        val movieRating = "5"

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

            val movie = Movie(movieName, movieDesc, movieLang, movieDate, movieSuitable, movieReview, movieRating)

            if (item?.itemId == R.id.miAdd) {
                val intent = Intent(this, ViewMovie::class.java).apply {
                    putExtra("movieData", movie as Serializable)
                }
//                intent.putExtra("Name", movieName)
//                intent.putExtra("Desc", movieDesc)
//                intent.putExtra("Date", movieDate)
//                intent.putExtra("Language", movieLang)
//                intent.putExtra("Suitable", movieSuitable)
//                intent.putExtra("Review", movieReview)

                startActivity(intent)
            }

        }

        if (item?.itemId == R.id.miClear) {
            etMovieName.text.clear()
            etMovieDesc.text.clear()
            etReleaseDate.text.clear()
            cbNotSuitable.isChecked = false
            cbLanguageUsed.isChecked = false
            cbViolence.isChecked = false
            rbEnglish.isChecked = true
        }

        return super.onOptionsItemSelected(item)
    }
}
