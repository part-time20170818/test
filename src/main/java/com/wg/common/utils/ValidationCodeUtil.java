package com.wg.common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ValidationCodeUtil {
    /**
     * 验证码图片封装
     */
    public static class ValidationCode {
        private BufferedImage image;
        private String code;

        public BufferedImage getImage() {
            return image;
        }

        public String getCode() {
            return code;
        }
    }
    
    public static ValidationCode getCode(){
        int width = 85;
        int height = 34;
        
        // 创建字体，字体的大小应该根据图片的高度来定
        List<Font> fonts = new ArrayList<Font>();
        fonts.add(new Font("Lucida Bright", Font.PLAIN|Font.ITALIC, 26));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 26));
        fonts.add(new Font("Lucida Bright", Font.PLAIN|Font.ITALIC, 24));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 24));
        fonts.add(new Font("Lucida Bright", Font.PLAIN|Font.ITALIC, 20));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 20));
        fonts.add(new Font("Lucida Bright", Font.PLAIN|Font.ITALIC, 18));
        fonts.add(new Font("Lucida Bright", Font.PLAIN, 18));

        // 创建具有可访问图像数据缓冲区的Image
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器
        Random random = new Random();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 画边框
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);

        // 产生随机的颜色分量来构造颜色值
        int red = 0, green = 0, blue = 0;
        red = random.nextInt(80)+150;
        green = random.nextInt(80)+150;
        blue = random.nextInt(80)+150;
        g.setColor(new Color(red, green, blue));

        // 随机产生150条干扰线
        for (int i = 0; i < 150; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        // randomCode 用于保存随机产生的验证码
        StringBuilder randomCode = new StringBuilder();

        // 设置备选验证码:包括"a-z"和数字"0-9"
        String base = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";

        // 随机产生4位验证码
        for (int i = 0; i < 4; i++) {
            // 得到随机产生的验证码字符
            int start = random.nextInt(base.length());
            String strRand = base.substring(start, start + 1);
            
            // 产生随机的颜色分量来构造颜色值
            red = random.nextInt(160);
            green = random.nextInt(160);
            blue = random.nextInt(160);

            // 随机设置字体
            g.setFont(fonts.get(random.nextInt(fonts.size())));

            // 用随机产生的颜色将验证码绘制到图像中
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, 17 * i + 6, 28);
            randomCode.append(strRand);
        }
        g.dispose();
        
        ValidationCode code = new ValidationCode();
        code.image = buffImg;
        code.code = randomCode.toString();
        return code;        
    }
}
