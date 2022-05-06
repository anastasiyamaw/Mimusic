package com.example.mimusic

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_media.*
import kotlinx.android.synthetic.main.fragment_media.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MediaFragment : Fragment() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_media, container, false)
        val args = this.arguments
        var position = args?.get("position")
        position = position.toString().toInt()

        val song = args?.get("song")
        val author = args?.get("author")
        view.SongName.text = song.toString()
        view.Author.text = author.toString()

        val allTracks = arrayListOf<Int>(R.raw.track2, R.raw.track3,
            R.raw.track4, R.raw.track5, R.raw.track6,
            R.raw.track7, R.raw.track8, R.raw.track9)

        val mp: MediaPlayer = MediaPlayer.create(requireActivity(), allTracks[position])

        view.seekbar.progress = 0
        view.seekbar.max = mp.duration

        view.playbtn.setOnClickListener{
            if (!mp.isPlaying) {
                mp.start()
                playbtn.setImageResource(R.drawable.pause)
            }else{
                mp.pause()
                playbtn.setImageResource(R.drawable.play1)
            }
        }

        view.seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if(changed){
                    mp.seekTo(pos)
                    view.startTime.text = format(mp.currentPosition)
                    view.endTime.text = format(mp.duration)
                }
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {

            }
            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            view.seekbar.progress = mp.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mp.setOnCompletionListener {
            playbtn.setImageResource(R.drawable.play1)
            view.seekbar.progress = 0
        }

        view.swipemusic.setOnClickListener{
            findNavController().navigate(R.id.action_mediaFragment_to_secondFragment)
        }
        return view
    }

    fun format(time: Int): String {
        val format: DateFormat = SimpleDateFormat("mm:ss", Locale.US)
        return format.format(Date(time.toLong()))
    }
}