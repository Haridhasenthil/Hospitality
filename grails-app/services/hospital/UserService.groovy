package hospital

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    def saveInstance(instance) {
        boolean status=true;
          if(instance){
            instance.save()
            status = true
          } else {
            status = false
          }
          return status;
    }
}
