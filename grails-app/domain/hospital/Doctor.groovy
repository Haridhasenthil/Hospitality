package hospital

class Doctor extends Person{

    String specialization;
    int experience;
    String designation;
    String qualication;
    byte[] photo;
    String description;

    static hasMany = [patient:Patient]

    static constraints = {
        experience nullable: false
        specialization nullable: false      
    }

    static mapping = {
       photo sqlType:"LONGBLOB"
    }

    Doctor(){

    }
}
