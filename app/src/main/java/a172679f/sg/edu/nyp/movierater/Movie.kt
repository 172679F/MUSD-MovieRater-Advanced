package a172679f.sg.edu.nyp.movierater

import java.io.Serializable

class Movie(movieName: String, movieDesc: String, movieLang: String, movieDate: String, movieSuitable: String, movieReview: String, movieRating: String):Serializable {

    var movieName: String
    var movieDesc: String
    var movieLang: String
    var movieDate: String
    var movieSuitable: String
    var movieReview: String
    var movieRating: String

    init {
        this.movieName = movieName
        this.movieDesc = movieDesc
        this.movieLang = movieLang
        this.movieDate = movieDate
        this.movieSuitable = movieSuitable
        this.movieReview = movieReview
        this.movieRating = movieRating
    }
}

object MovieList {
    var movies = arrayListOf<Movie>()

    fun listMovies() {
        for(i in movies)
        {
            println(i)
        }
    }
}
