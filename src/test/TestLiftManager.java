package test;

import liftapi.Lift;
import liftapi.LiftManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by fatiz on 21.11.2017.
 */

@RunWith(Parameterized.class)
public class TestLiftManager {

    private int personPosition;
    private boolean personDirectionUp;
    private int expectedNumberOfLift;
    private ArrayList<Lift> lifts = new ArrayList();

    public TestLiftManager(int exp, int personPosition, int personDirectionUp, Lift one, Lift two) {
        while(!lifts.isEmpty())
            lifts.remove(lifts.size() - 1);
        this.personPosition = personPosition;
        this.personDirectionUp = personDirectionUp == 1;
        lifts.add(one);
        lifts.add(two);
        this.expectedNumberOfLift = exp;
    }

    private LiftManager liftManager = new LiftManager(5, false, lifts);

    @Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
//                first value is expectedNumberOfLift
//                if 1 - then arrives lift one
//                if 2 - two
//                if 0 - none

//                simple test cases on different energy costs
                {2, 7, 0, new Lift(4, 5), new Lift(6, 7)},
                {2, 7, 1, new Lift(4, 5), new Lift(6, 7)},
                {1, 5, 0, new Lift(4, 5), new Lift(7, 6)},
                {1, 5, 1, new Lift(4, 5), new Lift(7, 6)},
                {1, 6, 0, new Lift(8, 3), new Lift(2, 4)},
                {1, 6, 0, new Lift(8, 3), new Lift(9, 1)},
                {2, 6, 0, new Lift(5, 12), new Lift(5, 8)},
                {2, 4, 1, new Lift(10, 12), new Lift(3, 1)},


//                test cases on same energy costs
                {1, 6, 0, new Lift(7, 5), new Lift(8, 4)},
                {2, 6, 0, new Lift(6, 6), new Lift(7, 5)},
                {1, 6, 0, new Lift(7, 5), new Lift(7, 5)},
                {1, 6, 0, new Lift(6, 6), new Lift(6, 6)},
                {1, 5, 0, new Lift(7, 6), new Lift(7, 6)},
                {1, 5, 0, new Lift(7, 6), new Lift(3, 4)},
                {1, 4, 0, new Lift(6, 5), new Lift(2, 5)},

//                test cases on workability of maximum filled in lifts
                {0, 6, 0, new Lift(7, 5, 10, 10), new Lift(8, 4, 10, 10)},
                {1, 6, 0, new Lift(7, 5, 10, 4), new Lift(8, 4, 10, 10)},
                {2, 6, 0, new Lift(7, 5, 10, 10), new Lift(8, 4, 10, 4)},
        });
    }

    @Test
    public void testLift() {
        liftManager.setLiftManager(personPosition, personDirectionUp, lifts);
        assertThat(liftManager.getOptimalLiftIndex() + 1, is(expectedNumberOfLift));
    }
}
