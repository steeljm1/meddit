class mysql::config {
        file { "/etc/mysql/my.cnf":
                ensure => present,
                source => "puppet:///modules/mysql/my.cnf",
                mode => 0444,
                owner => "mysql",
                group => "mysql",
                require => Class["mysql::install"],
                notify => Class["mysql::service"],

        }
        
        file { "/etc/mysql/debian-start":
                ensure => present,
                source => "puppet:///modules/mysql/debian-start",
                owner => "root",
                group => "root",
                mode => 0555,
                require => Class["mysql::install"],
                notify => Class["mysql::service"],
        }

#### Comment after initial install

        #$root_password  =  '*FC0AB3C571070AFC1E7F420C4DECB92A249682F0'
        $root_password  = 'M3dL@mP'

       exec { "set-mysql-password":               
               unless => "/usr/bin/mysql -uroot -p${root_password}",
               #path    => ["/bin", "/usr/bin"],
               ## Test hashed
               command => "/usr/bin/mysqladmin -u root password ${root_password}",                          
               require => Class["mysql::install"],
	             notify => Class["mysql::service"],
        }
  
  



}
