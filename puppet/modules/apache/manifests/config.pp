# Class: apache::service
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
class apache::config {
        
        #$USER =["steeljm1", "glennsp1", "liub3"]
        
        $sysAdmin = ["steeljm1"]
        
        # create a directory      
        file { "/home/$sysAdmin":
              ensure    => "directory",
        }
        
        file { "/home/$sysAdmin/public_html":
              ensure      => "directory",
              owner        => "$sysAdmin",
              group       => "www-data",
              mode        => 755,
              require     => Class["apache::install"],
              notify      => Class["apache::service"],
        }
        
#        file { "/var/www/ss":
#              ensure    => "directory",
#        }
        
        file { "/etc/apache2/apache2.conf":
              notify    => Class["apache::service"],
              ensure    => "present",
              source    => "puppet:///modules/apache/apache2.conf",
              owner     => "root",
              group     => "root",
              mode      => 644,
              require   => Class["apache::install"], 
        }
        
        
      	# push 000-default to client
      	file {"/etc/apache2/sites-enabled/000-default":
              ensure    	=> present,
              owner     	=> "root" ,
              group     	=> "root",
              mode      	=> 0644,
              source    	=> "puppet:///modules/apache/000-default",
              require   	=> Class["apache::install"],
              notify      => Class["apache::service"],
      	}
	          
	     # create symlink to apache headers module
	     file { "/etc/apache2/mods-enabled/headers.load":
		          ensure 		   => "link",
		          mode         => 0777,
		          target 		   => "/etc/apache2/mods-available/headers.load",
		          require      => Class["apache::install"],
              notify       => Class["apache::service"],
	     }

	     # create symlink to apache rewrite module
	     file { "/etc/apache2/mods-enabled/rewrite.load":
		          ensure 		   => "link",
		          mode         => 0777,
		          target 		   => "/etc/apache2/mods-available/rewrite.load",
		          require      => Class["apache::install"],
		          notify       => Class["apache::service"],
	     }
      
      # Enable userdir module 
      file { '/etc/apache2/mods-enabled/userdir.load':
                ensure => 'link',
                target => '/etc/apache2/mods-available/userdir.load',
                notify => Class["apache::service"],
                require => Class["apache::install"],
        }
              
       file {"/etc/apache2/mods-enabled/userdir.conf" :
              ensure => present,
              owner => 'root',
              group => 'root',
              mode => 0644,
              content => template('apache/userdir.erb'),
              notify => Class["apache::service"],
              require => Class["apache::install"],
        }
                       
        
      file { "/etc/apache2/mods-available/php5.conf":
              notify      => Class["apache::service"],
              ensure      => "present",
              source      => "puppet:///modules/apache/php5.conf",
              owner       => "root",
              group       => "root",
              mode        => 644,
              require     => Class["apache::install"], 
        }
      
      # SSL
      # Ensure ssl local certs dir is present
      file { "/etc/ssl/localcerts":
              ensure    => "directory",
      }
      
      file { "/etc/apache2/mods-enabled/ssl.load":
              ensure       => "link",
              mode         => 0777,
              target       => "/etc/apache2/mods-available/ssl.load",
              require      => Class["apache::install"],
              notify       => Class["apache::service"],
       }
        
        # push ssl
        file {"/etc/apache2/sites-enabled/default-ssl":
              ensure      => present,
              owner       => "root" ,
              group       => "root",
              mode        => 0644,
              source      => "puppet:///modules/apache/default-ssl",
              require     => Class["apache::install"],
              notify      => Class["apache::service"],
        }
      
      # SSL cert pem
      file { "/etc/ssl/localcerts/apache-meddit.pem":              
              ensure      => "present",
              source      => "puppet:///modules/apache/apache-meddit.pem",
              owner       => "root",
              group       => "root",
              mode        => 0600,
              require     => Class["apache::install"], 
              notify      => Class["apache::service"],
        }
      
      # SSL cert key
      file { "/etc/ssl/localcerts/apache-meddit.key":              
              ensure      => "present",
              source      => "puppet:///modules/apache/apache-meddit.key",
              owner       => "root",
              group       => "root",
              mode        => 0600,
              require     => Class["apache::install"],
              notify      => Class["apache::service"],
        }
      
      
      
      
      file { "/var/www/test.php":             
              ensure      => "present",
              source      => "puppet:///modules/apache/test.php",              
              require     => Class["apache::install"], 
        }
      
}
