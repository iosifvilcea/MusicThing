package blankthings.musicthing.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import blankthings.musicthing.R;
import blankthings.musicthing.data.models.Track;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by iosif on 8/19/17.
 */

public class PlaylistViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.artistTextView)
    TextView artist;

    @BindView(R.id.songTextView)
    TextView song;


    public static View inflateView(final ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_track_item, parent, false);
    }


    public PlaylistViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


    public void bind(final Track track) {
        song.setText(track.getSong());
        artist.setText(track.getArtist());
    }

}
