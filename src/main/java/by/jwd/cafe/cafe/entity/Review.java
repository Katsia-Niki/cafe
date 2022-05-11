package by.jwd.cafe.entity;

import java.util.StringJoiner;

public class Review extends AbstractEntity {
    private int rate;
    private String review;

    public Review() {

    }
    public Review(int rate, String review) {
        this.rate = rate;
        this.review = review;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review review1 = (Review) o;

        if (rate != review1.rate) return false;
        return review != null ? review.equals(review1.review) : review1.review == null;
    }

    @Override
    public int hashCode() {
        int result = rate;
        result = 31 * result + (review != null ? review.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "Review{", "}");
        joiner.add("rate=" + rate);
        joiner.add("review='" + review);
        return joiner.toString();
    }
}
