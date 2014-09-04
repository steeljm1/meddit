class phpmyadmin::config {

        
        file { "/etc/phpmyadmin/apache.conf":
              notify    => Class["phpmyadmin::service"],
              ensure    => "present",
              source    => "puppet:///modules/phpmyadmin/apache.conf",
              owner     => "root",
              group     => "root",
              mode      => 644,
              require   => Class["phpmyadmin::install"], 
        }
        
        file { "/etc/phpmyadmin/config.inc.php":
              notify    => Class["phpmyadmin::service"],
              ensure    => "present",
              source    => "puppet:///modules/phpmyadmin/config.inc.php",
              owner     => "root",
              group     => "root",
              mode      => 644,
              require   => Class["phpmyadmin::install"], 
        }
        
        
}
