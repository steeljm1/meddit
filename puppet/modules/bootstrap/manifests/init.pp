# Class: bootstrap
#
# This module manages bootstrap
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class bootstrap {
  
#        # ensure local apt cache index is up to date before beginning
#        exec { 'apt-get update':
#              command => '/usr/bin/apt-get update'
#        }


	# ensure local apt cache index is up to date before beginning
        package { 'puppet':
              ensure => present
        }
        
        service { "puppet" :
                  ensure      => running,
                  hasstatus   => true,
                  hasrestart  => true,
                  enable      => true,
                  require     => Package["puppet"],
        }
        
        file { "/etc/default/puppet" :
                ensure    => present,
                owner     => 'root',
                group     => 'root',
                mode      => 0644,
                content   => template('bootstrap/puppet.erb'),
                require     => Package["puppet"],
        }
        
        
        # silence puppet annoyance about the puppet group
        group { 'puppet':
              ensure => 'present'
        }
            
}
