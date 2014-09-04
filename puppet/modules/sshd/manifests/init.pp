# Class: sshd
#
# This module manages sshd
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class sshd {
            
            package { 'ssh': 
                    ensure => present                     
            }
            
            file { "/etc/ssh/sshd_config": 
                  ensure    => "present",
                  source    => "puppet:///modules/sshd/sshd_config",
                  owner     => "root",
                  group     => "root",
                  mode      => 644,
                  require   => Package["ssh"], 
                  notify    => Service["ssh"],              
            }
            
            service { "ssh":
                    ensure      => running,
                    hasstatus   => true,
                    hasrestart  => true,
                    enable      => true,
            }
            
}
