package wpi.dev.dmitry.shelepen.task2;

import java.util.*;

public class RouteSearchService {
    private LinkService linkService = new LinkService();
    private List<Route> foundRoutes = new ArrayList<>();
    private ArrayList<Link> links = new ArrayList<>();

    public String findRoute(ArrayList<Link> links) {
        this.links = links;

        LinkedList<Integer> routeRooms = new LinkedList<>();
        routeRooms.add(1);
        boolean found = foundSequence(routeRooms);

        String outputText;
        if (found) {
            Optional<Route> routeWithMinPrice = foundRoutes.stream().min(Comparator.comparingInt(Route::getPrice));
            StringBuilder result = new StringBuilder();
            routeWithMinPrice.ifPresent(route -> route.getRouteRooms().stream()
                    .forEach(roomNumber -> result.append(roomNumber).append(" ")));
            outputText = result.toString();
        } else {
            outputText = "NO";
        }
        return outputText;
    }


    private boolean foundSequence(LinkedList<Integer> routeRooms) {
        int currentRoom = routeRooms.getLast();
        int previousRoom = (routeRooms.size() > 1) ? routeRooms.get(routeRooms.size() - 2) : 1;

        List<Integer> possibleNextRooms = linkService.giveTargetDirections(links, currentRoom, previousRoom);
        int firsPossibleNextRoom = possibleNextRooms.get(0);
        int secondPossibleNextRoom = possibleNextRooms.get(1);

        if (firsPossibleNextRoom == 1 || secondPossibleNextRoom == 1) {
            int allRooms = links.size() / 3;
            if (routeRooms.size() == allRooms) {
                int routePrice = linkService.calculatePriceForRoute(links, routeRooms);
                Route route = new Route(routeRooms, routePrice);
                foundRoutes.add(route);
                return true;
            }
        }

        if (tryToFindTheNextRoom(routeRooms, firsPossibleNextRoom)) return true;
        if (tryToFindTheNextRoom(routeRooms, secondPossibleNextRoom)) return true;

        //not found
        return false;
    }

    private boolean tryToFindTheNextRoom(LinkedList<Integer> routeRooms, int thirdDirectionTo) {
        if (!routeRooms.contains(thirdDirectionTo)) {
            routeRooms.add(thirdDirectionTo);
            boolean found = foundSequence(routeRooms);
            if (found) {
                return true;
            } else {
                routeRooms.removeLast();
            }
        }
        return false;
    }
}
