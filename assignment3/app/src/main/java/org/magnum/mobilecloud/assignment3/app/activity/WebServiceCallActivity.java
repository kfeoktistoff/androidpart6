package org.magnum.mobilecloud.assignment3.app.activity;

import android.app.Activity;
import com.octo.android.robospice.SpiceManager;
import org.magnum.mobilecloud.assignment3.app.service.VideoSpiceService;

/**
 * Created by Kirill Feoktistov on 04.09.15
 */

public class WebServiceCallActivity extends Activity {
    protected SpiceManager spiceManager = new SpiceManager(VideoSpiceService.class);

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
