class moodle::install {
        
        $web_dir = '/var/www'  
        #$moodledata_tar  = 'moodledata-07092014.tar.gz'
        

        # Create moodledata dir
        file { "/var/moodledata":
              ensure    => directory,
              owner     => "www-data",
              group     => "www-data",
              mode      => 0777,              
        }
        
        # push moodledata ## fix tar file
        file { "/moodledata-2014-10-25-154024.tar.gz":
              ensure    => "present",
              source    => "puppet:///modules/moodle/moodledata-2014-10-25-154024.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodledata
        exec { "unpack-moodledata":  
              creates => '/var/moodledata',
              cwd     =>  '/',            
              command => "/bin/tar -xpvzf /moodledata-2014-10-25-154024.tar.gz",                                         
              require => File['/moodledata-2014-10-25-154024.tar.gz']
        }
        
        ## Setup moodle root
        # Ensure moodleroot dir
        #$moodle_root    = '/var/www/moodle'
        
        ## Ceate moodle root
        file { "/var/www/moodle":
              ensure    => directory,
              owner     => "root",
              group     => "www-data",
              mode      => 0755,
        }
      
      
        ## push moodleroot
        file { "/moodleroot-2014-10-25-154024.tar.gz":
              ensure    => "present",
              source    => "puppet:///modules/moodle/moodleroot-2014-10-25-154024.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodleroot
        ## Ensure moodleroot.tar.gz is in files dir before running
        exec { "unpack-moodleroot":  
              creates	     => '/var/www/moodle/moodle',
	            cwd          =>  '/',            
              command      => "/bin/tar -xpvzf /moodleroot-2014-10-25-154024.tar.gz",                                         
              require      => File['/moodleroot-2014-10-25-154024.tar.gz']
        }
            
#        file { "/var/www/moodle/moodle":
#              ensure    => directory,
#              owner     => "root",
#              group     => "root",
#              mode      => 0755,
#              require   => Exec['unpack-moodleroot']
#        }
      
       file { "/var/www/moodle/moodle/config.php":
              ensure    => present,
              source    => "puppet:///modules/moodle/config.php",
              owner     => "root",
              group     => "root",
              mode      => 0644,
              #require   => File['/var/www/moodle/moodle']
              require   => Exec["unpack-moodleroot"]
        }
            
        
      
      
}

