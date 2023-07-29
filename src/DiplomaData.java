public class DiplomaData {
    private int bil;
    private String category;
    private String name;
    private int[] intakes; // Store intakes for each year

    public DiplomaData(int bil,String category, String name, int[] intakes) {
        this.bil = bil;
        this.category = category;
        this.name = name;
        this.intakes = intakes;
    }

    public String getCategory() {
        return category;
    }

    public int getTotal() {
        int total = 0;
        for (int intake : intakes) {
            total += intake;
        }
        return total;
    }


    public int getMax() {
        int max = Integer.MIN_VALUE;
        for (int intake : intakes) {
            max = Math.max(max, intake);
        }
        return max;
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;
        for (int intake : intakes) {
            min = Math.min(min, intake);
        }
        return min;
    }

    @Override
    public String toString() {
        int total = getTotal();
        int max = getMax();
        int min = getMin();
        return String.format("%d. Category='%s', Name='%s', Total=%d, Max=%d, Min=%d",
                bil,category, name, total, max, min);
    }

//    This will display all the data including intake of each year.
//    @Override
//    public String toString() {
//        int total = getTotal();
//        int max = getMax();
//        int min = getMin();
//        return String.format("%d. Category='%s', Name='%s', Intake2014=%d, Intake2015=%d, Intake2016=%d, Intake2017=%d, Intake2018=%d, Intake2019=%d, Total=%d, Max=%d, Min=%d",
//                bil,category, name, intakes[0], intakes[1], intakes[2], intakes[3], intakes[4], intakes[5], total, max, min);
//    }
}
