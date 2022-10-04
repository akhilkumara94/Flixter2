package com.akhil.flixster

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akhil.flixster2.DetailActivity
import com.akhil.flixster2.R
import com.akhil.flixster2.TVShow
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray

private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
const val SHOW_EXTRA = "SHOW_EXTRA"

class ShowFragment : Fragment(), OnListFragmentInteractionListener {
    /*
     * Constructing the view
     */
    var models :List<TVShow>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_now_playing_tv_shows_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.recyclerView) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val asyncHttpClient = AsyncHttpClient()
        val requestParams = RequestParams()
        requestParams["api_key"] = API_KEY
        asyncHttpClient["https://api.themoviedb.org/3/tv/popular", requestParams, object : JsonHttpResponseHandler()
        {
            /*
             * The onSuccess function gets called when
             * HTTP response status is "200 OK"
             */
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                progressBar.hide()
                val resultJson : JSONArray = json.jsonObject.get("results") as JSONArray
                val moviesRawJSON : String = resultJson.toString()
                val gson = Gson()
                val arrayMovieType = object : TypeToken<List<TVShow>>() {}.type
                models = gson.fromJson(moviesRawJSON, arrayMovieType)

                recyclerView.adapter = models?.let { ShowRecyclerViewAdapter(it, this@ShowFragment) }
            }

            /*
             * The onFailure function gets called when
             * HTTP response status is "4XX" (eg. 401, 403, 404)
             */
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                errorResponse: String,
                t: Throwable?
            ) {
                progressBar.hide()
                t?.message?.let {
                    Log.e("TVFragment", errorResponse)
                }
            }
        }]
    }

    override fun onItemClick(item: TVShow) {
        Log.e("hello", item.summary.toString())
        val intent = Intent(this.context, DetailActivity::class.java)
        intent.putExtra(SHOW_EXTRA, item)
        startActivity(intent)

    }
}