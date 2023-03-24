package wpi.dev.dmitry.shelepen.task2;

import java.util.List;

public class Marshrut {

    private List<Integer> marshrutPoints;
    private int price;

    public Marshrut(List<Integer> marshrutPoints, int price) {
        this.marshrutPoints = marshrutPoints;
        this.price = price;
    }


    public List<Integer> getMarshrutPoints() {
        return marshrutPoints;
    }

    public void setMarshrutPoints(List<Integer> marshrutPoints) {
        this.marshrutPoints = marshrutPoints;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Marshrut{" +
                "marshrutPoints=" + marshrutPoints +
                '}';
    }
}
