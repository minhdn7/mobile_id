package mobileid.vnpt.vnptmobileid.app.service;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import mobileid.vnpt.vnptmobileid.app.utils.AppDef;
import mobileid.vnpt.vnptmobileid.app.utils.ConfigNotification;
import mobileid.vnpt.vnptmobileid.domain.repository.TinyDB;


/**
 * Created by MinhDN on 31/1/2018.
 */

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "FirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        //Displaying token on logcat
        Log.d(TAG, "Refreshed token ID: " + refreshedToken);
        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        //sendRegistrationToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent(ConfigNotification.REGISTRATION_COMPLETE);
        registrationComplete.putExtra("FIREBASE_TOKEN", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

//    private void sendRegistrationToServer(String token) {
//        //You can implement this method to store the token on your server
//        //Not required for current project
//    }

    private void storeRegIdInPref(String token) {
        TinyDB tinydb = new TinyDB(getBaseContext());
        tinydb.putString(AppDef.TOKEN_FIREBASE, token);
    }

}
