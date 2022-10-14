package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.uc.week4retrofit.R
import com.uc.week4retrofit.model.SpokenLanguage


class MovieDubAdapter(private val dataSet: List<SpokenLanguage>) :
    RecyclerView.Adapter<MovieDubAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dubView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            dubView= view.findViewById(R.id.tv_spoken_language)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item



            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_playing_language, viewGroup, false)
        return ViewHolder(view)

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.dubView.text = dataSet[position].name
        if (dataSet[position].name.isEmpty()){
            viewHolder.dubView.setVisibility(View.GONE)
        }else{
            viewHolder.dubView.setVisibility(View.VISIBLE)
        }


    }


    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
