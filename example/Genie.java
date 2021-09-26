package example;

import example.Line.Position;
import example.ColorUtil.Color;

public class Genie implements Linable {
    private Color color;
    private final double rank;

    private Position position;

    public Genie(Color color, double rank) {
        this.color = color;
        this.rank = rank;
//        if(s>=0.9999||s<=0.0001){
//            this.rank=(int)(v*2000000000);
//        }else {
//            this.rank = (int) ((h + v) * 100000);
//        }
//        this.rank = (int)h*100000+(int)v*10000;
    }

    public static Genie getGourdByRank(double rank, Linable[] genies) {

        for (Linable gourd : genies) {
            if (gourd.rank() == rank) {
                return (Genie)gourd;
            }
        }
        return null;

    }

    public static void main(String[] args){
        int[] rgb = {1,200,200};
        float[] ff = rgbToHsv(rgb);
        for (int i = 0; i < ff.length; i++) {

        System.out.println(ff[i]);
        }
    }

    public static float[] rgbToHsv(int[] rgb) {
        //切割rgb数组
        int R = rgb[0];
        int G = rgb[1];
        int B = rgb[2];
        //公式运算 /255
        float R_1 = R / 255f;
        float G_1 = G / 255f;
        float B_1 = B / 255f;
        //重新拼接运算用数组
        float[] all = {R_1, G_1, B_1};
        float max = all[0];
        float min = all[0];
        //循环查找最大值和最小值
        for (int i = 0; i < all.length; i++) {
            if (max <= all[i]) {
                max = all[i];
            }
            if (min >= all[i]) {
                min = all[i];
            }
        }
        float C_max = max;
        float C_min = min;
        //计算差值
        float diff = C_max - C_min;
        float hue = 0f;
        //判断情况计算色调H
        if (diff == 0f) {
            hue = 0f;
        } else {
            if (C_max == R_1) {
                hue = (((G_1 - B_1) / diff) % 6) * 60f;
            }
            if (C_max == G_1) {
                hue = (((B_1 - R_1) / diff) + 2f) * 60f;
            }
            if (C_max == B_1) {
                hue = (((R_1 - G_1) / diff) + 4f) * 60f;
            }
        }
        //计算饱和度S
        float saturation;
        if (C_max == 0f) {
            saturation = 0f;
        } else {
            saturation = diff / C_max;
        }
        //计算明度V
        float value = C_max;
        float[] result = {hue, saturation, value};
        return result;
    }


    public String format(){
        String str = "          "+(int)this.rank();
        int len = str.length();
        return str.substring(len-10, len);
    }

    @Override
    public String toString() {
//        return "\033[48;2;" + this.color.r + ";" + this.color.g + ";" + this.color.b + ";38;2;0;0;0m \033[0m";
//        return "\033[48;2;" + this.color.r + ";" + this.color.g + ";" + this.color.b + ";38;2;0;0;0m" + (int)this.rank() + "\033[0m";
//        return "\033[48;2;" + this.color.r + ";" + this.color.g + ";" + this.color.b + ";38;2;0;0;0m" + this.format() + "\033[0m";
        return "\033[48;2;" + this.color.r + ";" + this.color.g + ";" + this.color.b + ";38;2;0;0;0m" + "   " + "\033[0m";
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    public void swapPosition(Genie another) {
        Position p = another.position;
        this.position.setLinable(another);
        p.setLinable(this);
    }

    @Override
    public double rank() {
        return this.rank;
    }

}