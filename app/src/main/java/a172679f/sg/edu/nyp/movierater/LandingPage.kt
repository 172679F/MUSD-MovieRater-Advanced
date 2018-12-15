package a172679f.sg.edu.nyp.movierater

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.cardview.R.style.CardView
import android.support.v7.widget.CardView
import android.view.*
import android.widget.*
import kotlinx.android.synthetic.main.activity_landing_page.*
import kotlinx.android.synthetic.main.activity_landing_page.view.*
import java.io.Serializable

class LandingPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)

//        registerForContextMenu(tvLanding)

//        val movie = Movie("Avengers", "Cool Movie", "English", "123", "Yes", "Good movie!", "3")
//
//        MovieList.movies.add(movie)
        val movies = MovieList.movies
        var s = ""
        for (i in movies) {
            val movie = i
//            s += i.movieName
//            tvLanding.text = s
//            val tvListing = TextView(this)
//            tvListing.textSize = 20f
//            tvListing.textAlignment = View.TEXT_ALIGNMENT_CENTER
//            tvListing.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)



            val mainLinearLayout = findViewById(R.id.landing_layout) as LinearLayout
            val cardLinearLayout = LinearLayout(this)

            cardLinearLayout.orientation = LinearLayout.VERTICAL
            val params = RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            params.setMargins(16,16,16,16)

            val cardView = CardView(this)
//            cardView.radius = 30f
            cardView.setContentPadding(60,60,60,60)
            cardView.layoutParams = params
//            cardView.cardElevation = 30f

            val title = TextView(this)
            val image = ImageView(this)
            title.text = movie.movieName
            title.textSize = 15f
            title.setGravity(Gravity.CENTER)
//            image.setImageResource(R.drawable.movieicon)
            cardLinearLayout.addView(image)
            cardLinearLayout.addView(title)
            cardView.addView(cardLinearLayout)
            mainLinearLayout.addView(cardView)

            cardView.setOnClickListener {
                val intent = Intent(this, ViewMovie::class.java).apply {
                    putExtra("movieData", movie as Serializable)
                }
                startActivity(intent)
            }
        }

            }

//    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//        super.onCreateContextMenu(menu, v, menuInfo)
//
//        if (v?.id == R.id.tvLanding) {
//
//            menu?.add(1, 1001, 1, "Add")
//        }
//    }
//
//    override fun onContextItemSelected(item: MenuItem?): Boolean {
//
//        if (item?.itemId == 1001) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//
//        }
//
//        return super.onContextItemSelected(item)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.landing_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.miAdd) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        return super.onContextItemSelected(item)
    }
}



