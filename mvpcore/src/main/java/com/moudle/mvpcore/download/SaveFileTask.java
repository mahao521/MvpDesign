package com.moudle.mvpcore.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.moudle.mvpcore.FileUtil;
import com.moudle.mvpcore.app.ProjectInit;
import com.moudle.mvpcore.callback.IRequest;
import com.moudle.mvpcore.callback.ISuccess;

import java.io.File;
import java.io.InputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2018/8/23.
 */

public class SaveFileTask extends AsyncTask<Object,Void,File> {
    private  IRequest mIRequest;
    private ISuccess mISuccess;

    public SaveFileTask(IRequest IRequest, ISuccess ISuccess) {
        mIRequest = IRequest;
        mISuccess = ISuccess;
    }

    @Override
    protected File doInBackground(Object... objects) {
        String downDir = (String) objects[0];
        String extension = (String) objects[1];
        ResponseBody body = (ResponseBody) objects[2];
        String name = (String) objects[3];
        InputStream inputStream = body.byteStream();
        if(downDir == null || downDir.equals("")){
            downDir = "down_loads";
        }
        if(extension == null){
            extension = "";
        }
        if(name == null){
            return FileUtil.writeToDisk(inputStream,downDir,extension.toUpperCase(),extension);
        }else{
            return FileUtil.writeToDisk(inputStream,downDir,name);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if(mISuccess != null){
            mISuccess.onSuccess(file.getPath());
        }

        if(mIRequest != null){
            mIRequest.onRequestEnd();
        }

        //下载完成，直接安装
        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if(FileUtil.getExtension(file.getPath()).equals(".apk")){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            ProjectInit.getApplicationContext().startActivity(intent);
        }

    }
}
