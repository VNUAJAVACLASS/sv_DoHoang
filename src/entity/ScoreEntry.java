package entity;

public class ScoreEntry {
    private float score;
    private float weight;
    
    public ScoreEntry(float score, float weight) {
        this.score = score;
        this.weight = weight;
    }

    public float getScore() {
        return score;
    }

    public float getWeight() {
        return weight;
    }
}