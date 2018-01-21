package com.xsl.lerist.llibrarys.application;

import android.app.Application;
import android.graphics.Typeface;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by Lerist on 2016/7/13, 0013.
 */

public class LApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    /**
     * 自定义字体
     * 顺序为: DEFAULT DEFAULT_BOLD SANS_SERIF SERIF MONOSPACE
     *
     * @param fontPaths 字体Assets路径("fonts/FZSongKeBenXiuKaiS-R-GB.TTF")
     */
    public void customFont(String... fontPaths) {
        ArrayList<Typeface> fontTypefaces = new ArrayList<>();
        for (String fontPath :
                fontPaths) {
            fontTypefaces.add(Typeface.createFromAsset(getAssets(), fontPath));
        }

        try {
            ArrayList<Field> fields = new ArrayList<>();
            fields.add(Typeface.class.getDeclaredField("DEFAULT"));
            fields.add(Typeface.class.getDeclaredField("DEFAULT_BOLD"));
            fields.add(Typeface.class.getDeclaredField("SANS_SERIF"));
            fields.add(Typeface.class.getDeclaredField("SERIF"));
            fields.add(Typeface.class.getDeclaredField("MONOSPACE"));
            for (int i = 0; i < fields.size(); i++) {
                Typeface typeface = fontTypefaces.size() > i ? fontTypefaces.get(i) : fontTypefaces.get(0);

                Field field = fields.get(i);
                field.setAccessible(true);
                field.set(null, typeface);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
