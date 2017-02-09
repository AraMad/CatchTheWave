package Sounds;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Arina on 11.01.2017.
 */

public class TrackManager {

    private static ArrayList<Track> tracklist = new ArrayList<Track>();

    public void addTrackToTracklist(Context context, int res_id, String name,
                                           boolean looped){
        tracklist.add(new Track(context, res_id, name, looped));
        tracklist.trimToSize();
    }

    public void remoweTrackFromTracklist(String track_name){
        for (Track track: tracklist) {
            if (track.getTrackName().equals(track_name)){
                tracklist.remove(track);
                break;
            }
        }
        tracklist.trimToSize();
    }

    public void clearTracklist(){
        tracklist.clear();
    }

    public void startPlayAllTrackInTracklist(){
        for (Track track: tracklist) {
            track.startPlayTrack();
        }
    }

    public void stopPlayAllTrackInTracklist(){
        for (Track track: tracklist) {
            track.stopPlayTrack();
        }
    }

    public void pausePlayAllTrackInTracklist(){
        for (Track track: tracklist) {
            track.pausePlayTrack();
        }
    }

    public void startPlayTrack(String track_name){
        for (Track track: tracklist) {
            if (track.getTrackName().equals(track_name)){
                track.startPlayTrack();
                break;
            }
        }
    }

    public void pausePlayTrack(String track_name){
        for (Track track: tracklist) {
            if (track.getTrackName().equals(track_name)){
                track.pausePlayTrack();
                break;
            }
        }
    }

    public void stopPlayTrack(String track_name){
        for (Track track: tracklist) {
            if (track.getTrackName().equals(track_name)){
                track.stopPlayTrack();
                break;
            }
        }
    }

    public void rewindAndPlayTrack(String track_name, int time_to_rewind_msec){
        for (Track track: tracklist) {
            if (track.getTrackName().equals(track_name)){
                track.rewindAndPlayTrack(time_to_rewind_msec);
                break;
            }
        }
    }
}
