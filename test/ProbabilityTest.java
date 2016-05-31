import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * Created by jbhavi on 5/30/2016.
 */
public class ProbabilityTest {

    @Test
    public void testEqualsFailsForNull(){
        Probability probability1 = new Probability(0.6);
        Assert.assertFalse(probability1.equals(null));
    }

    @Test
    public void testEqualsFailsForDifferentObject(){
        Probability probability1 = new Probability(0.6);
        Assert.assertFalse(probability1.equals(1.0));
    }

    @Test
    public void testEquals(){
        Probability probability1 = new Probability(0.6);
        Probability probability2 = new Probability(0.6);
        Assert.assertTrue(probability1.equals(probability2));
    }

    @Test
    public void testEqualsReflexivity(){
        Probability probability1 = new Probability(0.6);
        Assert.assertTrue(probability1.equals(probability1));
    }

    @Test
    public void testEqualsTransitivity(){
        Probability probability1 = new Probability(0.6);
        Probability probability2 = new Probability(0.6);
        Probability probability3 = new Probability(0.6);
        Assert.assertTrue(probability1.equals(probability2));
        Assert.assertTrue(probability2.equals(probability3));
        Assert.assertTrue(probability1.equals(probability3));
    }

    @Test
    public void testEqualsSymmetry(){
        Probability probability1 = new Probability(0.6);
        Probability probability2 = new Probability(0.6);
        Assert.assertTrue(probability1.equals(probability2));
        Assert.assertTrue(probability2.equals(probability1));
    }

    @Test
    public void testNegate(){
        Probability probability1 = new Probability(0.6);
        Probability probability2 = new Probability(0.4);
        Assert.assertTrue(probability1.negate().equals(probability2));
    }

    @Test
    public void testNegateFails(){
        Probability probability1 = new Probability(0.6);
        Probability probability2 = new Probability(0.3);
        Assert.assertFalse(probability1.negate().equals(probability2));
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidProbability(){
       new Probability(-0.8);
       new Probability(1.2);
    }

    @Test
    public void testAnd(){
        Probability probability1 = new Probability(0.2);
        Probability probability2 = new Probability(0.3);
        Probability expectedResult = new Probability(0.06);

        Assert.assertThat(probability1.and(probability2), is(expectedResult));
    }

    @Test
    public void testOperationChaining(){
        Probability result = new Probability(0.654444444211111142109912);
        result = result.negate().negate();
        Probability probability= new Probability(0.654444444211111142109912);
        Assert.assertTrue(result.equals(probability));
    }

    @Test
    public void testUnionOperation(){
        Probability probability1 = new Probability(0.2);
        Probability probability2 = new Probability(0.3);
        Probability expected = new Probability(0.44);

        Assert.assertThat(probability1.union(probability2), is(expected));
    }

    @Test
    public void testUnionOperationProbabilityZero(){
        Probability probability1 = new Probability(0);
        Probability probability2 = new Probability(1);
        Probability expected = new Probability(1);

        Assert.assertThat(probability1.union(probability2), is(expected));
    }

    @Test
    public void testUnionOperationProbabilityOne(){
        Probability probability1 = new Probability(1.000000000000000001);
        Probability probability2 = new Probability(1);
        Probability expected = new Probability(1);

        Assert.assertThat(probability1.union(probability2), is(expected));
    }

    @Test
    public void testUnionOperationProbabilityOneX(){
        Probability probability1 = new Probability(1);
        Probability probability2 = new Probability(0.4);
        Probability expected = new Probability(1);

        Assert.assertThat(probability1.union(probability2), is(expected));
    }

}
