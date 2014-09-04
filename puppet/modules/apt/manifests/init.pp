# Class: apt
#
# This module manages apt
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class apt {
            
         file { "/etc/apt/sources.list": 
                  ensure    => "present",
                  source    => "puppet:///modules/apt/sources.list",
                  owner     => "root",
                  group     => "root",
                  mode      => 644,
            }   
            
}
