package blankthings.musicthing.data;

import blankthings.musicthing.data.models.Track;

/**
 * Created by iosif on 8/19/17.
 */

public class PlaylistData {

    private final Track[] tracks;

    public PlaylistData() {
        tracks = new Track[2];

        tracks[0] = new Track("J Balvin", "Buscando Huellas", "9QvalEyBiC4");
        tracks[1] = new Track("J Balvin", "Mi Gente", "wnJ6LuUFpMo");
    }


    public Track[] getSongs() {
        return tracks;
    }

}
