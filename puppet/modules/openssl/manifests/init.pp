# Class: openssl
#
# This module manages openssl
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class openssl {   
              
            package { "libssl":
                      ensure => "latest",
                      
            }
              

}
