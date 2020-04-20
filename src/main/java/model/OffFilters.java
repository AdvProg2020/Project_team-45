package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class OffFilters {
    private static ArrayList<String> availableFilters = new ArrayList<>();
    private LocalDateTime starTime;
    private LocalDateTime endTime;
    private int minimumDiscountAmount;

    public static ArrayList<String> getAvailableFilters() {
        return availableFilters;
    }

    public LocalDateTime getStarTime() {
        return starTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public int getMinimumDiscountAmount() {
        return minimumDiscountAmount;
    }

    public void setStarTime(LocalDateTime starTime) {
        this.starTime = starTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setMinimumDiscountAmount(int minimumDiscountAmount) {
        this.minimumDiscountAmount = minimumDiscountAmount;
    }
}
