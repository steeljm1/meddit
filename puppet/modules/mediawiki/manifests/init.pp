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
        file { "/mw-root-2014-10-17-132917.tar.gz":
              ensure    => "present",
              source    => "puppet:///modules/mediawiki/mw-root-2014-10-17-132917.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack mediawikiroot
        ## Ensure moodleroot.tar.gz is in files dir before running
        exec { "unpack-mediawiki":  
              creates      => '/var/www/wiki',
              #cwd          =>  '/var/www/moodle',            
              command      => "/bin/tar -xpvzf /mw-root-2014-10-17-132917.tar.gz",                                         
              require      => File['/mw-root-2014-10-17-132917.tar.gz']
        }


      ## push mediawiki config
        file { "/var/www/wiki/LocalSettings.php":
              ensure    => "present",
              source    => "puppet:///modules/mediawiki/LocalSettings.php",
              owner     => "root",
              group     => "root",
              mode      => 0644,
              require   => Exec["unpack-mediawiki"]              
        }


}
