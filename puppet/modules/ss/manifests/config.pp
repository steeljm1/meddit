class ss::config {
  #
  # push moodledata ## fix tar file
        file { "/ss-root-2014-10-25-154024.tar.gz":
              ensure    => "present",
              source    => "puppet:///modules/ss/ss-root-2014-10-25-154024.tar.gz",
              owner     => "root",
              group     => "root",
              mode      => 0644,              
        }
        
        # unpack moodledata - unpack in /
        exec { "unpack-ss_root":  
              creates => '/var/www/ss',
              cwd     =>  '/',            
              command => "/bin/tar -xpvzf /ss-root-2014-10-25-154024.tar.gz",                                         
              require => File['/ss-root-2014-10-25-154024.tar.gz']
        }
  
}