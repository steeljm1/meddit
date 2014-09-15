class moodle::install {
        
        $web_dir = '/var/www'  
        $moodle_tar  = 'moodledata-19082014.tar.gz'
        
        # Create moodle dir
        file { "/var/moodledata":
              ensure    => "directory",
              owner     => "www-data",
              group     => "www-data",
              mode      => 0777,              
        }
        
        # push moodledata
        file { "/var/$moodle_tar":
              ensure    => "present",
              source    => "puppet:///modules/moodle/moodledata-19082014.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodledata
        exec { "unpack-moodle":              
              command => "/bin/tar -xvzf /var/$moodle_tar",
              #creates => '/var/moodledata', 
              cwd     =>  '/var',            class moodle::install {
        
        $web_dir = '/var/www'  
        $moodledata_tar  = 'moodledata-07092014.tar.gz'
        $moodleroot_tar = 'moodleroot.tar.gz'

        # Create moodle dir
        file { "/var/moodledata":
              ensure    => directory,
              owner     => "www-data",
              group     => "www-data",
              mode      => 0777,              
        }
        
        # push moodledata ## fix tar file
        file { "/var/$moodledata_tar":
              ensure    => "present",
              source    => "puppet:///modules/moodle/moodledata-07092014.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodledata
        exec { "unpack-moodledata":  
              #creates => '/var/moodledata',
              cwd     =>  '/var',            
              command => "/bin/tar -xpvzf /var/$moodledata_tar",                                         
              require => File['/var/$moodle_tar']
        }
        
        ## Setup moodle root
        # Ensure moodleroot dir
        $moodle_root    = '/var/www/moodle'
        
        ## Ceate moodle root
        file { "/var/www/moodle":
              ensure    => directory,
              owner     => "root",
              group     => "www-data",
              mode      => 0755,
        }
      
      
        ## push moodleroot
        file { "/var/$moodleroot_tar":
              ensure    => "present",
              source    => "puppet:///modules/moodle/moodleroot.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodleroot
        exec { "unpack-moodleroot":  
              #creates => '/var/moodledata',
              cwd     =>  '/var/www/moodle',            
              command => "/bin/tar -xpvzf /var/$moodleroot_tar",                                         
              require => File['/var/$moodleroot_tar']
        }
            
        file { "$moodle_root/moodle":
              ensure    => directory,
              owner     => "root",
              group     => "root",
              mode      => 0755,
              require   => Exec['unpack-moodleroot']
        }
      
       file { "$moodle_root/moodle/config.php":
              ensure    => present,
              source    => "puppet:///modules/moodle/config.php",
              owner     => "root",
              group     => "root",
              mode      => 0644,
              require   => File['$moodle_root/moodle']
        }
            
        
      
      
}

              subscribe => File['/var/$moodle_tar'],
        }
        
        file { "/var/www/moodle":
              ensure    => "directory",
              owner     => "root",
              group     => "www-data",
              mode      => 0755,
        }
      
        $moodle_root    = '/var/www/moodle/moodle'
      
        file { $moodle_root:
              ensure    => "directory",
              owner     => "root",
              group     => "root",
              mode      => 0755,
              require   => File["/var/www/moodle"]
        }
      
       file { "$moodle_root/config.php":
              ensure    => "present",
              source    => "puppet:///modules/moodle/config.php",
              owner     => "root",
              group     => "root",
              mode      => 0644,
              require   => File[$moodle_root]
        }
            
        
      
      
}
