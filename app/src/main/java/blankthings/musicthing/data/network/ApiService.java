package blankthings.musicthing.data.network;

import blankthings.musicthing.data.models.Track;
import retrofit2.Callback;

/**
 * Created by iosif on 8/20/17.
 */

public final class ApiService {

    private static ApiService api;
    private static final String URL = "";

    private RetrofitClient<Endpoints> retrofit;


    public static ApiService instance() {
        if (api == null) {
            api = new ApiService();
        }

        return api;
    }


    ApiService() {
        retrofit = new RetrofitClient<>(URL, Endpoints.class);
    }


    public void fetchTracks(final Callback<Track> callback) {
        retrofit.make().fetchTracks().enqueue(callback);
    }

}
