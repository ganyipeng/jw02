package example;

public class Snake {

    private static Snake theSnake;

    public static Snake getTheSnake() {
        if (theSnake == null) {
            theSnake = new Snake();
        }
        return theSnake;
    }

    private Snake() {

    }

    private Sorter sorter;

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public String lineUp(Line line) {

        String log = new String();

        if (sorter == null) {
            return null;
        }

        Linable[] linables = line.toArray();
        double[] ranks = new double[linables.length];

        for (int i = 0; i < linables.length; i++) {
            ranks[i] = linables[i].rank();
        }

        sorter.load(ranks);
        sorter.sort();

        String[] sortSteps = this.parsePlan(sorter.getPlan());

        String lastLog = "";
        for (String step : sortSteps) {
            this.execute(step, linables);
            System.out.println(line.toString());
            lastLog = "";
            lastLog += line.toString()+"\n";
//            lastLog += line.toString()+"\n";
//            lastLog += line.toString()+"\n";
//            lastLog += line.toString()+"\n";
            lastLog += "\n[frame]\n";
            log+=lastLog;
//            log += line.toString();
//            log += "\n[frame]\n";
        }
        for(int i=0; i<100; i++){
            log+=lastLog;
        }
        return log;

    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(String step, Linable[] linables) {
        String[] couple = step.split("<->");
        Genie.getGourdByRank(Double.parseDouble(couple[0]), linables)
                .swapPosition(Genie.getGourdByRank(Double.parseDouble(couple[1]), linables));
    }

}
