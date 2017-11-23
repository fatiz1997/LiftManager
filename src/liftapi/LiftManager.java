package liftapi;

import java.util.*;

/**
 * Created by fatiz on 20.11.2017.
 */

public class LiftManager {

    private int personPosition;
    private boolean personDirectionUp;
    private ArrayList<Lift> lifts;

    public LiftManager(int personPosition, boolean personDirectionUp, List<Lift> lifts) {
        this.personPosition = personPosition;
        this.personDirectionUp = personDirectionUp;
        this.lifts = new ArrayList(lifts);
    }

    public void setLiftManager(int personPosition, boolean personDirectionUp, List<Lift> lifts){
        this.personPosition = personPosition;
        this.personDirectionUp = personDirectionUp;
        this.lifts = new ArrayList(lifts);
    }

    public int getOptimalLiftId(){
        if(allFilledIn())
            return -1;
        Collections.sort(this.lifts, comparator);
        return this.lifts.get(0).getId();
    }

    public int getOptimalLiftIndex(){
        if(allFilledIn())
            return -1;
        ArrayList<Lift> liftArrTemp = new ArrayList(this.lifts);
        Collections.sort(this.lifts, comparator);
        return liftArrTemp.indexOf(this.lifts.get(0));
    }


    /**
     *
     * @return true if all lifts are maximum filled in
     */
    private boolean allFilledIn(){
        for (Lift ex: this.lifts) {
            if(ex.isAvailable())
                return false;
        }
        return true;
    }

    /**
     *
     * @param lift
     * @return needed energy(distance) for delivering lift to personPosition
     */
    private int calcLossEnergy(Lift lift){
        if (!lift.isAvailable())
            return -1;


        int directionLift = lift.getEndPosition() - lift.getCurrentPosition();

        boolean sameDirection = (directionLift < 0 && !personDirectionUp) || (directionLift > 0 && personDirectionUp);

        boolean includesMovementOfPerson =
                (!personDirectionUp && ((lift.getCurrentPosition() >= personPosition) &&
                        (lift.getEndPosition() <= personPosition) ) ) ||

                        (personDirectionUp && ((lift.getCurrentPosition() <= personPosition) &&
                                (lift.getEndPosition() >= personPosition) )  );

        if(lift.isMoving() && sameDirection && includesMovementOfPerson) {
//       both lift and person are moving in same direction
//       the movement of the elevator also includes the movement of person - incidentally
            return 0;
        }
        else{
//            calculate energy loss = distance
            return Math.abs(lift.getEndPosition() - personPosition);
        }
    }

    private Comparator<Lift> comparator = (o1, o2) -> {
        if(!o1.isAvailable() && !o2.isAvailable()){
            return o1.getId() - o2.getId();
        }
        if(!o1.isAvailable())
            return 1;
        if(!o2.isAvailable())
            return -1;

        if(calcLossEnergy(o1) - calcLossEnergy(o2) != 0)
            return calcLossEnergy(o1) - calcLossEnergy(o2);
        else{
            if(o1.isMoving() && o2.isMoving()){
                int direct1 = o1.getEndPosition() - o1.getCurrentPosition();
                int direct2 = o2.getEndPosition() - o2.getCurrentPosition();

                if((direct1 > 0 && direct2 < 0) || (direct1 < 0 && direct2 > 0))
                    return personDirectionUp && (direct1 > 0) ? -1 : 1;
                else
                    return o1.getId() - o2.getId();
            }
            else if(!o2.isMoving() && o1.isMoving()){
                return -1;
            }
            else if(!o1.isMoving() && o2.isMoving()){
                return 1;
            }
            else{
//                    both lifts don't move
                return o1.getId() - o2.getId();
            }
        }
    };
}