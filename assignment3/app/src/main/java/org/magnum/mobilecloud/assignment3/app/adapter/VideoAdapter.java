package org.magnum.mobilecloud.assignment3.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import org.magnum.mobilecloud.assignment3.app.model.Video;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class VideoAdapter extends BaseAdapter {
    private List<Video> videos = new ArrayList<Video>();
    private Context context;

    public VideoAdapter(Context context, List<Video> videos) {
        this.videos = videos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return videos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
