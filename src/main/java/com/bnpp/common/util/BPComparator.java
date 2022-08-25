package com.bnpp.common.util;

import com.bnpp.common.models.DataLine;

import java.util.Comparator;

public class BPComparator implements Comparator<DataLine> {

    public static final BPComparator INSTANCE = new BPComparator();

    @Override
    public int compare(DataLine o1, DataLine o2) {
        return this.compare(o1.getBackpressureOccurredAt(), o2.getBackpressureOccurredAt());
    }

    public int compare(String first, String second) {
        HoursMinutes hmFirst = toHoursMinutes(removeCharacters(first));
        HoursMinutes hmSecond = toHoursMinutes(removeCharacters(second));

        if (hmFirst.getHours() > hmSecond.getHours()) {
            return 1;
        }

        if (hmFirst.getHours() < hmFirst.getHours()) {
            return -1;
        }

        if (hmFirst.getMinutes() > hmSecond.getMinutes()) {
            return 1;
        }

        if (hmFirst.getMinutes() < hmSecond.getMinutes()) {
            return -1;
        }

        return 0;
    }

    private static String removeCharacters(String first) {
        StringBuilder resp = new StringBuilder(first);

        for (int i = 0; i < first.length(); i++) {
            if (Character.isAlphabetic(resp.charAt(i))) {
                resp.deleteCharAt(i);
            }
        }

        return resp.toString();
    }

    private HoursMinutes toHoursMinutes(String src) {
        String[] hoursMinutes = src.split(":");
        return new HoursMinutes(Integer.parseInt(hoursMinutes[0]), Integer.parseInt(hoursMinutes[1]));
    }

}

class HoursMinutes {

    private Integer hours;
    private Integer minutes;

    public HoursMinutes(Integer hours, Integer minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }
}