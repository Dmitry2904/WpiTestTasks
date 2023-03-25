package wpi.dev.dmitry.shelepen.task2;

public class Link {
    private int from;
    private int to;
    private int hard;

    public Link(int from, int to, int hard) {
        this.from = from;
        this.to = to;
        this.hard = hard;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getHard() {
        return hard;
    }

    public void setHard(int hard) {
        this.hard = hard;
    }
}
