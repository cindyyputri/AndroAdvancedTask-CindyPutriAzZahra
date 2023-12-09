package com.example.taskadvanced.view

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.taskadvanced.R

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val imageView = findViewById<ImageView>(R.id.poster_image)
        val title_tv = findViewById<TextView>(R.id.mTitle)
        val overview_tv = findViewById<TextView>(R.id.mOverview)
        val release_date = findViewById<TextView>(R.id.mRelease_date)
        val popularity = findViewById<TextView>(R.id.mPopular)
        val original_language = findViewById<TextView>(R.id.mLanguage)
        val bundle = intent.extras
        val mTitle = bundle!!.getString("title")
        val mPoster = bundle.getString("poster_path")
        val mOverview = bundle.getString("overview")
        val mRelease_date = bundle.getString("release_date")
        val mPopularity = bundle.getDouble("popularity")
        val mLanguage = bundle.getString("original_language")
        Glide.with(this).load("https://image.tmdb.org/t/p/w185/$mPoster").into(imageView)
        title_tv.text = mTitle
        overview_tv.text = mOverview
        release_date.text = mRelease_date
        popularity.text = mPopularity.toString()
        original_language.text = mLanguage
    }
}