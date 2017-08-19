package blankthings.musicthing.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by iosif on 8/19/17.
 */

public class PlayManager {

    private PlayManager() {
    }


    public static void playYoutube(final Context context, final String url) {
        final String ytAppUri = "vnd.youtube:";
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytAppUri + url));
        appIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        final String ytWebUri = "https://www.youtube.com/watch?v=";
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ytWebUri + url));
        webIntent.setFlags(FLAG_ACTIVITY_NEW_TASK);

        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }


    public static void playSoundcloud(final Context context, final String url) {

    }


    public static void playSpotify(final Context context, final String url) {

    }
}
