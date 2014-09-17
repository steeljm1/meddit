class ntp::service {
  
              service {"ntp" :
                      ensure => running,
                      hasstatus => true,
                      hasrestart => true,
                      enable => true,
                      require => Class["ntp::config"],
              }
  
}