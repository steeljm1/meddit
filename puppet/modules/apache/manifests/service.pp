# Class: apache::service
#
# This module manages apache
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class apache::service {
      
      # ensure apache is running 
      service { "apache2" :
                  ensure      => running,
                  hasstatus   => true,
                  hasrestart  => true,
                  enable      => true,
                  require     => Class["apache::config"],
      }

}
