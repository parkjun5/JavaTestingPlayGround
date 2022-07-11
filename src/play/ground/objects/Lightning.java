package play.ground.objects;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Lightning {

    Long strokesId;
    String createBy;
    int nsfrac;
    String wkt;
    Float lon;
    Float lat;
    Float height;
    Float intensity;
    Float observationalError;
    int discharge;
    int sensorNumber;
    int[] sensors;
    int quality;
    int flashId;

    protected Lightning() {
    }

    public static Lightning createLightning(String[] values) {
        Lightning lgt = new Lightning();
        try {
            lgt.strokesId = Long.valueOf(values[0]);
            lgt.createBy = values[1] + " " + values[2];
            lgt.nsfrac = Integer.parseInt(values[3]);
            lgt.wkt = values[4];
            lgt.lon = Float.valueOf(values[5]);
            lgt.lat = Float.valueOf(values[6]);
            lgt.height = Float.valueOf(values[7]);
            lgt.intensity = Float.valueOf(values[8]);
            lgt.observationalError = Float.valueOf(values[9]);
            lgt.discharge = Integer.parseInt(values[10]);
            lgt.sensorNumber = Integer.parseInt(values[11]);
            lgt.sensors = new int[]{Integer.parseInt(values[12]),
                    Integer.parseInt(values[13]),
                    Integer.parseInt(values[14]),
                    Integer.parseInt(values[15]),
                    Integer.parseInt(values[16]),
                    Integer.parseInt(values[17]),
                    Integer.parseInt(values[18]),
                    Integer.parseInt(values[19])};
            lgt.quality = Integer.parseInt(values[20]);
            if (values[21].equals("None")) {
                lgt.flashId = -999;

            } else {
                lgt.flashId = Integer.parseInt(values[21]);
            }
        } catch (NumberFormatException exception) {

            System.out.println("values = " + Arrays.stream(values).toList().toString());
            exception.getStackTrace();
        }

        return lgt;
    }

//    private static LocalDateTime parseLocalDateTime(String date) {
//        return LocalDateTime.parse(date, formatter);
//    }

    public String toInsertQuery() {
        return "INSERT INTO public.lightning (" +
                "strokes_id, create_by, nsfrac, wkt_geom, longitude, latitude, height, intensity," +
                "observational_error, discharge , sensor_num, sensors, quality, flash_id ) VALUES (" +
                strokesId + ", " +
                "'" + createBy + "', " +
                nsfrac + ", " +
                "'" + wkt + "', " +
                lon + ", " +
                lat + ", " +
                height + ", " +
                intensity + ", " +
                observationalError + ", " +
                discharge + ", " +
                sensorNumber + ", " +
                "ARRAY " + Arrays.toString(sensors) + ", " +
                quality + ", " +
                flashId + ");\n";
    }
}
