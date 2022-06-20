package play.ground;

import play.ground.objects.CsvData;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class MainMethod {

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        String forestPath = "D:\\data\\FOREST_PLACE_BAK_22_06_20.csv";
        List<CsvData> forestPlaces = extractCsvToObject(forestPath);

        String obsPath = "D:\\data\\MATCH_OBS_LIST.csv";
        List<CsvData> obsList = extractCsvToObject(obsPath);

        for (CsvData forest : forestPlaces) {
            AtomicReference<Double> smallDistance = new AtomicReference<>(99999999.9);
            AtomicInteger targetNum = new AtomicInteger(-1);

            innerForEach(obsList, smallDistance, targetNum, forest.getLon(), forest.getLat());

            forest.setRefId(targetNum.get());
        }
        forestPlaces.forEach(n -> System.out.println(n.toString()));
        processTimeCheck(startTime);
        //TODO : to make update 형태로
    }

    private static List<CsvData> extractCsvToObject(String filePath) {
        List<CsvData> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            extracted(list, br);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    private static void extracted(List<CsvData> list, BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            list.add(new CsvData(Integer.parseInt(values[0]), Double.parseDouble(values[1]), Double.parseDouble(values[2])));
        }
    }

    private static void innerForEach(List<CsvData> obsList, AtomicReference<Double> smallDistance, AtomicInteger targetNum, double lng1, double lat1) {
        obsList.forEach(obs -> {
            double distance = calculateDistance(lng1, lat1, obs.getLon(), obs.getLat());
            compareDistance(smallDistance, targetNum, obs, distance);
        });
    }

    private static double calculateDistance(double lng1, double lat1, double lng2, double lat2) {
        int r = 6371; //지구의 반지름(km)
        double dLat = lat2 - lat1;
        dLat = dLat * (Math.PI / 180);
        double dLon = lng2 - lng1;
        dLon = dLon * (Math.PI / 180);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(lat1 * (Math.PI / 180)) * Math.cos(lat2 * (Math.PI / 180)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = r * c; // Distance in km
        return Math.abs(Math.round(d * 1000));
    }

    private static void compareDistance(AtomicReference<Double> smallDistance, AtomicInteger targetNum, CsvData obs, double distance) {
        if (distance < smallDistance.get()) {
            smallDistance.set(distance);
            targetNum.set(obs.getId());
        }
    }

    private static void processTimeCheck(long startTime) {
        long endTime = System.currentTimeMillis();
        long lTime = endTime - startTime;
        System.out.println("TIME : " + lTime + "(ms)");
    }

}
