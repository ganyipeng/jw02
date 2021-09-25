package example;

public class Test {
    public static class Color{
        int r;
        int g;
        int b;
        float h;
        float s;
        float v;
        public Color(int r, int g, int b, float h, float s, float v){
            this.r = r;
            this.g = g;
            this.b = b;
            this.h = h;
            this.s = s;
            this.v = v;
        }
        public static Color[] random(int size){
            Color[] colors = new Color[size];
            // 8个h
            double[] h = new double[8];
            for (int i = 0; i < 8; i++) {
                h[i] = i*45/360.0;
            }

            // 32个g
            double[] s = new double[32];
            for(int j=0; j<32; j++){
                s[j] = j*3/100.0;
            }

            int index = -1;
            for(int ii=0; ii<8; ii++){
                for(int jj=0; jj<32; jj++){
                    index++;
                    float _h = (float) h[ii];
                    float _s = (float) s[jj];
                    float _v = 1;
                    int[] rgb = hsvToRgb(_h, _s, _v);
                    int r = rgb[0];
                    int g = rgb[1];
                    int b = rgb[2];
                    r = Math.min(r,255);
                    g = Math.min(g,255);
                    b = Math.min(b,255);
                    colors[index] = new Color(r,g,b,_h,_s,_v);
                }
            }
            return colors;
        }
    }
    public static void showColor(Color color) {
        String colorString = "\033[48;2;" + color.r + ";" + color.g + ";" + color.b + ";38;2;0;0;0m \033[0m";
        System.out.println(colorString);
//        return "\033[48;2;" + this.r + ";" + this.g + ";" + this.b + ";38;2;0;0;0m" + this.rank() + "\033[0m";
    }
    public static void main(String[] args) {

        Color[] colors = Color.random(256);
        for (int i = 0; i < colors.length; i++) {
            showColor(colors[i]);
        }
        System.out.println(colors.length);

//        float hue = (float) 180.0/360;
//        float saturation = 0.995f;
//        float value = 0.78431374f;
//        int[] rgb = hsvToRgb(hue,saturation,value);
//        for (int i = 0; i < rgb.length; i++) {
//            System.out.println(rgb[i]);
//        }
//        System.out.println(rgb);
    }
    public static int[] hsvToRgb(float hue, float saturation, float value) {

        int h = (int)(hue * 6);
        float f = hue * 6 - h;
        float p = value * (1 - saturation);
        float q = value * (1 - f * saturation);
        float t = value * (1 - (1 - f) * saturation);

        switch (h) {
            case 0: return rgbToString(value, t, p);
            case 1: return rgbToString(q, value, p);
            case 2: return rgbToString(p, value, t);
            case 3: return rgbToString(p, q, value);
            case 4: return rgbToString(t, p, value);
            case 5: return rgbToString(value, p, q);
            default: throw new RuntimeException("Something went wrong when converting from HSV to RGB. Input was " + hue + ", " + saturation + ", " + value);
        }
    }

    public static int[] rgbToString(float r, float g, float b) {
        int rs = (int)(r * 256);
        int gs = (int)(g * 256);
        int bs = (int)(b * 256);
        return new int[]{rs,gs,bs};
    }

//    public static String rgbToString(float r, float g, float b) {
//        String rs = Integer.toHexString((int)(r * 256));
//        String gs = Integer.toHexString((int)(g * 256));
//        String bs = Integer.toHexString((int)(b * 256));
//        return rs + gs + bs;
//    }
}
