package se.lexicon;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParkingService {
    private ParkingArea parkingArea;
    private List<Reservation> reservations;


    //Constructor runs when a ParkingService object is created in Main
    public ParkingService() {
        parkingArea = new ParkingArea("A001", "Lexicon Växjö");
        reservations = new ArrayList<>();
    }

    //Show all parking spaces
    public void ShowAllParkingSpaces() {
        for (ParkingSpace space : parkingArea.getParkingSpaces()) {
            IO.println("Space " + space.getSpaceNumber() + " - " + space.getStatus());
        }
    }
    //Show all available spaces

    public void showAvailableSpaces() {
        for (ParkingSpace space : parkingArea.getParkingSpaces()) {
            if (space.getStatus() == SpaceStatus.AVAILABLE) {
                IO.println("Space " + space.getSpaceNumber() + " is available");
            }
        }

    }

    //Create a reservation
    public Reservation makeReservation(String name, String phone, String vehicleNumber, int spaceNumber, int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("Parking duration must be greater than zero");
        }
        ParkingSpace selectedSpace = null;
        for(ParkingSpace space: parkingArea.getParkingSpaces()){
            if(space.getSpaceNumber()== spaceNumber){
                selectedSpace = space;
                break;
            }
        }
        if(selectedSpace== null){
            throw new IllegalArgumentException("Invalid parking space number");
        }
        if(selectedSpace.getStatus()== SpaceStatus.OCCUPIED){
            throw new IllegalStateException("This parking space is already occupied");
        }
        String personId = UUID.randomUUID().toString();
        Person person = new Person(personId,name,phone,vehicleNumber);

        String reservationId = UUID.randomUUID().toString();
        Reservation reservation = new Reservation(reservationId,person,selectedSpace, Duration.ofHours(hours));
        reservations.add(reservation);
        return reservation;
    }
    //Complete a reservation when leaving
    public boolean completeReservation(String reservationId){
        for(Reservation reservation: reservations){
            if (reservation.getReservationId().equals(reservationId)){
                if(reservation.getStatus()==ReservationStatus.COMPLETED){
                    IO.println("Reservation already completed");
                    return false;
                }
                reservation.completeReservation();
                return true;
            }
        }
        return false;
    }

    //Show reservation history
    public void showReservations(){
        if(reservations.isEmpty()){
            IO.println("No reservation found");
            return;
        }
        for(Reservation reservation: reservations){
            IO.println("------------------------------");
            IO.println("Reservation ID: " +reservation.getReservationId());
            IO.println("Person: " +reservation.getPerson().getName());
            IO.println("Vehicle: " +reservation.getPerson().getVehicleRegistrationNumber());
            IO.println("Parking space: " +reservation.getParkingSpace().getSpaceNumber());
            IO.println("Start time: " +reservation.getStartTime());
            IO.println("Expected End: " +reservation.getExpectedEndTime());
            IO.println("Status: " +reservation.getStatus());






        }
    }


}