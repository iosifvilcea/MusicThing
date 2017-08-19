package blankthings.musicthing.data;

/**
 * Created by iosif on 8/19/17.
 */

public class Track {

    private String artist;
    private String song;
    private String url;

    public Track(final String artist, final String song, final String url) {
        this.artist = artist;
        this.song = song;
        this.url = url;
    }

    public String getArtist() {
        return artist;
    }

    public String getSong() {
        return song;
    }

    public String getUrl() {
        return url;
    }
}
