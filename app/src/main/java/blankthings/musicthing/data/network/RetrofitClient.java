package blankthings.musicthing.data.network;

import retrofit2.Retrofit;

/**
 * Created by iosif on 8/20/17.
 */

class RetrofitClient<T> {

    private T endpoints;

    private RetrofitClient() {
        throw new IllegalStateException("Use the 2 param constructor.");
    }


    RetrofitClient(final String url, final Class<T> endpoint) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();

        this.endpoints = retrofit.create(endpoint);
    }


    T make() {
        return endpoints;
    }

}
