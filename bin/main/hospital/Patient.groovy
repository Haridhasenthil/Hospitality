package hospital

class Patient extends Person{
    String bloodgroup;
    int age;
    User user;
 
    static constraints = {
        bloodgroup nullable: false
    }

   
    Patient(){

    }

}
