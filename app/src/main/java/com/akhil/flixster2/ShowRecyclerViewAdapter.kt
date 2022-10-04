package com.akhil.flixster

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.akhil.flixster2.DetailActivity
import com.akhil.flixster2.R
import com.akhil.flixster2.TVShow
import com.bumptech.glide.Glide



class ShowRecyclerViewAdapter(
    private val shows: List<TVShow>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<ShowRecyclerViewAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_now_playing_tv_shows, parent, false)
        return MovieViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView){
        var mItem: TVShow? = null
        val mShowTitle: TextView = mView.findViewById<View>(R.id.tvShowTitle) as TextView
        val mShowImage: ImageView = mView.findViewById<View>(R.id.tvShowImage) as ImageView
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val show = shows[position]

        holder.mItem = show
        holder.mShowTitle.text = show.title
        var showImage = "https://image.tmdb.org/t/p/w500"+ show.multimedia
        Glide.with(holder.mView)
            .load(showImage)
            .fitCenter()
            .into(holder.mShowImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { show ->
                mListener?.onItemClick(show)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return shows.size
    }
}