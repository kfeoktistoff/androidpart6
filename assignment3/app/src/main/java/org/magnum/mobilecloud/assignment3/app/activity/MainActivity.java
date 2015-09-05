package org.magnum.mobilecloud.assignment3.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import org.magnum.mobilecloud.assignment3.app.R;
import org.magnum.mobilecloud.assignment3.app.request.GetVideoListRequest;
import org.magnum.mobilecloud.assignment3.app.request.LikeVideoRequest;
import org.magnum.mobilecloud.assignment3.app.service.VideoSpiceService;

import java.util.List;


public class MainActivity extends Activity {
    SpiceManager spiceManager = new SpiceManager(VideoSpiceService.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        retrieveVideosList();
    }

    private void retrieveVideosList() {
        spiceManager.execute(new GetVideoListRequest(), new RequestListener<List>() {
            @Override
            public void onRequestFailure(SpiceException spiceException) {
                Toast.makeText(MainActivity.this, "Error getting videos list", Toast.LENGTH_SHORT).show();
                spiceException.printStackTrace();
            }

            @Override
            public void onRequestSuccess(List videoList) {
                // TODO show videos list
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
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }
}
