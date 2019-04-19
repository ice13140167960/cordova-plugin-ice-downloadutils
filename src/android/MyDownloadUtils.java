package com.ice.plugin.downloadutils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MyDownloadUtils {

    public static void download(Context context,String url,String destinationPath){
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),destinationPath);
        if(file.exists()){
            file.delete();
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,destinationPath);
        long downloadId = downloadManager.enqueue(request);
    }

    public static JSONArray readFilePaths(String path){
        JSONArray jsonArray=new JSONArray();
        File directory=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),path);
        if(!directory.exists()){
            directory.mkdirs();
        }
        File[] files=directory.listFiles();
        for (File file : files) {
            jsonArray.put(file.getName());
        }
        return jsonArray;
    }
    
    public static String getDirectory(){
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    /**
     *
     * @param context
     * @param path
     * @param type image/*,video/*,application/vnd.ms-powerpoint
     */
    public static void openFile(Context context,String path,String type){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        File file=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),path);

        if(Build.VERSION.SDK_INT>=24){//绕过7.0的文件权限检查
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        Uri uri= Uri.fromFile(file);
        intent.setDataAndType(uri, type);
        context.startActivity(intent);
    }

}
