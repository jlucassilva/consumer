package CollectiveIntelligence;

public class Rank {
    public Reviewer reviewer;
    public double similarity;

    public Rank() {
    }

    public Rank(Reviewer reviewer, double similarity) {
        this.reviewer = reviewer;
        this.similarity = similarity;
    }

    public Reviewer getReviewer() {
        return reviewer;
    }

    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }
}
