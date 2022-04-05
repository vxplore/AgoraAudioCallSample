package com.vxplore.agoraaudiocall

import android.app.Activity
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.SoundPool
import android.os.Build
import androidx.appcompat.app.AppCompatActivity

class SoundBox {
    companion object{
        private var instance: SoundBox? = null
        fun play(id: Int){
            instance?.play(id)
        }
    }
    private var soundsIds = mutableMapOf<Int,Int>()
    private var soundPool: SoundPool? = null
    private var volume = 0f
    private var loaded = false
    private var audioManager: AudioManager? = null
    fun initialize(context: Activity, vararg soundIds: Int){
        audioManager = context.getSystemService(AppCompatActivity.AUDIO_SERVICE) as AudioManager
        val currentVolumeIndex =
            audioManager!!.getStreamVolume(AudioManager.STREAM_MUSIC).toFloat()
        val maxVolumeIndex =
            audioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC).toFloat()
        volume = currentVolumeIndex / maxVolumeIndex
        context.volumeControlStream = AudioManager.STREAM_MUSIC
        if (Build.VERSION.SDK_INT >= 21) {
            val audioAttrib = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()
            val builder = SoundPool.Builder()
            builder.setAudioAttributes(audioAttrib).setMaxStreams(5)
            soundPool = builder.build()
        } else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            soundPool = SoundPool(5, AudioManager.STREAM_MUSIC, 0)
        }
        soundPool?.setOnLoadCompleteListener { soundPool, sampleId, status -> loaded = true }
        soundIds.forEach {
            val id = soundPool!!.load(context, it, 1)
            this.soundsIds[it] = id
        }
        instance = this
    }
    fun play(id: Int){
        if (loaded) {
            val leftVolumn = volume
            val rightVolumn = volume
            val streamId = soundPool?.play(soundsIds[id]?:return, leftVolumn, rightVolumn, 1, 0, 1f)
        }
    }
    fun destroy(){
        audioManager = null
        soundsIds.clear()
        instance = null
    }
}