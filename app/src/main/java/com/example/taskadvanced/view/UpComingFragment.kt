package com.example.taskadvanced.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.taskadvanced.R
import com.example.taskadvanced.adapters.MovieAdapter
import com.example.taskadvanced.entitas.Movie
import com.example.taskadvanced.repositories.VolleySingleton
import org.json.JSONException
import org.json.JSONObject

class UpComingFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var requestQueue: RequestQueue
    private lateinit var movieList: MutableList<Movie>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_up_coming, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        recyclerView.setHasFixedSize(true)
        recyclerView.setLayoutManager(LinearLayoutManager(activity))
        requestQueue = VolleySingleton.getmInstance(requireContext())!!.getRequestQueue()
        movieList = ArrayList()
        fetchMovies()
        return view
    }

    private fun fetchMovies() {
        val url =
            "https://api.themoviedb.org/3/movie/upcoming?api_key=c5c96a6cdc8a22403898d9ae2308545d&language=en-US"
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    val movieArray = jsonObject.getJSONArray("results")
                    for (i in 0 until movieArray.length()) {
                        val movieObject = movieArray.getJSONObject(i)
                        val title = movieObject.getString("title")
                        val overview = movieObject.getString("overview")
                        val poster_path = movieObject.getString("poster_path")
                        val release_date = movieObject.getString("release_date")
                        val popularity = movieObject.getDouble("popularity")
                        val original_language = movieObject.getString("original_language")


                        // Tambahkan log untuk melacak nilai-nilai JSON
                        Log.d("NowPlayingFragment", "Title: $title")
                        Log.d("NowPlayingFragment", "Overview: $overview")
                        Log.d("NowPlayingFragment", "Poster Path: $poster_path")
                        Log.d("NowPlayingFragment", "Release Date: $release_date")
                        Log.d("NowPlayingFragment", "Popularity: $popularity")
                        Log.d(
                            "NowPlayingFragment",
                            "Original Language: $original_language"
                        )

                        // Buat objek Movie dan tambahkan ke list
                        val movie = Movie(
                            title,
                            poster_path,
                            overview,
                            release_date,
                            popularity,
                            original_language
                        )
                        movieList!!.add(movie)
                    }
                    val adapter = MovieAdapter(requireContext(), movieList!!)
                    recyclerView!!.adapter = adapter
                } catch (e: JSONException) {
                    e.printStackTrace()
                    // Tangani kesalahan penguraian JSON
                }
            }
        ) { error -> Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show() }
        requestQueue!!.add(stringRequest)
    }
}