package hospital
import java.text.SimpleDateFormat

class PatientController {

    static List responseFormats = ['json','xml']
    def userService
    def index() { }
    def save(){
        def reqIns = request.JSON;
        println"=============>>>>>>>>${reqIns}"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        def date = sdf.parse(reqIns.DOB)
        def signupIns = new Patient(
            name: reqIns?.name,
            DOB: date,
            gender: reqIns?.gender,
            mobileNo: reqIns?.mobileNo,
            bloodgroup: reqIns?.bloodgroup,
            user: User.get(reqIns?.user)
        )
        def data = [:]
        if(signupIns.validate()){
            def log = userService.saveInstance(signupIns);
            println"===========${log}"
            data = ["emessage":true]
        } else {
            data = ["emessage":false]
        }
        respond data;
    }
}

