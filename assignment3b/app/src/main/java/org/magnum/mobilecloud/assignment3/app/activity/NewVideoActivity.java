package org.magnum.mobilecloud.assignment3.app.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import org.magnum.mobilecloud.assignment3.app.R;
import org.magnum.mobilecloud.assignment3.app.model.Video;
import org.magnum.mobilecloud.assignment3.app.request.AddVideoRequest;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class NewVideoActivity extends WebServiceCallActivity {
    private Button uploadNewVideo;
    private EditText newVideoName;
    private EditText newVideoLink;
    private EditText newVideoDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_video);

        newVideoName = (EditText)findViewById(R.id.newVideoName);
        newVideoLink = (EditText)findViewById(R.id.newVideoLink);
        newVideoDuration = (EditText)findViewById(R.id.newVideoDuration);

        uploadNewVideo = (Button)findViewById(R.id.uploadNewVideo);
        uploadNewVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Video video = new Video(
                        newVideoName.getText().toString(),
                        newVideoLink.getText().toString(),
                        Long.parseLong(newVideoDuration.getText().toString()),
                        0
                );

                spiceManager.execute(new AddVideoRequest(video), new RequestListener<Video>() {
                    @Override
                    public void onRequestFailure(SpiceException spiceException) {
                        onWebServiceError(spiceException);
                    }

                    @Override
                    public void onRequestSuccess(Video video) {
                        finish();
                    }
                });
            }
        });
    }
}
