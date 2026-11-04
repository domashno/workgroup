package se.lexicon;

import java.time.format.DateTimeFormatter;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {
        /*ParkingArea area = new ParkingArea("A001","Lexicon Växjö");
        IO.println("Parking Area: " +area.getName());
        IO.println("Total Spaces: " +area.getParkingSpaces().size());

        for(ParkingSpace space : area.getParkingSpaces()){
            IO.println("Space " +space.getSpaceNumber() +" - " +space.getStatus());
        }*/

        ParkingService parkingService = new ParkingService();
        boolean running = true;
        while(running){
            IO.println("\n==========LEXICON PARKING SYSTEM===========");
            IO.println("1.  Show all parking spaces");
            IO.println("2.  Show available parking spaces");
            IO.println("3.  Make a reservation");
            IO.println("4.  Check out (Leave parking)");
            IO.println("5.  Show all reservations");
            IO.println("0.  Exit");

            String input = IO.readln("Enter your choice:");
            int choice;
            try{
                choice = Integer.parseInt(input);
            }catch (NumberFormatException e){
                IO.println("Please enter a valid number (0-5).");
                continue;
            }
            switch(choice){
                case 1->parkingService.ShowAllParkingSpaces();
                case 2->parkingService.showAvailableSpaces();
                case 3->{

                    String name = IO.readln("Enter your name: ");
                    String phone = IO.readln("Enter your phone: ");
                    String vehicle = IO.readln("Enter your vehicle number: ");
                    parkingService.showAvailableSpaces();
                    try{
                       int spaceNumber = Integer.parseInt(IO.readln("Select parking space: "));
                        int hours = Integer.parseInt(IO.readln("Parking duration(hours): "));
                        Reservation reservation = parkingService.makeReservation(name,phone,vehicle,spaceNumber,hours);
                        IO.println("Reservation successful");
                        IO.println("Reservation ID: " +reservation.getReservationId());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
                        IO.println("Expected end time: " +reservation.getExpectedEndTime().format(formatter));

                    }
                     catch (NumberFormatException e){
                        IO.println("Please enter a valid number");
                    }catch (IllegalArgumentException|IllegalStateException e){
                        IO.println("Error: " +e.getMessage());
                    }


                }
                case 4 -> {
                    String reservationId = IO.readln("Enter reservation id: ");
                    if(parkingService.completeReservation(reservationId)){
                        IO.println("Check out successful");
                        IO.println("Parking space is now available");
                    }
                    else{
                        IO.println("Check out not completed");
                    }
                }
                case 5-> parkingService.showReservations();
                case 0->{
                    running = false;
                    IO.println("Thank you for using Lexicon parking");
                }
                default -> IO.println("Invalid choice. please try again.");
            }

        }

    }
}
