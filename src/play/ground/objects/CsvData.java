package play.ground.objects;

public class CsvData {
    private int id;
    private Double lon;
    private Double lat;
    private int refId;

    public CsvData(int id, Double lat,  Double lon) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;

    }

    public int getId() {
        return id;
    }

    public CsvData setId(int id) {
        this.id = id;
        return this;
    }

    public Double getLon() {
        return lon;
    }

    public CsvData setLon(Double lon) {
        this.lon = lon;
        return this;
    }

    public Double getLat() {
        return lat;
    }

    public CsvData setLat(Double lat) {
        this.lat = lat;
        return this;
    }

    public int getRefId() {
        return refId;
    }

    public CsvData setRefId(int refId) {
        this.refId = refId;
        return this;
    }

    @Override
    public String toString() {
        return "CsvData{" +
                "id=" + id +
                ", lon=" + lon +
                ", lat=" + lat +
                ", refId=" + refId +
                '}';
    }
}
