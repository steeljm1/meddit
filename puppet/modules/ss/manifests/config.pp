class ss::config {
  
  # push moodledata ## fix tar file
        file { "/ss-root-2014-09-23-090342.tar.gz":
              ensure    => "present",
              source    => "puppet:///modules/ss/ss-root-2014-09-23-090342.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodledata - unpack in /
        exec { "unpack-ss_root":  
              creates => '/var/www/ss',
              cwd     =>  '/var',            
              command => "/bin/tar -xpvzf /ss-root-2014-09-23-090342.tar.gz",                                         
              require => File['/ss-root-2014-09-23-090342.tar.gz']
        }
  
}