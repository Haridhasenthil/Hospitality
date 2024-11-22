package hospital
import grails.web.mapping.LinkGenerator
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Base64

class DoctorController {
    static List responseFormats = ['json','xml']

    def index() { }
    def showdoctordetails() {
    def data = []
    def doctorIns = Doctor.getAll()
    println "===============>>>>>>>>${doctorIns}"
    doctorIns.each { list ->
        def imageLink = grailsLinkGenerator.link(controller: 'doctor', action: 'getimage',params: ['id':list.id],absolute:true)
        println "imageLink --> ${imageLink}"
        def doctor = [
            "id": list.id,
            "name": list.name,
            "photo": imageLink, 
            "specialization": list.specialization
        ]
        data << doctor
    }
    respond data
}

def showparticulardoctor() {
    def reqIns = request.JSON;
    def doctorIns = Doctor.get(reqIns.id.toLong())
    def imageLink =  grailsLinkGenerator.link(controller: 'doctor',action:'getimage',params: ['id':doctorIns.id],absolute:true)
    def data = ["name":doctorIns.name,
    "photo":imageLink,
    "specialization":doctorIns.specialization,
    "description":doctorIns.description,
    "experience":doctorIns.experience,
    "qualication":doctorIns.qualication,
    "designation":doctorIns.designation]
    respond data;
}

def getimage() {
    def doctor = Doctor.get(params.long("id"))
    
    if (!doctor || !doctor.photo) {
        response.sendError(404, 'Image not found')
        return
    }
    response.setContentType("image/jpeg")
    response.setContentLength(doctor.photo?.size()?:0)
    OutputStream out = response.getOutputStream();
    out.write(doctor.photo);
    out.close();
    }
    

   def save(){
     def stringpath = "/home/amphisoft/Amphisoft/haridha/hospital/src/imageb5.jpg"
     byte[] imageBytes =  Files.readAllBytes(Paths.get(stringpath))
     String base64Image = Base64.getEncoder().encodeToString(imageBytes)
     def doctorIns = new Doctor(
        name:"Dr. Shashi Ranjani",
        gender:"female",
        mobileNo: "9834659690",
        specialization:"Pediatric Oncology",
        experience:4,
        designation:"Consultant",
        qualication:"M.B.B.S., DNB(paed), FPHO & BMT",
        description:"Department of Pediatric Oncology",
        photo: imageBytes
     )
     println"==================>>>>>>.${doctorIns}"
     def data
     if(doctorIns){
     doctorIns.save();
      data = ["emessage":true]
     }else{
        data= ["emessage":false]
     }
     respond data
   }

   def saveshow(){
    def doctor = Doctor.get(4);
    println"==============${doctor.photo}"
    def stringpath="/home/amphisoft/Amphisoft/haridha/hospital/src/imageb2.jpg"
    byte[] image =  Files.readAllBytes(Paths.get(stringpath))
    doctor.photo=image;
    def data
    if(doctor){
    doctor.save();
     data = ["emessage":true];
    }else{
        data= ["emessage":false]
     }
    respond data;
   }
}