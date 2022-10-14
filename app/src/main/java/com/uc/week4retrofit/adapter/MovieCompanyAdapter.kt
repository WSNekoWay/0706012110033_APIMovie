package com.uc.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.uc.week4retrofit.R
import com.uc.week4retrofit.helper.Const
import com.uc.week4retrofit.model.ProductionCompany


class MovieCompanyAdapter(private val dataSet: List<ProductionCompany>) :
    RecyclerView.Adapter<MovieCompanyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val prodView: ImageView
        val prodTemp : TextView

        init {
            // Define click listener for the ViewHolder's View.
            prodView = view.findViewById(R.id.img_company)
            prodTemp = view.findViewById(R.id.img_company_text)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.card_playing_company, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        if (dataSet[position].logo_path != null) {
            Glide.with(viewHolder.itemView.context)
                .load(Const.IMG_URL + dataSet[position].logo_path)
                .into(viewHolder.prodView)
            viewHolder.prodTemp.text=""
        }else{
            Glide.with(viewHolder.itemView.context)
                .load(Const.IMG_URL + dataSet[position].logo_path)
                .into(viewHolder.prodView)
            viewHolder.prodTemp.text=dataSet[position].name
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}



