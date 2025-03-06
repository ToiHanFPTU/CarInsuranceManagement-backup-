package tool;

import java.util.HashMap;

public class PlaceRegis extends HashMap<String, String> {
    public String getPlace(String licensePlate) {
        this.put("t1", "District 1");
        this.put("t2", "District 1");
        this.put("b1", "District 2");
        this.put("f1", "District 3");
        this.put("f2", "District 3");
        this.put("c1", "District 4");
        this.put("h1", "District 5");
        this.put("k1", "District 6");
        this.put("k2", "District 6");
        this.put("c2", "District 7");
        this.put("l1", "District 8");
        this.put("l2", "District 8");
        this.put("x1", "District 9");
        this.put("u1", "District 10");
        this.put("u2", "District 10");
        this.put("m1", "District 11");
        this.put("m2", "District 11");
        this.put("g1", "District 12");
        this.put("g2", "District 12");
        this.put("d1", "District Tan Phu");
        this.put("e1", "District Phu Nhuan");
        this.put("n1", "District Binh Tan");
        this.put("p1", "District Tan Binh");
        this.put("p2", "District Tan Binh");
        this.put("s1", "District Binh Thanh");
        this.put("s2", "District Binh Thanh");
        this.put("s3", "District Binh Thanh");
        this.put("v1", "District Go Vap");
        this.put("v2", "District Go Vap");
        this.put("v3", "District Go Vap");
        this.put("x2", "District Thu Duc");
        this.put("x3", "District Thu Duc");
        this.put("n2", "District Binh Chanh");
        this.put("n3", "District Binh Chanh");
        this.put("y1", "District Hoc Mon");
        this.put("y2", "District Cu Chi");
        this.put("y3", "District Cu Chi");
        this.put("z1", "District Nha Be");
        return this.get(licensePlate.substring(2, 4).toLowerCase());
    }
}
