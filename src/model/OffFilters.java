package model;

import java.time.LocalDateTime;

public class OffFilters {
    private LocalDateTime starTime;
    private LocalDateTime endTime;
    private int minimumDiscountAmount;

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
