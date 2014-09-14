# Class: apached::install
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
class apache::install {
      
      # install required packages 
      $prereqisites = ["apache2", "php5", "php5-gd", "php-xml-parser", "php5-intl", "php5-ldap", "openssl", "libapache2-mod-php5", "php-apc", "php5-mcrypt"]
      
      package { $prereqisites :
        ensure    => present,
      }
      
      #$opt_prereqisites = ["php5-mysql", "curl", "libcurl3", "php5-curl", "php-soap", "php-xml", "php-xmlrpc"]
      $opt_prereqisites = ["php5-mysql", "curl", "libcurl3", "php5-curl", "php-soap"]

      package { $opt_prereqisites :
        ensure  => present,
      }
           

}
