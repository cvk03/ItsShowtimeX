package com.example.workingwithjson

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.workingwithjson.Jsonfiles.Movie
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieAdapter(var movieList : ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var card = itemView.findViewById<CardView>(R.id.cardview)
        var tv_title = itemView.findViewById<TextView>(R.id.tv_title)
        var tv_year = itemView.findViewById<TextView>(R.id.tv_year)
        var tv_runtime = itemView.findViewById<TextView>(R.id.tv_runtime)
        var tv_cast = itemView.findViewById<TextView>(R.id.tv_cast)
        var btn_addFav =itemView.findViewById<Button>(R.id.btn_addFav)
        var fab_del = itemView.findViewById<FloatingActionButton>(R.id.fab_del)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.eachmovie,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        var currMovie = movieList[position]
        holder.tv_title.text = "Movie : "+currMovie.title
        holder.tv_year.text = "Year : "+currMovie.year
        holder.tv_runtime.text= "Runtime : "+currMovie.runtime
        holder.tv_cast.text = "Cast : "+currMovie.cast
    }

    override fun getItemCount(): Int {
        Log.d("MovieAdapter", "getItemCount() called. Count: ${movieList.size}")
        return movieList.size
    }


}
/*class MovieAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var movieList = emptyList<MovieEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.eachmovie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount() = movieList.size

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tv_title)
        private val year: TextView = itemView.findViewById(R.id.tv_year)
        private val summary: TextView = itemView.findViewById(R.id.tv_runtime)
        private val poster: TextView = itemView.findViewById(R.id.tv_cast)
        private val favoriteButton: Button = itemView.findViewById(R.id.btn_addFav)

        fun bind(movie: MovieEntity) {
            title.text = movie.title
            year.text = movie.year
            summary.text = movie.summary
            poster.text = movie.cast

            favoriteButton.text = if (movie.isFavorite) "Remove from Favorites" else "Add to Favorites"
            favoriteButton.setOnClickListener {
                listener.onFavoriteClick(movie)
            }

            itemView.setOnClickListener {
                listener.onItemClick(movie)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(movie: MovieEntity)
        fun onFavoriteClick(movie: MovieEntity)
    }

    fun setData(movieList: List<MovieEntity>) {
        this.movieList = movieList
        notifyDataSetChanged()
    }
}*/

