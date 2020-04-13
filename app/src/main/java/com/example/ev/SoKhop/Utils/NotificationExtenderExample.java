package com.example.ev.SoKhop.Utils;

import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.onesignal.NotificationExtenderService;
import com.onesignal.OSNotificationDisplayedResult;
import com.onesignal.OSNotificationReceivedResult;

import java.math.BigInteger;

/**
 * Created by MSI on 11/1/2016.
 */

public class NotificationExtenderExample extends NotificationExtenderService {
    @Override
    protected boolean onNotificationProcessing(OSNotificationReceivedResult receivedResult) {
        OverrideSettings overrideSettings = new OverrideSettings();
        overrideSettings.extender = new NotificationCompat.Extender() {
            @Override
            public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
                // Sets the background notification color to Green on Android 5.0+ devices.
                return builder.setColor(new BigInteger("FF00FF00", 16).intValue());
            }
        };

        OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
        Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);

        return true;
    }

//    public class AJNotificationExtender extends NotificationExtenderService {
//        @Override
//        protected boolean onNotificationProcessing(OSNotificationReceivedResult notification) {
//            OverrideSettings overrideSettings = new OverrideSettings();
//            overrideSettings.extender = new NotificationCompat.Extender() {
//                @Override
//                public NotificationCompat.Builder extend(NotificationCompat.Builder builder) {
//                    // Sets the background notification color to Green on Android 5.0+ devices.
//                    return builder.setColor(new BigInteger("FF00FF00", 16).intValue());
//                }
//            };
//            if(SoKhopApplication.getmActivity() != null){
//                SoKhopApplication.getmActivity().showAlert(notification.payload.title, notification.payload.body);
//                return true;
//            }else {
//                OSNotificationDisplayedResult displayedResult = displayNotification(overrideSettings);
//                Log.d("OneSignalExample", "Notification displayed with id: " + displayedResult.androidNotificationId);
//            }
//            return false;
//        }
//    }

}
