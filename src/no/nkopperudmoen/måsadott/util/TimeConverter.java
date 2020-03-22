package no.nkopperudmoen.måsadott.util;

public class TimeConverter {

    public static int[] convertTime(int innMin) {
        int[] tidsarray = new int[3];
        int dager = (innMin / 60) / 24;
        tidsarray[0] = dager;

        int timer = ((innMin / 60) % 24);
        tidsarray[1] = timer;
        int min = innMin % 60;
        tidsarray[2] = min;
        return tidsarray;
    }

    public static String toString(int[] tidsarray) {
        String tid;
        if (tidsarray[0] == 0) {
            if (tidsarray[1] == 0) {
                if (tidsarray[2] == 0) {
                    tid = "0 minutter";
                } else {
                    tid = tidsarray[2] + " minutter";
                }
            } else {
                tid = tidsarray[1] + " timer og " + tidsarray[2] + " minutter";
            }
        } else {
            tid = tidsarray[0] + " dager, " + tidsarray[1] + " timer og " + tidsarray[2] + " minutter";
        }
        return tid;
    }
}
