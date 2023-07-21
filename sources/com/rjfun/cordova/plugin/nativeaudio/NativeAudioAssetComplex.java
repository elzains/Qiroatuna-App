package com.rjfun.cordova.plugin.nativeaudio;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import java.io.IOException;
import java.util.concurrent.Callable;

public class NativeAudioAssetComplex implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private static final int INVALID = 0;
    private static final int LOOPING = 5;
    private static final int PENDING_LOOP = 4;
    private static final int PENDING_PLAY = 2;
    private static final int PLAYING = 3;
    private static final int PREPARED = 1;
    Callable<Void> completeCallback;

    /* renamed from: mp */
    private MediaPlayer f13mp = new MediaPlayer();
    private int state = 0;

    public NativeAudioAssetComplex(AssetFileDescriptor afd, float volume) throws IOException {
        this.f13mp.setOnCompletionListener(this);
        this.f13mp.setOnPreparedListener(this);
        this.f13mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        this.f13mp.setAudioStreamType(3);
        this.f13mp.setVolume(volume, volume);
        this.f13mp.prepare();
    }

    public void play(Callable<Void> completeCb) throws IOException {
        this.completeCallback = completeCb;
        invokePlay(false);
    }

    private void invokePlay(Boolean loop) {
        int i = 4;
        Boolean playing = Boolean.valueOf(this.f13mp.isPlaying());
        if (playing.booleanValue()) {
            this.f13mp.pause();
            this.f13mp.setLooping(loop.booleanValue());
            this.f13mp.seekTo(0);
            this.f13mp.start();
        }
        if (!playing.booleanValue() && this.state == 1) {
            if (!loop.booleanValue()) {
                i = 2;
            }
            this.state = i;
            onPrepared(this.f13mp);
        } else if (!playing.booleanValue()) {
            if (!loop.booleanValue()) {
                i = 2;
            }
            this.state = i;
            this.f13mp.setLooping(loop.booleanValue());
            this.f13mp.start();
        }
    }

    public boolean pause() {
        try {
            if (this.f13mp.isPlaying()) {
                this.f13mp.pause();
                return true;
            }
        } catch (IllegalStateException e) {
        }
        return false;
    }

    public void resume() {
        this.f13mp.start();
    }

    public void stop() {
        try {
            if (this.f13mp.isPlaying()) {
                this.state = 0;
                this.f13mp.pause();
                this.f13mp.seekTo(0);
            }
        } catch (IllegalStateException e) {
        }
    }

    public void setVolume(float volume) {
        try {
            this.f13mp.setVolume(volume, volume);
        } catch (IllegalStateException e) {
        }
    }

    public void loop() throws IOException {
        invokePlay(true);
    }

    public void unload() throws IOException {
        stop();
        this.f13mp.release();
    }

    public void onPrepared(MediaPlayer mPlayer) {
        if (this.state == 2) {
            this.f13mp.setLooping(false);
            this.f13mp.seekTo(0);
            this.f13mp.start();
            this.state = 3;
        } else if (this.state == 4) {
            this.f13mp.setLooping(true);
            this.f13mp.seekTo(0);
            this.f13mp.start();
            this.state = 5;
        } else {
            this.state = 1;
            this.f13mp.seekTo(0);
        }
    }

    public void onCompletion(MediaPlayer mPlayer) {
        if (this.state != 5) {
            this.state = 0;
            try {
                stop();
                if (this.completeCallback != null) {
                    this.completeCallback.call();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
