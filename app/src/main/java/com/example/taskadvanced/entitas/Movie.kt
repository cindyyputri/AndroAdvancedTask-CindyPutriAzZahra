package com.example.taskadvanced.entitas

import kotlin.properties.Delegates

class Movie(
    private val title: String,
    private val poster_path: String,
    private val overview: String,
    private val release_date: String,
    private val popularity: Double,
    private val original_language: String
) {

    fun getTitle(): String {
        return title
    }

    fun getposter_path(): String {
        return poster_path
    }

    fun getOverview(): String {
        return overview
    }

    fun getrelease_date(): String {
        return release_date
    }

    fun getpopularity(): Double {
        return popularity
    }

    fun getOriginal_language(): String {
        return original_language
    }
}