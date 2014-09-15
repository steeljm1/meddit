class mysql::create{ 
    
	    ####  Create databases   #####
        
        # Global variables
        $db_host      = 'localhost'
        $root_password  =  '*2B5396BABAD4AC79025680BDC14F8669DD1414BD'
         
        # Moodle db          
        $moodle_password  = '*9C167602ADE8001A2D66B729D3A2E035FD2BA75B'
        $moodledb = 'moodle' 
        $user            = 'moodleuser'
        
        exec {"create_moodle_user":                              
          
              command => "/usr/bin/mysql -uroot -p$root_password -e \"CREATE DATABASE ${moodledb}; GRANT ALL on ${moodledb}.* to ${user}@localhost identified by '$moodle_password';\"",
              
        } 
         
        # Push database 
        file { "/root/moodle-database.sql":
              ensure    => present,
              source    => "puppet:///modules/mysql/moodle-database.sql",
        } 
         
        exec {'importMoodleDb':

              command => "/usr/bin/mysql -uroot -p$root_password ${moodledb} < /root/moodle-database.sql",
              require => File["/root/moodle-database.sql"],

        }  
		

		
        # Create Silverstripe db
        
        
        
        
        
        # Mediawiki
        
        
        
        
        
        
	
  }