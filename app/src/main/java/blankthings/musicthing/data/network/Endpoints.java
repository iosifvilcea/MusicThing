package blankthings.musicthing.data.network;

import blankthings.musicthing.data.models.Track;
import retrofit2.Call;

/**
 * Created by iosif on 8/20/17.
 */

interface Endpoints {

    Call<Track> fetchTracks();

}
