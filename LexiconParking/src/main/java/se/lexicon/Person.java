package se.lexicon;

public class Person {
    private String personId;
    private String name;
    private String phoneNumber;
    private String vehicleRegistrationNumber;

    public Person(String personId, String name, String phoneNumber, String vehicleRegistrationNumber) {
        if(name==null||name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if(phoneNumber==null||phoneNumber.isBlank()){
            throw new IllegalArgumentException("Phone number cannot be empty");
        }
        if(vehicleRegistrationNumber==null||vehicleRegistrationNumber.isBlank()){
            throw new IllegalArgumentException("Vehicle number cannot be empty");
        }
        this.personId = personId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicleRegistrationNumber = vehicleRegistrationNumber;
    }

    public String getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }
}
