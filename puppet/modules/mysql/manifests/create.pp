class mysql::create{ 
    
	       ################################  Create databases   #####################################################

        # Global variables
        $db_host      = 'localhost'

        # root password
        #$root_password  =  '*FC0AB3C571070AFC1E7F420C4DECB92A249682F0'  
        $root_password  = 'M3dL@mP'

        ################################  Create/Import Moodle DB   ################################################
        
        #$moodle_password  = '*9C167602ADE8001A2D66B729D3A2E035FD2BA75B'
        $moodle_password  = 'M3dm00d13Db'

        $moodledb = 'moodle'
        $user     = 'moodleuser'
        
        exec{"createMoodleUser":
            unless  => "/usr/bin/mysql -u${user} -p${moodle_password}",
	           command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE USER '${user}'@'localhost' IDENTIFIED BY '${moodle_password}'; GRANT ALL ON ${moodledb}.* TO '${user}'@'localhost';\"",
            require => Class["mysql::install"],
        }

        # Push database
        file { "/root/moodle-database.sql":
              ensure    => present,
              source    => "puppet:///modules/mysql/moodle-database.sql",
        }
          

	## fix - run onlyif!!! 
        exec {"setup_moodleDb":
             #unless => "/usr/bin/mysql -u${user} -p${moodle_password} ${moodledb}",             
             creates => "/var/lib/mysql/${moodledb}",
             command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE DATABASE IF NOT EXISTS ${moodledb};\"",
             require => Class["mysql::install"]
        } ->
        
        ## fix - run onlyif!!!
        exec {'importMoodleDb':
             # unless => "/usr/bin/mysql -u${user} -p${moodle_password} ${moodledb}",
             command => "/usr/bin/mysql -uroot -p$root_password ${moodledb} < /root/moodle-database.sql",             
             require  => File["/root/moodle-database.sql"]
        }



        ############################## Create/Import Silverstripe DB ########################################################
    
        $ss_user = 'root'
        #Password = same as $root_password         
        $ss_dbName  = 'SS_mysite'
                
        # Push database
        file { "/root/ss-database.sql":
              ensure    => present,
              source    => "puppet:///modules/mysql/ss-database.sql",
        }
          

  ## fix - run onlyif!!! 
        exec {"setup_SsDb":
             #unless => "/usr/bin/mysql -u${ss_user} -p${root_password} ${ss_dbName}",             
             creates => "/var/lib/mysql/${ss_dbName}",
             command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE DATABASE IF NOT EXISTS ${ss_dbName};\"",
             require => Class["mysql::install"]
        } ->
        
        ## fix - run onlyif!!!
        exec {'importSsDb':
             # unless => "/usr/bin/mysql -u${ss_user} -p${root_password} ${ss_dbName}",
             command => "/usr/bin/mysql -uroot -p$root_password ${ss_dbName} < /root/ss-database.sql",             
             require  => File["/root/ss-database.sql"]
        }



        ########################### Mediawiki  ##################################################################
        
        $mw_user = 'root'
        #Password = same as $root_password         
        $mw_dbName  = 'my_wiki'
        # Test import locale 
        $wgShellLocale = "en_NZ.utf8"        
                
        # Push database
        file { "/root/mw-database.sql":
              ensure    => present,
              source    => "puppet:///modules/mysql/mw-database.sql",
        }
        
        

        ## fix - run onlyif!!! 
        exec {"setup_mwDb":
             #unless => "/usr/bin/mysql -u${ss_user} -p${root_password} ${ss_dbName}",             
             creates => "/var/lib/mysql/${mw_dbName}",
             command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE DATABASE IF NOT EXISTS ${mw_dbName};\"",
             require => Class["mysql::install"]
        } ->
        
        ## fix - run onlyif!!!
        exec {'importmwDb':
             # unless => "/usr/bin/mysql -u${ss_user} -p${root_password} ${ss_dbName}",
             command => "/usr/bin/mysql -uroot -p$root_password ${mw_dbName} < /root/mw-database.sql",             
             require  => File["/root/mw-database.sql"],
             
        }
        
        
        
	
  }
