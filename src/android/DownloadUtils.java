package cordova-plugin-ice-downloadutils;

import com.ice.downloaddemo.MyDownloadUtils;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class DownloadUtils extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("download")) {
            JSONObject jsonObject=args.getJSONObject(0);
            MyDownloadUtils.download(cordova.getActivity(),jsonObject.getString("url"),jsonObject.getString("destinationPath"));
            return true;
        }else if(action.equals("readFilePaths")){
            JSONArray jsonArray=MyDownloadUtils.readFilePaths(args.getString(0));
            callbackContext.success(jsonArray.toString())
            return true;
        }else if(action.equals("openFile")){
            JSONObject jsonObject=args.getJSONObject(0);
            MyDownloadUtils.openFile(cordova.getActivity(),jsonObject.getString("path"),jsonObject.getString("type"));
            return true;
        }
        return false;
    }

//    private void coolMethod(String message, CallbackContext callbackContext) {
//        if (message != null && message.length() > 0) {
//            callbackContext.success(message);
//        } else {
//            callbackContext.error("Expected one non-empty string argument.");
//        }
//    }
}
