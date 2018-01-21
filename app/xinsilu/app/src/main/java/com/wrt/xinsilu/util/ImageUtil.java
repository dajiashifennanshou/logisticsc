package com.wrt.xinsilu.util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wrt.xinsilu.MyApplication;
import com.wrt.xinsilu.dialog.PopDialog;
import com.xsl.lerist.llibrarys.utils.FileUtils;


/**
 * Created by Administrator on 2016/8/11 0011.
 */
public class ImageUtil {
    private PopDialog dialog;
    private Context context;
    public ImageUtil(Context context){
        this.context = context;
    }
    /**
     * @param path          图片路径
     * @param pic           图片控件
     * @param delete_button 删除按钮
     */
    public void ChoiceImg(final String path, final ImageView pic, final android.widget.ImageView delete_button) {
        dialog = new PopDialog(context, new PopDialog.Callback() {
            @Override
            public void onSuccess(final String photoPath) {
                FileUtils.renameFile(photoPath, MyApplication.PATH_TEMP + "/" + path);
                bingImg(pic, "file://" + MyApplication.PATH_TEMP + "/" + path);
                delete_button.setVisibility(View.VISIBLE);
//                        uploadImg(photoPath1, updateIdPic);
            }

            @Override
            public void onFailure() {
                bingImg(pic, "");
                delete_button.setVisibility(View.GONE);
                pic.setTag(null);
            }
        });
        dialog.show();
    }

    /**
     * 删除图片
     * @param img
     * @param delete
     */
    public void deleteImage(ImageView img, android.widget.ImageView delete) {
        bingImg(img, "");
        delete.setVisibility(View.GONE);
        img.setTag(null);
    }

    /**
     * 绑定图片
     * @param imageView
     * @param filePath
     */
    public void bingImg(android.widget.ImageView imageView, String filePath) {
        imageView.setTag(null);
        Glide.with(context).load(filePath).centerCrop().crossFade()
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        imageView.setTag(filePath.replace("file://",""));
    }
}
