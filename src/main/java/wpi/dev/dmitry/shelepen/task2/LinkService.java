package wpi.dev.dmitry.shelepen.task2;

import java.util.List;

public class LinkService {

    public List<Integer> giveTargetDirections(List<Link> links, int currentRoom, int previousRoom) {
        return links.stream()
                .filter(link -> link.getFrom() == currentRoom && link.getTo() != previousRoom)
                .map(Link::getTo)
                .toList();
    }

    public int calculatePriceForRoute(List<Link> links, List<Integer> routeRooms) {
        int totalPrice = 0;
        for (int i = 0; i + 1 < routeRooms.size(); i++) {
            Integer from = routeRooms.get(i);
            Integer to = routeRooms.get(i + 1);
            int price = links.stream()
                    .filter(link -> link.getFrom() == from && link.getTo() == to)
                    .map(Link::getHard)
                    .findAny().get();
            totalPrice += price;
        }
        return totalPrice;
    }
}
