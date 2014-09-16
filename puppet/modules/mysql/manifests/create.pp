class mysql::create{ 
    
	       ####  Create databases   #####

        # Global variables
        $db_host      = 'localhost'

        # root password
        #$root_password  =  '*FC0AB3C571070AFC1E7F420C4DECB92A249682F0'  
        $root_password  = 'M3dL@mP'

        # Moodle db
        #$moodle_password  = '*9C167602ADE8001A2D66B729D3A2E035FD2BA75B'
        $moodle_password  = 'M3dm00d13Db'

        $moodledb = 'moodle'
        $user     = 'moodleuser'
          
         ## fix - run onlyif!!! 
        exec {"create_moodle_user":
             unless => "/usr/bin/mysql -u${user} -p${moodle_password} ${moodledb}",
             # command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE DATABASE ${moodledb}; GRANT ALL ON ${moodledb}.* TO '${user}'@'localhost' IDENTIFIED BY '${moodle_password}';\"",
             command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE DATABASE ${moodledb}; CREATE USER '${user}'@'localhost' IDENTIFIED BY '${moodle_password}'; GRANT ALL ON ${moodledb}.* TO '${user}'@'localhost';\"",
        }

        # Push database
        file { "/root/moodle-database.sql":
              ensure    => present,
              source    => "puppet:///modules/mysql/moodle-database.sql",
        }
        
        ## fix - run onlyif!!!
        exec {'importMoodleDb':
              unless => "/usr/bin/mysql -u${user} -p${moodle_password} ${moodledb}",
             command => "/usr/bin/mysql -uroot -p$root_password ${moodledb} < /root/moodle-database.sql",
             require => File["/root/moodle-database.sql"],

       }



        # Create Silverstripe db





        # Mediawiki

        
        
        
	
  }
