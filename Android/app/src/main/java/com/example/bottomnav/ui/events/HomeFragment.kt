package com.example.bottomnav.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        // Set up the RecyclerView with the adapter
        recyclerView = root.findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(context)
        // Assuming you have a method to get your list of events
        val events = getEventsList() // You should define this method to return a list of EventItem

        eventAdapter = EventAdapter(events)
        recyclerView.adapter = eventAdapter

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }

    // Here you'll have a method like this, or you'll obtain the events list from your ViewModel
    private fun getEventsList(): List<EventItem> {
        // This is where you would retrieve or generate your list of events
        return listOf(
                EventItem("Event 1", "Date 1", "Location 1"),
                EventItem("Event 2", "Date 2", "Location 2")
                // ... more events
        )
    }
}