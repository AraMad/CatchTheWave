package Sounds;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Arina on 11.01.2017.
 */

public class Track {

    private MediaPlayer track;
    private String track_name;

    public Track(Context context, int res_id, String track_name, boolean looping) {
        this.track = MediaPlayer.create(context, res_id);
        this.track_name = track_name;
        track.setLooping(looping);
    }

    public String getTrackName(){
        return track_name;
    }

    public void startPlayTrack(){
        track.seekTo(0);
        track.start();
    }

    public  void stopPlayTrack(){
        track.stop();
    }

    public void  pausePlayTrack(){
        track.pause();
    }

    public void rewindAndPlayTrack(int time_ofrewind_msec){
        if (track.isPlaying()){
            track.stop();
            track.seekTo(time_ofrewind_msec);
            track.start();
        } else {
            track.seekTo(time_ofrewind_msec);
            track.start();
        }
    }
}
