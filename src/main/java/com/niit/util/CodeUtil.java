package com.niit.util;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @program: shop
 * @description: 验证码生产工具类
 * @author: hanliang
 * @create: 2020-02-06 09:50
 **/
public class CodeUtil {

    // 字典/词典
    public static final String WORDS = "abcdefghijkmnpqrstuvwxyz23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    // 字体类型
    private static final String[] FONTS  = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};

    // 旋转角度
    private static final int[] thetas= {30,45,50,-30};

    // 宽度
    public static final int w=100;

    // 高度
    public static final int h=30;


    public static Random rd = new Random();
    /**
     *  生产并绘制验证码
     * @param session
     */
    public static BufferedImage getCode(HttpSession session){

        String code = "";// 记录最终生产的4位验证码
        //  缓存图片对象
        BufferedImage image = new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);

        // 绘制对象
        Graphics2D g = (Graphics2D)image.getGraphics();

        // 1. 绘制背景
        g.setColor(Color.white); //背景颜色
        g.fillRect(0,0,w,h);

        // 2. 绘制验证码
        for(int i=0;i<4;i++){
            // 设置随机颜色
            g.setColor(getColor(100,200));
            g.setFont(getFont());

            // 随机索引
            int index = rd.nextInt(WORDS.length());

            // 获取字符
            char c = WORDS.charAt(index);
            code+=c; //记录验证码

            //旋转
           //  int t = thetas[rd.nextInt(thetas.length)];
            g.rotate(45*Math.PI/180,i*15+10,15);
            g.drawString(c+"",i*15+10,15);
            g.rotate(-45*Math.PI/180,i*15+10,15);
        }

        // 绘制干扰线
       for(int i=0;i<10;i++){
            g.setColor(getColor(200,255));

            int x1 = rd.nextInt(w);
            int y1 = rd.nextInt(h);
            int x2 = rd.nextInt(w);
            int y2 = rd.nextInt(h);

            g.drawLine(x1,y1,x2,y2);
       }


        // 绘制干扰点
        for(int i=0;i<20;i++){
            g.setColor(getColor(0,100));
            int x1 = rd.nextInt(w);
            int y1 = rd.nextInt(h);
            g.fillOval(x1,y1,1,1);
        }
        // 将4为验证码字符串 存入session
        session.setAttribute("CODE",code);

        return image;
    }

    /**
     * 随机颜色值
     * @return
     */
    public static Color getColor(int c1, int c2){
        int red = c1 + rd.nextInt(c2-c1);  // [0,256)
        int green = c1 +rd.nextInt(c2-c1);// [0,256)
        int blue = c1+ rd.nextInt(c2-c1); // [0,256)

        return new Color(red,green,blue);
    }

    /**
     * 随机字体
     * @return
     */
    public static Font getFont(){

        int index = rd.nextInt(FONTS.length);

        return new Font(FONTS[index],Font.BOLD,24);
    }
}
