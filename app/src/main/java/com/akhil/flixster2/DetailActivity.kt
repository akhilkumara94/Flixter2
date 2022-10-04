package com.akhil.flixster2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"
const val SHOW_EXTRA = "SHOW_EXTRA"
class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var abstractTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mediaImageView = findViewById(R.id.mediaImage)
        titleTextView = findViewById(R.id.mediaTitle)
        dateTextView = findViewById(R.id.mediaDate)
        abstractTextView = findViewById(R.id.mediaAbstract)

        val show = intent.getSerializableExtra(SHOW_EXTRA) as TVShow

        titleTextView.text = show.title
        dateTextView.text = show.first_air_date
        abstractTextView.text = show.summary

        var showImage = "https://image.tmdb.org/t/p/w500"+ show.multimedia

        Glide.with(this)
            .load(showImage)
            .into(mediaImageView)
    }
}