package com.example.bottomnav.ui.events

import android.Manifest
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bottomnav.R

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.content.pm.PackageManager






class HomeFragment : Fragment() {

    companion object {
        private const val PERMISSION_REQUEST_CODE = 100
    }


    private lateinit var homeViewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

        recyclerView = root.findViewById(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val events = getEventsList()

        eventAdapter = EventAdapter(events)
        recyclerView.adapter = eventAdapter

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 100)
        } else {
            getLastLocation()
        }

        return root
    }

    // Here you'll have a method like this, or you'll obtain the events list from your ViewModel
    private fun getLastLocation() {
        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    updateEventDistances(location)
                } else {
                    // Handle case where location is null
                }
            }
        } else {
            // Permission is not granted, request the permission
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), PERMISSION_REQUEST_CODE)
        }
    }


    private fun updateEventDistances(location: Location) {
        val updatedEvents = eventAdapter.events.map { event ->
            val distance = calculateDistance(location, event.locationLatitude, event.locationLongitude)
            event.copy(distanceFromUser = distance) // Use copy for data class
        }
        eventAdapter.events = updatedEvents
        eventAdapter.notifyDataSetChanged() // Notify the adapter to refresh the list
    }


    private fun calculateDistance(currentLocation: Location, lat: Double, long: Double): Float {
        val eventLocation = Location("").apply {
            latitude = lat
            longitude = long
        }
        return currentLocation.distanceTo(eventLocation) // Returns distance in meters
    }

    private fun getEventsList(): List<EventItem> {
        return listOf(
                EventItem("Event 1", "Date 1", "Location 1", 0.0, 0.0),
                EventItem("Event 2", "Date 2", "Location 2", 0.0, 0.0),
                EventItem("Event 3", "Date 3", "Location 3", 0.0, 0.0)
        )
    }
}