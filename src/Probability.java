/**
 * Created by jbhavi on 5/30/2016.
 */
public class Probability {

    private static final double MAX_PROBABILITY = 1;
    private final double value;

    public Probability(double value) {
        if(value < 0 || value > 1)
            throw new RuntimeException("Invalid probability");
        this.value = value;
    }

    public Probability negate(){
        return new Probability(MAX_PROBABILITY - this.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Probability that = (Probability) o;

        return Double.compare(that.value, value) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value);
        return (int) (temp ^ (temp >>> 32));
    }


    public Probability and(Probability probability) {
        return new Probability(this.value*probability.value);

    }
}
