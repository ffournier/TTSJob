package com.android2ee.ttsjob.service;

import android.content.Context;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.util.Log;

public class AudioFocusHelper {
	
	private AudioManager mAudioManager;
	private Context mContext;
	 
	public AudioFocusHelper(Context ctx) {
		mContext = ctx;
        mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }
	
	public static boolean isOnPause(Context ctx) {
		AudioManager am = (AudioManager) ctx.getSystemService(Context.AUDIO_SERVICE);
		return am.getMode() != AudioManager.MODE_NORMAL;
	}

    public boolean requestFocus(OnAudioFocusChangeListener listener) {
        boolean result = AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
            mAudioManager.requestAudioFocus(listener, AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
        Log.i(getClass().getCanonicalName(), "requestFocus " + result);
        return result;
    }

    public boolean abandonFocus(OnAudioFocusChangeListener listener) {
        boolean result =  AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
            mAudioManager.abandonAudioFocus(listener);
        Log.i(getClass().getCanonicalName(), "abandonFocus " + result);
        return result;
    }
    
}
