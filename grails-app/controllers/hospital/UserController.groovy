package hospital

class UserController {
    static List responseFormats = ['json','xml']
    UserService userService=new UserService();
    def index() { }

     def signup(){
        def reqIns = request.JSON;
        def signupIns = new User(
            name: reqIns?.name,
            password: reqIns?.password,
            email: reqIns?.email
        )
        def data = [:]
        if(signupIns.validate()){
            def log = userService.saveInstance(signupIns);
            data = ["emessage":true]
        } else {
            data = ["emessage":false]
        }
        respond data;
    }

    def login(){
        def reqIns = request.JSON;
        def loginIns = User.findByEmailAndPassword(reqIns.email,reqIns.password)
        println"===================>>>>>>>>>>>${loginIns.id}"
        def data = [:]
        if (loginIns){
            data = ["id":loginIns.id,"name":loginIns.name,"emessage":true]
        } else {
            data = ["emessage":false]
        }
        respond data;
    }
}
