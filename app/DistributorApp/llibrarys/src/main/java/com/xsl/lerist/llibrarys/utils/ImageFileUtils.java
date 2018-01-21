package com.xsl.lerist.llibrarys.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageFileUtils {

    public static void resizeImageFile(String imgFilePath, int maxWidth,
                                       int maxSize,
                                       String savePath) {
        resizeImageFile(imgFilePath, maxWidth, maxSize, savePath, false);
    }

    /**
     * 重设图片文件大小
     *
     * @param imgFilePath
     * @param maxWidth
     * @param maxSize
     * @param savePath
     * @param isDelSourceFile 是否删除原文件
     */
    public static void resizeImageFile(String imgFilePath, int maxWidth, int maxSize,
                                       String savePath, boolean isDelSourceFile) {
        if (StringUtils.isEmpty(imgFilePath)) return;

        if (StringUtils.isEmpty(savePath)) {
            savePath = imgFilePath;
        }

        Bitmap bitmap = BitmapFactory.decodeFile(imgFilePath);
        Bitmap newBitmap = resizeBitmap(bitmap, maxWidth);
        if (maxSize > 0) {
            //质量压缩
            Bitmap compressBitmap = compressBitmap(newBitmap, maxSize, savePath);
        } else {
            try {
                ImageUtils.saveImage(savePath, newBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (isDelSourceFile) FileUtils.deleteFile(imgFilePath);


    }

    /**
     * 根据质量压缩
     *
     * @param bitmap
     * @param savePath
     * @return
     */
    public static Bitmap compressBitmap(Bitmap bitmap, int maxSize, String savePath) {

        if (bitmap == null) return null;

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int quality = 90;
        while (baos.toByteArray().length / 1024 > maxSize && quality >= 0) {  //循环判断如果压缩后图片是否大于maxSize kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);//这里压缩options%，把压缩后的数据存放到baos中
            quality -= 10;//每次都减少10
        }

        if (StringUtils.isEmpty(savePath) == false) {
            //保存到文件
            try {
                FileOutputStream fos = new FileOutputStream(savePath);
                byte[] bytes = baos.toByteArray();
                fos.write(bytes);
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap newBitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        bitmap.recycle();
        return newBitmap;
    }

    /**
     * 设置位图大小
     *
     * @param bitmap
     * @param maxWidth 指定最大宽度(压缩图片)
     * @return
     */
    public static Bitmap resizeBitmap(Bitmap bitmap, int maxWidth) {
        if (bitmap == null) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        //如果原图片比指定宽度小则不压缩
        if (width <= maxWidth) {
            return bitmap;
        }
        float temp = ((float) height) / ((float) width);
        int newHeight = (int) ((maxWidth) * temp);
        float scaleWidth = ((float) maxWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);
        // matrix.postRotate(45);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        bitmap.recycle();
        return resizedBitmap;
    }
}
