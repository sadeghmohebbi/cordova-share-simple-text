/**
 *created by sadegh mohebbi
 */
package com.sadegh;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class ShareText extends CordovaPlugin {

    private static final String KEY_SHARE = "share";
    private static final String TAG = "ShareText";

    private static final int ACTIVITY_CODE_SEND__BOOLRESULT = 1;
    private static final int ACTIVITY_CODE_SEND__OBJECT = 2;  

    final CordovaPlugin plugin = this;

    public ShareText() {}

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);

        Log.d(TAG, "Initializing ShareText Plugin");
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
        String phrase = null;
        if(action.equals(KEY_SHARE)) {
            phrase = args.getString(0);
            Log.d(TAG, phrase);

            if (phrase != null) {
                try {
                    // Context context = this.cordova.getActivity().getApplicationContext();
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(Intent.EXTRA_TEXT, phrase);
                    intent.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());

                    cordova.getActivity().runOnUiThread(new Runnable() {
                      public void run() {
                        mycordova.startActivityForResult(plugin, Intent.createChooser(sendIntent, "share text via"), 0);
                      }
                    });

                } catch (Exception e) {
                    Log.i(TAG, e.getLocalizedMessage());
                }
            }
        }
        return true;
    }
}