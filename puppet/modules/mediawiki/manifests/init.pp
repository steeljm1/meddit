# Class: mediawiki
#
# This module manages mediawiki
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class mediawiki {

        ## push mediawikiroot
        file { "/mw-root.tar.gz":
              ensure    => "present",
              source    => "puppet:///modules/mediawiki/mw-root.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack mediawikiroot
        ## Ensure moodleroot.tar.gz is in files dir before running
        exec { "unpack-moodleroot":  
              creates      => '/var/www/wiki',
              #cwd          =>  '/var/www/moodle',            
              command      => "/bin/tar -xpvzf /mw-root.tar.gz",                                         
              require      => File['/mw-root.tar.gz']
        }


      ## push mediawiki config
        file { "/var/www/wiki/LocalSettings.php":
              ensure    => "present",
              source    => "puppet:///modules/mediawiki/LocalSettings.php",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }


}
