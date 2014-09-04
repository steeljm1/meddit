#
# This manifest requires apache::service to refresh apache


class phpmyadmin::service inherits apache::service {

      class service {
                    class{ 'apache::service':    
                    }
                    include phpmyadmin::service
      }  

}