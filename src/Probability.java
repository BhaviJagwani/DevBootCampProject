import java.math.BigDecimal;

/**
 * Created by jbhavi on 5/30/2016.
 */
public class Probability {

    private static final BigDecimal MAX_PROBABILITY = BigDecimal.ONE;
    private final BigDecimal value;

    public Probability(double value) {
        if(value < 0 || value > 1)
            throw new RuntimeException("Invalid probability");
        this.value = BigDecimal.valueOf(value);
    }

    public Probability negate(){
        return new Probability(MAX_PROBABILITY.subtract(this.value).doubleValue());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Probability that = (Probability) o;

        return that.value.compareTo(this.value) == 0;

    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(value.doubleValue());
        return (int) (temp ^ (temp >>> 32));
    }


    public Probability and(Probability that) {
        return new Probability(this.value.multiply(that.value).doubleValue());
    }


    public Probability union(Probability that) {
        BigDecimal intersectionValue = this.and(that).value;
        BigDecimal unionValue = this.value.add(that.value).subtract(intersectionValue);
        return new Probability(unionValue.doubleValue());
    }
}
