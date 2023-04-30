package com.example.workingwithjson

import android.os.Bundle
import android.service.voice.VoiceInteractionSession.VisibleActivityCallback
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.workingwithjson.Jsonfiles.Movie
import com.example.workingwithjson.Jsonfiles.MovieApi
import com.example.workingwithjson.Jsonfiles.MovieListResponse

import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class Fragment1 : Fragment() {


   lateinit var btn_showMore :Button
   lateinit var rv : RecyclerView
    private lateinit var adapter: MovieAdapter
    private val url1 = "http://task.auditflo.in"

    private var movieList: ArrayList<Movie> = arrayListOf()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_1, container, false)
        rv = view.findViewById(R.id.rv1)
        btn_showMore = view.findViewById(R.id.btn_showMore)
        rv.layoutManager = LinearLayoutManager(requireContext())
        adapter = MovieAdapter(ArrayList<Movie>())
        rv.adapter = adapter



        showMovies()
        btn_showMore.setOnClickListener {
            showMoreMovies()
            print("Enteres the fragment file")

        }

        rv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (totalItemCount <= (lastVisibleItem + 1)) {
                    btn_showMore.visibility= View.VISIBLE
                    btn_showMore.setOnClickListener { showMoreMovies() }
                } else {
                    btn_showMore.visibility = View.GONE
                }
            }
        })


        return view



    }

    fun showMovies() {
        Log.d("MovieFragment", "showMovies() called")
        val retrofit = Retrofit.Builder().baseUrl(url1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: MovieApi = retrofit.create(MovieApi :: class.java)
        val call: Call<MovieListResponse> = retrofitAPI.getMovies()
        call.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                Log.d("MovieFragment", "onResponse() called")
                if(response.isSuccessful) {
                    movieList.clear() // Clear the existing data
                    movieList.addAll(response.body()?.movieList ?: emptyList())
                    adapter.movieList = movieList as ArrayList<Movie>
                    Toast.makeText(requireContext(), "Movies loaded successfully!", Toast.LENGTH_SHORT).show()
                    activity?.runOnUiThread {
                        adapter.notifyDataSetChanged()
                    }

                    Toast.makeText(requireContext(), "Movies loaded successfully!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()
                Log.d("MovieFragment", "onFailure() called")
                try {
                    // Handle the exception here
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    fun showMoreMovies() {
        val retrofit = Retrofit.Builder().baseUrl(url1)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: MovieApi = retrofit.create(MovieApi::class.java)
        val call: Call<MovieListResponse> = retrofitAPI.getMoreMovies()

        call.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(call: Call<MovieListResponse>, response: Response<MovieListResponse>) {
                if (response.isSuccessful) {
                    val newMovies = response.body()?.movieList ?: emptyList()
                    movieList.addAll(newMovies)
                    adapter.notifyDataSetChanged()
                    Toast.makeText(requireContext(),"More movies Loaded Successfully!",Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_LONG).show()
            }
        })
    }}







