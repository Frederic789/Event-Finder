package com.example.bottomnav.ui.events

//import EventItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.R
import com.example.bottomnav.R.id.*


class EventAdapter(private val events: List<EventItem>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.titleTextView)
        val dateTextView: TextView = view.findViewById(R.id.dateTextView)
        val locationTextView: TextView = view.findViewById(R.id.locationTextView)
        val viewDetailsButton: Button = view.findViewById(R.id.viewDetailsButton)

        fun bind(event: EventItem) {
            titleTextView.text = event.title
            dateTextView.text = event.date
            locationTextView.text = event.location
        }

        init {
            viewDetailsButton.setOnClickListener {
                // Handle click event
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount(): Int {
        return events.size
    }
}

