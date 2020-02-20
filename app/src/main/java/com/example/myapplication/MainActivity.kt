package com.example.myapplication

import android.content.ComponentName
import android.media.AudioManager
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.VideoView


private const val VIDEO_BASE_URI = "android.resource://"

class MainActivity : AppCompatActivity() {

    private lateinit var mediaBrowser: MediaBrowserCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMediaBrowser()
        setupVideoView()
    }

    private fun setupVideoView() {
        val videoView = findViewById<VideoView>(R.id.videoView)
        videoView.setAudioFocusRequest(AudioManager.AUDIOFOCUS_NONE)
        videoView.setMediaController(null)
        videoView.setVideoPath(VIDEO_BASE_URI + packageName + "/" + R.raw.test_video)
        videoView.setOnPreparedListener {
            it.start()
        }
    }

    private fun initMediaBrowser() {
        mediaBrowser = MediaBrowserCompat(applicationContext, ComponentName(this, MainService::class.java),
                mediaBrowserConnectionCallback, null)
        mediaBrowser.connect()
    }


    private val mediaBrowserConnectionCallback = object : MediaBrowserCompat.ConnectionCallback() {


        override fun onConnected() {
            Log.e(this@MainActivity.javaClass.simpleName, "onConnected")

        }
    }
}
