package hospital
import java.text.SimpleDateFormat

class AppointmentController {
    def userService
    static List responseFormats = ['json','xml']

    def index() { }

    def appoint(){
     def reqIns = request.JSON;
        println"=============>>>>>>>>${reqIns}"
        
        def patientIns = new Patient(
            name: reqIns?.name,
            age: reqIns?.age,
            gender: reqIns?.gender,
            mobileNo: reqIns?.mobileNo,
            bloodgroup: reqIns?.bloodgroup,
            user: User.get(reqIns?.user)
        )
        def data = [:]
        if(patientIns.validate()){
            def log = userService.saveInstance(patientIns);
            println"===========${log}"
            data = ["emessage":true]
        } else {
            data = ["emessage":false]
        }
        respond data;
        println"============="
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        def date = sdf.parse(reqIns?.date)
        def appointIns =  new Appointment(
            appointmentdate:date,
            disease:reqIns.disease,
            doctor:Doctor.findByName(reqIns.doctorname),
            patient:Patient.findByName(reqIns.name)
        )
        if(appointIns.validate()){
            def log = userService.saveInstance(appointIns);
            data = ["emessage":true]
        } else {
            data = ["emessage":false]
        }
        respond data
        }

    def history(){
        def reqIns = request.JSON;
        def user = User.findById(reqIns.id.toLong());
        //println"===========${history}"
        def historyIns = Patient.createCriteria().list{
            eq("user",user)
        }
        
        def data 
        if(historyIns){
        // println"==========${historyIns}"
        def appointIns = Appointment.findByPatient(historyIns)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd")
        def date = sdf.format(appointIns.appointmentdate)
        //println"==============>>>>>>>>>>>>...${appointIns.doctor.name}"
        data = ["doctorname":appointIns.doctor.name,
        "specialization":appointIns.doctor.specialization,
        "appointmentdate":date,
        "disease":appointIns.disease,
        "patientname":historyIns.name,
        "age":historyIns.age]
    }
    else{
        data = ["message":"No appointment for this user"]
    }
        respond data;
    }
}
