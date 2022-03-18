package lesson05;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreateFileClass {

    public static ArrayList<FIleObject> fIleObjectArrayList = new ArrayList<>();
    public static final String pathToFile1 = "src/lesson05/file1.csv";
    public static final String title = "value1"+ ";" + "value2"
            + ";" + "value3" + ";"+ System.getProperty("line.separator");

    public static void main(String[] args) throws IOException {
        createFileObjects();
        writeStream();

        AppData appData = readToObject();
        printAppData(appData);
        changeAppData(appData);
        printAppDataAfterChanges(appData);
        save(appData);
    }

    public static void createFileObjects(){
        Random random = new Random();

        for(int i=1;i<5;i++){
            fIleObjectArrayList.add( new FIleObject(i, random.nextInt(100), random.nextInt(10000)));
        }
    }

    public static void writeStream() throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(pathToFile1)){
            for(byte b :title.getBytes(StandardCharsets.UTF_8)){
                fileOutputStream.write(b);
            }
            for(FIleObject fIleObject : fIleObjectArrayList){
                String raw = fIleObject.getValue() + ";" + fIleObject.getValueFrom()
                        + ";" + fIleObject.getValueTo() + ";"+ System.getProperty("line.separator");
                for(byte b :raw.getBytes(StandardCharsets.UTF_8)){
                    fileOutputStream.write(b);
                }
            }
        }
    }

    public static AppData readToObject() throws IOException {
        AppData appData = new AppData();
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile1))) {
            String line = br.readLine();
            appData.setHeader( line.split(";"));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        };

        int[][] resultData = new int[records.size()][3];

        for(int i=0;i<records.size();i++){
            for(int j=0;j<records.get(i).size();j++){
                resultData[i][j] = Integer.valueOf(records.get(i).get(j));
            }
        }
        appData.setData(resultData);
        return appData;
    }

    public static void printAppData(AppData appData) {
        System.out.println(Arrays.toString(appData.getHeader()));
        for (int[] data : appData.getData()) {
            System.out.println(Arrays.toString(data));
        }
    }

    public static void changeAppData(AppData appData) {
        appData.setData(new int[][]{{ 1, 2, 3},{1, 2, 3},{1, 2, 3}});
        }

    public static void printAppDataAfterChanges(AppData appData) {
        System.out.println(Arrays.toString(appData.getHeader()));
        for (int[] data : appData.getData()) {
            System.out.println(Arrays.toString(data));
        }
    }

    public static void save(AppData data) throws IOException {
        try (FileWriter fileWriter = new FileWriter(pathToFile1)) {
            StringBuilder valueOfHeader = new StringBuilder();
            for (String value : data.getHeader()) {
                valueOfHeader.append(value).append(";");
            }
            fileWriter.write(valueOfHeader + "\n");
            for (int[] row : data.getData()) {
                StringBuilder valueOfData = new StringBuilder();
                for (int value : row) {
                    valueOfData.append(value).append(";");
                }
                fileWriter.write(valueOfData + "\n");
            }
        }
    }


}
