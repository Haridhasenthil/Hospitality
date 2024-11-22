package hospital

class User {
    String name;
    String password;
    String email;

    static constraints = {
        name nullable: false
        password nullable: false 
        email email: true
    }
}
