package com.xsl.lerist.llibrarys.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import com.socks.library.KLog;

import java.io.File;

public class LPhotoSingleChooser {

    public interface Callback {
        void onSuccess(String photoPath);

        void onFailure();
    }

    public static void openCamera(Context context, final Callback callback) {
        //打开相机
        final File file = FileUtils.createFile(Environment.getExternalStorageDirectory()
                        + File.separator + Environment.DIRECTORY_DCIM + File.separator + "Camera",
                StringUtils.toTimeStr(System.currentTimeMillis(), "yyyyMMddhhmm") + ".jpg");
        if (file == null) return;
        Uri mTakePhotoUri = Uri.fromFile(file);
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mTakePhotoUri);

        LResultActivity.startActivityForResult(context, captureIntent, new LResultActivity.Callback() {
            @Override
            public void onSuccess(Intent result) {
                callback.onSuccess(file.getAbsolutePath());
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });

    }

    public static void openGallery(final Context context, final Callback callback) {
        //打开图片浏览器
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        LResultActivity.startActivityForResult(context, intent, new LResultActivity.Callback() {
            @Override
            public void onSuccess(Intent result) {
                Uri uri = result.getData();
                String path = FileUtils.getPath(context, uri);
                KLog.i(path);
                callback.onSuccess(path);
            }

            @Override
            public void onFailure() {
                callback.onFailure();
            }
        });

    }

    /**
     * 显示图片选择对话框
     *
     * @param context
     */
    public static void showDialog(final Context context, final Callback callback) {

        //构建对话框
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setItems(new String[]{"相册", "拍照"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    //打开图库
                    openGallery(context, callback);
                } else if (which == 1) {
                    //打开相机
                    openCamera(context, callback);
                }
            }
        });
        builder.create().show();
    }
}
