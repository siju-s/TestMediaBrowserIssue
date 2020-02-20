package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import androidx.media.MediaBrowserServiceCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log


private const val TAG = "MainService"

class MainService : MediaBrowserServiceCompat() {

    private lateinit var mediaSession: MediaSessionCompat

    override fun onCreate() {
        super.onCreate()
        initMediaSession()
    }

    private fun initMediaSession() {
        mediaSession = MediaSessionCompat(this, TAG)
        sessionToken = mediaSession.sessionToken
        mediaSession.setFlags(
            MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS or
                    MediaSessionCompat.FLAG_HANDLES_QUEUE_COMMANDS
        )
        mediaSession.setCallback(mediaSessionCallback)
    }


    private val mediaSessionCallback = object : MediaSessionCompat.Callback() {

        override fun onPlay() {
            Log.e(TAG, "onPlay:")
        }

        override fun onPause() {
            Log.e(TAG, "onPause:")
        }

        override fun onMediaButtonEvent(mediaButtonEvent: Intent?): Boolean {
            Log.e(TAG, "onMediaButtonEvent:$mediaButtonEvent")
            return super.onMediaButtonEvent(mediaButtonEvent)
        }
    }


    override fun onGetRoot(p0: String, p1: Int, p2: Bundle?): BrowserRoot? {
        return BrowserRoot("test", null)
    }

    override fun onLoadChildren(p0: String, p1: Result<MutableList<MediaBrowserCompat.MediaItem>>) {

    }
}