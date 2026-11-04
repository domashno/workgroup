package se.lexicon;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private String reservationId;
    private Person person;
    private ParkingSpace parkingSpace;

    private LocalDateTime startTime;
    private Duration plannedDuration;
    private LocalDateTime expectedEndTime;
    private LocalDateTime completedAt;

    private ReservationStatus status;

    public Reservation(String reservationId, Person person, ParkingSpace parkingSpace, Duration plannedDuration) {
       if(reservationId==null|| reservationId.isBlank()){
           throw new IllegalArgumentException("Reservation ID is required");
       }
       if(person==null|| parkingSpace== null){
           throw new IllegalArgumentException("Person and parking space are required");
       }
        if(plannedDuration==null|| plannedDuration.isZero() || plannedDuration.isNegative()){
            throw new IllegalArgumentException("Parking duration must be positive");
        }
       parkingSpace.occupy();
        this.reservationId = reservationId;
        this.person = person;
        this.parkingSpace = parkingSpace;
        this.plannedDuration = plannedDuration;
        this.startTime = LocalDateTime.now();
        this.expectedEndTime = startTime.plus(plannedDuration);
        this.status = ReservationStatus.ACTIVE;

    }

    public String getReservationId() {
        return reservationId;
    }

    public Person getPerson() {
        return person;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Duration getPlannedDuration() {
        return plannedDuration;
    }

    public LocalDateTime getExpectedEndTime() {
        return expectedEndTime;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void completeReservation(){
        if(status == ReservationStatus.COMPLETED){
            throw new IllegalStateException("Reservation is already completed");
        }
        completedAt = LocalDateTime.now();
        status = ReservationStatus.COMPLETED;
        parkingSpace.release();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
        IO.println("Completed at: " +completedAt.format(formatter));
    }
}
