package blankthings.musicthing.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import blankthings.musicthing.data.models.Track;

/**
 * Created by iosif on 8/19/17.
 */

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistViewHolder> {

    private List<Track> tracks;

    public PlaylistAdapter() {
        tracks = new ArrayList<>();
    }


    public void setTracks(final List<Track> tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }


    public void addTracks(final List<Track> tracks) {
        this.tracks.addAll(tracks);
        notifyDataSetChanged();
    }


    @Override
    public PlaylistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PlaylistViewHolder(PlaylistViewHolder.inflateView(parent));
    }


    @Override
    public void onBindViewHolder(PlaylistViewHolder holder, int position) {
        holder.bind(tracks.get(position));
    }



    @Override
    public int getItemCount() {
        return tracks.size();
    }

}
