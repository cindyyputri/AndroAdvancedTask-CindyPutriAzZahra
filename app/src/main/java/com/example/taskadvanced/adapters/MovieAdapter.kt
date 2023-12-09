package com.example.taskadvanced.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taskadvanced.R
import com.example.taskadvanced.entitas.Movie
import com.example.taskadvanced.view.DetailActivity

class MovieAdapter(private val context: Context, private val movieList: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MovieHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return MovieHolder(view)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = movieList[position]
        holder.release_date.text = movie.getrelease_date()
        holder.title.text = movie.getTitle()
        holder.overview.text = movie.getOverview()
        Glide.with(context).load("https://image.tmdb.org/t/p/w185/" + movie.getposter_path())
            .into(holder.imageView)
        holder.constraintLayout.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            val bundle = Bundle()
            bundle.putString("title", movie.getTitle())
            bundle.putString("overview", movie.getOverview())
            bundle.putString("release_date", movie.getrelease_date())
            bundle.putString("poster_path", movie.getposter_path())
            bundle.putDouble("popularity", movie.getpopularity()!!)
            bundle.putString("original_language", movie.getOriginal_language())
            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageView
        var title: TextView
        var overview: TextView
        var release_date: TextView
        var constraintLayout: ConstraintLayout

        init {
            imageView = itemView.findViewById(R.id.imageview)
            title = itemView.findViewById(R.id.title_tv)
            overview = itemView.findViewById(R.id.overview_tv)
            release_date = itemView.findViewById(R.id.release_date)
            constraintLayout = itemView.findViewById(R.id.main_layout)
        }
    }

    fun clear() {
        movieList.clear()
        notifyDataSetChanged()
    }

    fun addAll(movies: List<Movie>?) {
        movieList.addAll(movies!!)
        notifyDataSetChanged()
    }
}

