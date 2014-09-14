class mysql::create{ 
    
    $user       = moodleuser
    $password   = M3dm00d13Db
    $name       = moodle
    
    
    }
    define db( $user, $password ) {
      exec { "create-${name}-db":
        unless => "/usr/bin/mysql -uroot ${name}",
        command => "/usr/bin/mysql -uroot -e \"create database ${name};\"",
        require => Class["mysql::install"],
        notify => Class["mysql::service"],
      }

      exec { "grant-${name}-db":
        unless => "/usr/bin/mysql -u${user} -p${password} ${name}",
        command => "/usr/bin/mysql -uroot -e \"grant all on ${name}.* to ${user}@localhost identified by '$password';\"",
        require => [Class["mysql::service"], Exec["create-${name}-db"]]
      }
    
    }