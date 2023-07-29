import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File diploma = new File("diploma.csv");

        int index = 0;
        int totalSum = 0;
        int maxIntake = Integer.MIN_VALUE;
        int minIntake = Integer.MAX_VALUE;

        if (diploma.exists()) {
            System.out.println("File exists");

            if (diploma.canRead()) {
                System.out.println("File is readable");
            } else {
                System.out.println("File is unreadable");
            }

            try (Scanner reader = new Scanner(diploma)) {
                List<DiplomaData> diplomaDataList = new ArrayList<>();
                List<DiplomaData> diplomaLanjutanList = new ArrayList<>();
                List<DiplomaData> kursusPengkhususanList = new ArrayList<>();

                while (reader.hasNext()) {
                    String line = reader.nextLine();
                    if (index > 0) {
                        String[] items = line.split(",");

                        int bil = Integer.parseInt(items[0].trim());
                        String category = items[1].trim();
                        String name = items[2].trim();
                        int[] intakes = new int[6]; // Store intakes from 2014 to 2019

                        for (int i = 3; i < items.length; i++) {
                            int intake = Integer.parseInt(items[i].trim());
                            intakes[i - 3] = intake;
                        }

                        DiplomaData data = new DiplomaData(bil,category, name, intakes);

                        diplomaDataList.add(data);

                        totalSum += data.getTotal(); // Calculate the total sum
                        maxIntake = Math.max(maxIntake, data.getMax()); // Calculate the maximum intake
                        minIntake = Math.min(minIntake, data.getMin()); // Calculate the minimum intake

                        System.out.println(data);
                    }
                    index++;
                }

                for (DiplomaData data : diplomaDataList) {
                    if (data.getCategory().equalsIgnoreCase("Diploma Lanjutan")) {
                        diplomaLanjutanList.add(data);
                    } else if (data.getCategory().equalsIgnoreCase("Kursus Pengkhususan")) {
                        kursusPengkhususanList.add(data);
                    }
                }

                // Write Matching Data to Files
                writeDataToFile(diplomaLanjutanList, "diploma_lanjutan.txt");
                writeDataToFile(kursusPengkhususanList, "kursus_pengkhususan.txt");

                // Print to console
                System.out.println("\nThe sum of intakes from the year 2014 to 2019 is " + totalSum);
                System.out.println("The maximum number of intakes between the year 2014 to 2019 is " + maxIntake);
                System.out.println("The minimum number of intakes between the year 2014 to 2019 is " + minIntake);

            } catch (FileNotFoundException exception) {
                System.out.println(exception.getMessage());
            }

        } else {
            System.out.println("File does not exist");
        }
    }

    private static void writeDataToFile(List<DiplomaData> dataList, String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (DiplomaData data : dataList) {
                writer.println(data.toString());
            }
        } catch (FileNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
