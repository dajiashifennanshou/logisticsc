package com.xsl.distributor.ws.dialog;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.xsl.distributor.R;
import com.xsl.distributor.lerist.AppApplication;
import com.xsl.lerist.llibrarys.utils.FileUtils;
import com.xsl.lerist.llibrarys.utils.ImageFileUtils;
import com.xsl.lerist.llibrarys.utils.StringUtils;
import com.xsl.lerist.llibrarys.widget.LProgressDialog;

import java.io.File;
import java.util.List;

import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.permission.EasyPermissions;


/**
 * Created by Administrator on 2016/7/7 0007.
 */
public class PopDialog extends Dialog implements View.OnClickListener {

    private static final int PHOTO_MAX_WIDTH = 512;
    private static final int PHOTO_MAX_SIZE = 300;
    private static final int REQUEST_PERMISSIONS_CODE = 100;
    private final Callback callback;
    private String tempPhotoPath = AppApplication.PATH_TEMP + "/temp.jpg";
    private Message message;
    private Context context;
    private View dialogView;
    private Intent intent;
    private TextView startGroupChatBtn;
    private TextView createAnonymousGroupBtn;
    private String path;
    private TextView cancle;
    private Handler mMainHandler = new Handler(Looper.getMainLooper());

    public interface Callback {
        void onSuccess(String photoPath);

        void onFailure();
    }

    public PopDialog(Context context, Callback callback) {
        super(context, R.style.customer_dialog);
        this.context = context;
        this.callback = callback;

        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
        window.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(R.style.PopupAnimation);  //添加动画
        dialogView = LayoutInflater.from(context).inflate(R.layout.popuwindow_image, null);
        dialogView.setMinimumWidth((int) (displayRectangle.width() * 1f));
        dialogView.setMinimumHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(true);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dialogView);
        init();
        OnClickListener();
    }

    /**
     * 实例化
     */
    private void init() {
        startGroupChatBtn = (TextView) findViewById(R.id.start_group_chat_btn);
        createAnonymousGroupBtn = (TextView) findViewById(R.id.create_anonymous_group_btn);
        cancle = (TextView) findViewById(R.id.cancle);
    }

    /**
     * 监听
     */
    private void OnClickListener() {
        startGroupChatBtn.setOnClickListener(this);
        createAnonymousGroupBtn.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (!EasyPermissions.hasPermissions(context, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //未授权
            new AlertDialog.Builder(context).setTitle("提示")
                    .setMessage("未获取到[读写手机存储]权限, 是否需要手动授权? " +
                            "\n\n" +
                            "启用 [权限管理]->[读写手机存储] 权限\n")
                    .setNegativeButton("下次再说", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .setPositiveButton("去授权", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                context.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(Uri.fromParts("package", context.getPackageName(), null)));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }).show();
            return;
        }
        switch (v.getId()) {
            case R.id.start_group_chat_btn:

                FunctionConfig config1 = new FunctionConfig.Builder()
                        .setEnableCrop(true)
                        .build();
                GalleryFinal.openCamera(0, config1, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
                        if (resultList == null || resultList.isEmpty()) return;
                        PhotoInfo photoInfo = resultList.get(resultList.size() - 1);
                        final String photoPath = photoInfo.getPhotoPath();
                        if (!StringUtils.isEmpty(photoPath)) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    LProgressDialog.show(context, "");
                                    final File file = FileUtils.createFile(AppApplication.PATH_TEMP, "Temp" + System.currentTimeMillis() + ".jpg");

                                    if (file != null) {

                                        //压缩图片
                                        ImageFileUtils.resizeImageFile(photoPath, PHOTO_MAX_WIDTH, PHOTO_MAX_SIZE, file.getAbsolutePath());
                                        mMainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                callback.onSuccess(file.getAbsolutePath());
                                            }
                                        });
                                    }
                                    LProgressDialog.dismiss();
                                }
                            }).start();
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });
//                LPhotoSingleChooser.openCamera(context, new LPhotoSingleChooser.Callback() {
//                    @Override
//                    public void onSuccess(final String photoPath) {
//                        if (!StringUtils.isEmpty(photoPath)) {
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    LProgressDialog.show(context, "");
//                                    final File file = FileUtils.createFile(AppApplication.PATH_TEMP, "Temp" + System.currentTimeMillis() + ".jpg");
//
//                                    if (file != null) {
//
//                                        //压缩图片
//                                        ImageFileUtils.resizeImageFile(photoPath, PHOTO_MAX_WIDTH, PHOTO_MAX_SIZE, file.getAbsolutePath());
//                                        mMainHandler.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                callback.onSuccess(file.getAbsolutePath());
//                                            }
//                                        });
//                                    }
//                                    LProgressDialog.dismiss();
//                                }
//                            }).start();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        mMainHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                callback.onFailure();
//                            }
//                        });
//                    }
//                });
                dismiss();
                break;
            case R.id.create_anonymous_group_btn:

                final FunctionConfig config = new FunctionConfig.Builder()
                        .setEnableCrop(true)
                        .setEnableRotate(false)
                        .setEnableCamera(false)
                        .setEnableEdit(true)
                        .setForceCrop(true)
                        .setCropHeight(1080)
                        .setCropWidth(1080)
                        .build();

                GalleryFinal.openGallerySingle(0, config, new GalleryFinal.OnHanlderResultCallback() {
                    @Override
                    public void onHanlderSuccess(int reqeustCode, final List<PhotoInfo> resultList) {
                        if (resultList == null || resultList.isEmpty()) return;
                        PhotoInfo photoInfo = resultList.get(resultList.size() - 1);
                        final String photoPath = photoInfo.getPhotoPath();
                        if (!StringUtils.isEmpty(photoPath)) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    LProgressDialog.show(context, "");
                                    final File file = FileUtils.createFile(AppApplication.PATH_TEMP, "Temp" + System.currentTimeMillis() + ".jpg");

                                    if (file != null) {

                                        //压缩图片
                                        ImageFileUtils.resizeImageFile(photoPath, PHOTO_MAX_WIDTH, PHOTO_MAX_SIZE, file.getAbsolutePath());
                                        mMainHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                callback.onSuccess(file.getAbsolutePath());
                                            }
                                        });
                                    }
                                    LProgressDialog.dismiss();
                                }
                            }).start();
                        }
                    }

                    @Override
                    public void onHanlderFailure(int requestCode, String errorMsg) {

                    }
                });

//                LPhotoSingleChooser.openGallery(context, new LPhotoSingleChooser.Callback() {
//                    @Override
//                    public void onSuccess(final String photoPath) {
//                        if (!StringUtils.isEmpty(photoPath)) {
//                            new Thread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    LProgressDialog.show(context, "");
//                                    final File file = FileUtils.createFile(AppApplication.PATH_TEMP, "Temp" + System.currentTimeMillis() + ".jpg");
//                                    if (file != null) {    //压缩图片
//                                        ImageFileUtils.resizeImageFile(photoPath, PHOTO_MAX_WIDTH, PHOTO_MAX_SIZE, file.getAbsolutePath());
//                                        mMainHandler.post(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                callback.onSuccess(file.getAbsolutePath());
//                                            }
//                                        });
//                                    }
//                                    LProgressDialog.dismiss();
//                                }
//                            }).start();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure() {
//                        mMainHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                callback.onFailure();
//                            }
//                        });
//                    }
//                });
                dismiss();
                break;
            case R.id.cancle:
                dismiss();
        }
    }
}

