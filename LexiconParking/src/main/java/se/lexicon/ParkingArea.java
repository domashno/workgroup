package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class ParkingArea {
    private String areaID;
    private String name;
    private List<ParkingSpace> parkingSpaces;//List contains ParkingSpace object

    public ParkingArea(String areaID, String name) {
        this.areaID = areaID;
        this.name = name;
        parkingSpaces = new ArrayList<>();
        //Here Java creates 20 parking space objects and stores them in an array list.
        for(int i=1;i<=20;i++){
            ParkingSpace space = new ParkingSpace(i);
            parkingSpaces.add(space);
        }
    }

    public String getAreaID() {
        return areaID;
    }

    public String getName() {
        return name;
    }
    public List<ParkingSpace> getParkingSpaces(){
        return new ArrayList<>(parkingSpaces);
    }
}
