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
              cwd     =>  '/var',            
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
