/**
 */
package com.sadegh;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

import java.util.Date;

public class ShareText extends CordovaPlugin {
  private static final String KEY_SHARE = "share";
  private static final String TAG = "ShareText";

  public ShareText() {}

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing ShareText Plugin");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals(KEY_SHARE)) {
      try {
        String phrase = args.getString(0);
      } catch(JSONException e) {
        Log.i(TAG, e.getLocalizedMessage());
        continue;
      }

      try {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        goToOffline.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Intent.EXTRA_TEXT, phrase);
        intent.setPackage(this.cordova.getActivity().getApplicationContext().getPackageName());
        this.cordova.startActivity(Intent.createChooser(intent, "Share this text via"));
      } catch (Exception e) {
        Log.i(TAG, e.getLocalizedMessage())
      }

      Log.d(TAG, phrase);
    }
    return true;
  }
}
