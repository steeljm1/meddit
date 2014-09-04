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

#exec { "set-mysql-password":
#    unless  => "mysql -uroot -proot",
#    path    => ["/bin", "/usr/bin"],
#	command => "/usr/bin/mysqladmin -u root password M3dL@mP",
#    #command => "mysqladmin -uroot password P@ssword",
#    require => Class["mysql::install"],
#	notify => Class["mysql::service"],
#  }




}
