package hospital

class Appointment {
    
    Date appointmentdate;
    String disease;
    Doctor doctor;
    Patient patient;

    static constraints = {
        appointmentdate Date : true
    }
}
