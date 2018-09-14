package com.moudle.mvpcore;

import android.os.Environment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2018/8/23.
 */

public class FileUtil {

    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";
    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getPath();

    public static File writeToDisk(InputStream is, String dir,String perx, String extension){
        File file = FileUtil.createFileByName(dir,perx,extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            byte[] bytes = new byte[1024*4];
            int len = 0;
            while((len = bis.read(bytes)) != -1){
               bos.write(bytes,0,len);
            }
            bos.flush();
            fos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(bos != null){
                    bos.close();
                }
                if(fos != null){
                    fos.close();
                }
                if(bis != null){
                    bis.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return file;
    }

    private static File createFileByName(String sdcardDieName,String timeFormatHeader,String extersion){
        final String fileName = getFileNameByTime(timeFormatHeader,extersion);
        return createFile(sdcardDieName,fileName);
    }

    private static File createFile(String sdcardDieName, String fileName) {
        return new File(createDir(sdcardDieName),fileName);
    }

    private static File createDir(String sdcardDieName) {
        final String url = SDCARD_DIR + "/" + sdcardDieName+"/";
        final File fileDir = new File(url);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }

    private static String getFileNameByTime(String timeFormatHeader, String extersion) {
        return getTimeFormatName(timeFormatHeader) + "." + extersion;
    }

    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        final SimpleDateFormat format = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return format.format(date);
    }

    public static File writeToDisk(InputStream inputStream, String downDir, String name) {
        final File file = FileUtil.createFile(downDir, name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(inputStream);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];

            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
            }

            bos.flush();
            fos.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    public static String getExtension(String filepath){
        String suffix = "";
        File file = new File(filepath);
        String name = file.getName();
        int index = name.lastIndexOf(".");
        if(index > 0){
            suffix = name.substring(index+1);
        }
        return suffix;
    }
}
