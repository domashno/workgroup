package se.lexicon;

public class ParkingSpace {
    private int spaceNumber;
    private SpaceStatus status;

    public ParkingSpace(int spaceNumber) {
        this.spaceNumber = spaceNumber;
        this.status=SpaceStatus.AVAILABLE;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public SpaceStatus getStatus() {
        return status;
    }
    public void occupy(){
        if(status == SpaceStatus.OCCUPIED){
            throw new IllegalStateException("Parking space is already occupied");
        }
        status = SpaceStatus.OCCUPIED;
    }
    public void release(){
        status = SpaceStatus.AVAILABLE;
    }
}
