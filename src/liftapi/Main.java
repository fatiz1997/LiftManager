package liftapi;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by fatiz on 20.11.2017.
 */
public class Main {

    public static void main(String[] args){

        ArrayList<Lift> lifts = new ArrayList();

        Lift one = new Lift(6, 6, 10, 4);
        Lift two = new Lift(6, 6, 10, 4);

        lifts.add(one);
        lifts.add(two);

        LiftManager liftManager = new LiftManager(6, false, lifts);

        Logger log = Logger.getLogger(Main.class.getName());

        log.info(liftManager.getOptimalLiftId() + " ");
    }
}