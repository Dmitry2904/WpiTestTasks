package wpi.dev.dmitry.shelepen.task2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CaveExtractor {

    public ArrayList<Link> extractLinks(List<String> lines) {
        ArrayList<Link> links = new ArrayList<>();

        Iterator<String> iterator = lines.iterator();
        if (iterator.hasNext()) {
            String next = iterator.next();
            String[] line = next.trim().split(" ");
            int allRooms = Integer.parseInt(line[0]);
            int outdoorRooms = Integer.parseInt(line[1]);

            int countOfLinks = allRooms * 3 / 2;
            for (int i = 0; i < countOfLinks && iterator.hasNext(); i++) {
                line = iterator.next().trim().split(" ");
                int from = Integer.parseInt(line[0]);
                int to = Integer.parseInt(line[1]);
                int hard = Integer.parseInt(line[2]);
                links.add(new Link(from, to, hard));
                links.add(new Link(to, from, hard));
            }
        }
        return links;
    }

}
