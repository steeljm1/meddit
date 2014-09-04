# Class: fail2ban
#
# This module manages fail2ban
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class fail2ban {

            package { 'fail2ban': 
                    ensure => present                     
            }
            
            file { "/etc/fail2ban/fail2ban.conf": 
                  ensure    => "present",
                  source    => "puppet:///modules/fail2ban/fail2ban.conf",
                  owner     => "root",
                  group     => "root",
                  mode      => 644,
                  require   => Package["fail2ban"], 
                  notify    => Service["fail2ban"],              
            }

            file { "/etc/fail2ban/jail.conf": 
                  ensure    => "present",
                  source    => "puppet:///modules/fail2ban/jail.conf",
                  owner     => "root",
                  group     => "root",
                  mode      => 644,
                  require   => Package["fail2ban"], 
                  notify    => Service["fail2ban"],              
            }
                        
            service { "fail2ban":
                    ensure      => running,
                    hasstatus   => true,
                    hasrestart  => true,
                    enable      => true,
            }

}
