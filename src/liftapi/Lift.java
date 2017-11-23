package liftapi;

/**
 * Created by fatiz on 20.11.2017.
 */

public class Lift {
    private static int counter = 0;
    private int id;
    private int currentPosition;
    private int endPosition;
    private int maxPlaces;
    private int busyPlaces;
    private boolean isMoving;

    public Lift(int currentPosition, int endPosition) {
        this.currentPosition = currentPosition;
        this.endPosition = endPosition;

        if(endPosition == currentPosition)
            isMoving = false;
        else
            isMoving = true;

        this.maxPlaces = 10;
        this.busyPlaces = 4;

        this.id = counter;
        counter++;
    }

    public Lift(int currentPosition, int endPosition, int maxPlaces, int busyPlaces) {
        this(currentPosition, endPosition);

        this.maxPlaces = maxPlaces;
        this.busyPlaces = busyPlaces;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public int getId() {
        return id;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        if(this.currentPosition == endPosition)
            isMoving = false;
        else
            isMoving = true;
    }

    public int getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int endPosition) {
        this.endPosition = endPosition;
        if(this.endPosition == currentPosition)
            isMoving = false;
        else
            isMoving = true;
    }

    public boolean isAvailable(){
        return (maxPlaces - busyPlaces) > 0;
    }
}