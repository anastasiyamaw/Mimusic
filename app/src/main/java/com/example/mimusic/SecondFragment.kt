package com.example.mimusic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondFragment : Fragment(), CellClickListener {

    val songs = arrayListOf(
        "Ной", "Money and the power", "Saving Me", "Unity",
        "Pieces", "The high road", "Leave a light on", "Сеть"
    )

    val authors = arrayListOf(
        "ATL", "Kid Ink", "Nickelback", "Shinedown",
        "Sum 41", "Three Days Grace", "Tom Walker", "Макс Корж"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // setHasOptionsMenu(true)
        // (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val view = inflater.inflate(R.layout.fragment_second, container, false)
        val layoutManager: RecyclerView.LayoutManager
        val adapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
        val recyclerView = view.findViewById<RecyclerView>(R.id.TracksRecycler)

        layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        adapter = RecyclerAdapter(songs, authors, this)
        recyclerView.adapter = adapter

        return view
    }

    override fun onCellClickListener(position: Int) {
        val bundle = Bundle()
        bundle.putInt("position", position)
        bundle.putString("song", songs[position])
        bundle.putString("author", authors[position])

        val fragment = MediaFragment()
        fragment.arguments = bundle
        findNavController().navigate(R.id.action_secondFragment_to_mediaFragment, bundle)
    }
}