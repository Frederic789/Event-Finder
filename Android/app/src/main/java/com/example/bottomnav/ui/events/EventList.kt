package com.example.bottomnav.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bottomnav.R


class EventList : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_event_list, container, false)

        // Initialize RecyclerView
        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewEvents)
      //  recyclerView = LinearLayoutManager(activity) // Use activity context

        // Initialize the adapter with an empty list or data from a ViewModel
        eventAdapter = EventAdapter(emptyList()) // Replace with actual data source
        recyclerView.adapter = eventAdapter

        return view
    }

    // Method to update data on adapter
    fun updateEvents(events: List<EventItem>) {
        eventAdapter = EventAdapter(events)
        recyclerView.adapter = eventAdapter
    }
}
