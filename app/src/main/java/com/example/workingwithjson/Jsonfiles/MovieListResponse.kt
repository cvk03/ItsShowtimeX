package com.example.workingwithjson.Jsonfiles

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("Movie List")
    val movieList: ArrayList<Movie>
)

data class Movie(
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Summary")
    val summary: String,

    @SerializedName("Short Summary")
    val shortSummary: String,

    @SerializedName("Genres")
    val genres: String,

    @SerializedName("IMDBID")
    val imdbId: String,

    @SerializedName("Runtime")
    val runtime: String,

    @SerializedName("YouTube Trailer")
    val youTubeTrailer: String,

    @SerializedName("Rating")
    val rating: String,

    @SerializedName("Movie Poster")
    val moviePoster: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Writers")
    val writers: String,

    @SerializedName("Cast")
    val cast: String
)
