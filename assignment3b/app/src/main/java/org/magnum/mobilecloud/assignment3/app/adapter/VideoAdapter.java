package org.magnum.mobilecloud.assignment3.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import org.magnum.mobilecloud.assignment3.app.R;
import org.magnum.mobilecloud.assignment3.app.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class VideoAdapter extends ArrayAdapter<Video> {

    private int mCurrSelected = -1;
    private List<Video> videos = new ArrayList<Video>();
    private Context context;

    public VideoAdapter(Context context, List<Video> videos) {
        super(context, android.R.layout.simple_list_item_1, videos);
        this.videos = videos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Video getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return videos.get(position).getId();
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = lInflater.inflate(R.layout.video, parent, false);
        TextView tName = (TextView) rowView.findViewById(R.id.name);
        tName.setText(getVideoDisplayName(getItem(position)));

        return rowView;
    }

    private String getVideoDisplayName(Video item) {
        return item.getName() + "\nLikes: " + item.getLikes();
    }
}
