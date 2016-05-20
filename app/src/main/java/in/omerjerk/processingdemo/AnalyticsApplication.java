package in.omerjerk.processingdemo;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;


//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public class AnalyticsApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

//        Crashlytics crashlytics = new Crashlytics.Builder().disabled(isAnalyticsDisabled()).build();
//        Fabric.with(this, crashlytics);

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(getApplicationContext());
        analytics.setAppOptOut(isAnalyticsDisabled());
        analytics.setDryRun(isAnalyticsDisabled());
    }

    private boolean isAnalyticsDisabled() {
        return true;
    }
}
