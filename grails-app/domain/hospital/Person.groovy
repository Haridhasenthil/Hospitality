package hospital

class Person {
    String name; 
    String gender; 
    String mobileNo;

    static constraints = {
        name nullable: false
        gender nullable: false
    }

     static mappings = {
        name type: 'string', length: 50
        mobileNo type: 'string', length: 10
    }

}
