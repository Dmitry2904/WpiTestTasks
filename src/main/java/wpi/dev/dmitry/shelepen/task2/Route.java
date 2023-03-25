package wpi.dev.dmitry.shelepen.task2;

import java.util.List;

public class Route {

    private List<Integer> routeRooms;
    private int price;

    public Route(List<Integer> routeRooms, int price) {
        this.routeRooms = routeRooms;
        this.price = price;
    }

    public List<Integer> getRouteRooms() {
        return routeRooms;
    }

    public void setRouteRooms(List<Integer> routeRooms) {
        this.routeRooms = routeRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Route{" +
                "routeRooms=" + routeRooms +
                '}';
    }
}
