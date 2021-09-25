package example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Scene {
    public static Random random = new Random();
    static final int SIZE = 256;
    public static int r(){
        return random.nextInt(256);
    }

    /**
     * 生成[0~N)连续的整数，然后做乱序处理
     * @return
     */
    public static List<Integer> generateRandomDiffInteger(int N){
        List<Integer> orders = new ArrayList<>();
        for(int i=0; i<N; i++){
            orders.add(i);
        }
        Collections.shuffle(orders);
        return orders;
    }

    public static void main(String[] args) throws IOException {


        Line line = new Line(SIZE);
        List<Integer> orders = generateRandomDiffInteger(SIZE);
        Test.Color[] colors = Test.Color.random(256);


        for(int i=0; i<SIZE; i++){
            int r = colors[i].r;
            int g = colors[i].g;
            int b = colors[i].b;
            float h = colors[i].h;
            float s = colors[i].s;
            float v = colors[i].v;
//            int rank = i;
            double rank = 0;
//            if(s>=0.9999999||s<=0.0000001){
//                rank=(int)((s+1)*2000);
//            }else {
//                rank = (int) (h+100 + s);
//            }
//            if(s>=0.9999999||s<=0.0000001){
//                rank=(int)(s*20000000);
//            }else {
//                rank = (int) (h * 100000 + s * 1000);
//            }
            rank = (int) (h * 100000 + s * 1000);
            line.put(new Genie(colors[i], rank), orders.get(i));
        }
        System.out.println(orders);

        Snake theSnake = Snake.getTheSnake();

        Sorter sorter = new BubbleSorter();

        theSnake.setSorter(sorter);

        String log = theSnake.lineUp(line);

        BufferedWriter writer;
        writer = new BufferedWriter(new FileWriter("result.txt"));
        writer.write(log);
        writer.flush();
        writer.close();

    }

}
