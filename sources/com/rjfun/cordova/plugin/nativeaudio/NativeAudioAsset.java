package com.rjfun.cordova.plugin.nativeaudio;

import android.content.res.AssetFileDescriptor;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class NativeAudioAsset {
    private int playIndex = 0;
    private ArrayList<NativeAudioAssetComplex> voices = new ArrayList<>();

    public NativeAudioAsset(AssetFileDescriptor afd, int numVoices, float volume) throws IOException {
        numVoices = numVoices < 0 ? 1 : numVoices;
        for (int x = 0; x < numVoices; x++) {
            this.voices.add(new NativeAudioAssetComplex(afd, volume));
        }
    }

    public void play(Callable<Void> completeCb) throws IOException {
        this.voices.get(this.playIndex).play(completeCb);
        this.playIndex++;
        this.playIndex %= this.voices.size();
    }

    public boolean pause() {
        boolean wasPlaying = false;
        for (int x = 0; x < this.voices.size(); x++) {
            wasPlaying |= this.voices.get(x).pause();
        }
        return wasPlaying;
    }

    public void resume() {
        if (this.voices.size() > 0) {
            this.voices.get(0).resume();
        }
    }

    public void stop() {
        for (int x = 0; x < this.voices.size(); x++) {
            this.voices.get(x).stop();
        }
    }

    public void loop() throws IOException {
        this.voices.get(this.playIndex).loop();
        this.playIndex++;
        this.playIndex %= this.voices.size();
    }

    public void unload() throws IOException {
        stop();
        for (int x = 0; x < this.voices.size(); x++) {
            this.voices.get(x).unload();
        }
        this.voices.removeAll(this.voices);
    }

    public void setVolume(float volume) {
        for (int x = 0; x < this.voices.size(); x++) {
            this.voices.get(x).setVolume(volume);
        }
    }
}
