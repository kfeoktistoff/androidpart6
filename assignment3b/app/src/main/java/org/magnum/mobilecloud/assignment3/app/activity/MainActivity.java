package org.magnum.mobilecloud.assignment3.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import org.magnum.mobilecloud.assignment3.app.R;
import org.magnum.mobilecloud.assignment3.app.adapter.VideoAdapter;
import org.magnum.mobilecloud.assignment3.app.model.Video;
import org.magnum.mobilecloud.assignment3.app.request.GetVideoLikedBy;
import org.magnum.mobilecloud.assignment3.app.request.GetVideoListRequest;
import org.magnum.mobilecloud.assignment3.app.request.LikeVideoRequest;
import org.magnum.mobilecloud.assignment3.app.request.UnlikeVideoRequest;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends WebServiceCallActivity {
    private ListView lvVideos;
    private List<Video> videosList = new ArrayList<Video>();
    private Button uploadButton;
    private Button refreshButton;
    private Button likeButton;
    private Button unlikeButton;
    private Video selectedVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        refreshVideoList();
    }

    private void initUI() {
        lvVideos = (ListView) findViewById(R.id.videos);
        lvVideos.setAdapter(new VideoAdapter(this, videosList));
        lvVideos.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        lvVideos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedVideo = (Video) lvVideos.getAdapter().getItem(position);
                likeButton.setEnabled(true);
                unlikeButton.setEnabled(true);
                getLikedBy(selectedVideo);
            }
        });

        refreshButton = (Button) findViewById(R.id.refresh);
        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshVideoList();
            }
        });

        likeButton = (Button) findViewById(R.id.like);
        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeVideo();
            }
        });

        unlikeButton = (Button) findViewById(R.id.unlike);
        unlikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unlikeVideo();
            }
        });

        uploadButton = (Button) findViewById(R.id.upload);
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NewVideoActivity.class));
            }
        });

        likeButton.setEnabled(lvVideos.isSelected());
        unlikeButton.setEnabled(lvVideos.isSelected());
    }

    private void refreshVideoList() {
        spiceManager.execute(new GetVideoListRequest(), new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                onWebServiceError(spiceException);
            }

            @Override
            @SuppressWarnings("unchecked")
            public void onRequestSuccess(List videosList) {
                MainActivity.this.videosList.clear();
                MainActivity.this.videosList.addAll(videosList);
                lvVideos.invalidateViews();
            }
        });
    }

    private void likeVideo() {
        spiceManager.execute(new LikeVideoRequest(selectedVideo), new RequestListener<Void>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                onWebServiceError(spiceException);
            }

            @Override
            public void onRequestSuccess(Void aVoid) {
                refreshVideoList();
            }
        });
    }

    private void unlikeVideo() {
        spiceManager.execute(new UnlikeVideoRequest(selectedVideo), new RequestListener<Void>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(MainActivity.this, "Error getting video list", Toast.LENGTH_SHORT).show();
                spiceException.printStackTrace();
            }

            @Override
            public void onRequestSuccess(Void aVoid) {
                refreshVideoList();
            }
        });
    }

    private void getLikedBy(final Video video) {
        spiceManager.execute(new GetVideoLikedBy(selectedVideo), new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                onWebServiceError(spiceException);
            }

            @Override
            public void onRequestSuccess(List list) {
                for (Video someVideo : videosList) {
                    if (someVideo.equals(video)) {
                        someVideo.setName(someVideo.getName() + " Liked by: " + list.toString());
                        break;
                    }
                }

                lvVideos.invalidateViews();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshVideoList();
    }

}
