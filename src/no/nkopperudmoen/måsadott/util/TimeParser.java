package no.nkopperudmoen.måsadott.util;

public class TimeParser {
    public String[] parseTime(String tid) {
        return tid.split("d|t|m");
    }

    public int toMins(String[] tid) {
        int minutter = 0;
        switch (tid.length) {
            case 1:
                return Integer.parseInt(tid[0]);
            case 2:
                return Integer.parseInt(tid[0] + ((Integer.parseInt(tid[1]) * 60)));
            case 3:
                return Integer.parseInt(tid[0] + ((Integer.parseInt(tid[1]) * 60)) + Integer.parseInt(tid[2]));
            default:
                return 0;
        }
    }
}
