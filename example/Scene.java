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

        // 可以把SIZE常量的值改为16，25，64等小的方阵来快速测试
        Line line = new Line(SIZE);
        // 生成随机的Size个座位次序
        List<Integer> orders = generateRandomDiffInteger(SIZE);
        // 生成随机的256个颜色值
        ColorUtil.Color[] colors = ColorUtil.Color.random(256);


        for(int i=0; i<SIZE; i++){
            float h = colors[i].h;
            float s = colors[i].s;
            double rank = (int) (h * 100000 + s * 1000);
            // 将颜色值和rank赋值给Genie小怪
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
