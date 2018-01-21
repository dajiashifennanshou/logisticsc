package com.brightsoft.utils;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

public class CodeImageUtil{
    
    private CodeImageUtil(){
        super();
        // TODO Auto-generated constructor stub
    }

    public static BufferedImage createCodeImg(String codeNumber) {
        int width = 100;
        int height = 40;
        //创建BufferedImage类的对象
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);    
        Graphics g = image.getGraphics(); // 创建Graphics类的对象
        Random random = new Random(); // 实例化一个Random对象
        Font mFont = new Font("华文宋体", Font.BOLD, 30); // 通过Font构造字体
        g.setColor(getRandColor(200, 250)); // 改变图形的当前颜色为随机生成的颜色
        g.fillRect(0, 0, width, height); // 绘制一个填色矩形
        // 画干扰线
        for (int i = 1; i <= 3; i++) {
            Color color = new Color(20 + random.nextInt(110),
                                    20 + random.nextInt(110), 20 + random.nextInt(110));
            g.setColor(color);
            g.drawLine(random.nextInt(width), random.nextInt(height),
                       random.nextInt(width), random.nextInt(height));
        }

        // 输出随机的验证文字
        g.setFont(mFont);
        int itmp = 0;
        for (int i = 0; i < codeNumber.length(); i++) {
            itmp = codeNumber.charAt(i);// 获取单个验证码进行处理
            char ctmp = (char) itmp;
            Color color = new Color(20 + random.nextInt(110),
                    20 + random.nextInt(110), 20 + random.nextInt(110));
            g.setColor(color);
            g.drawString(String.valueOf(ctmp), 28 * i , 28);
        }
        return image;
    }
    
    /*
     * 给定范围获得随机颜色
     */
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
    
    /**
     * 
     * @Title: createCodeNumber   
     * @Description:生成四位随机数字  
     * @return String
     */
    public static String createCodeNumber() {
        int number = (int)(Math.random()*9000+1000);
        return  number + "";
    }
}
