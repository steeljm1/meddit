class mysql::create{ 
    
	####  Create databases   #####
        
        # Global variables
        $db_host      = 'localhost'
        
        # Create Moodle db          
        $moodle_password  = '*9C167602ADE8001A2D66B729D3A2E035FD2BA75B'
          
        mysql::db { 'moodle':
              user            => 'moodleuser',
              password_hash   => $moodle_password,
              host            => $db_host,
              grant           => ['SELECT', 'INSERT', 'UPDATE', 'DELETE', 'CREATE', 'CREATE TEMPORARY TABLES', 'DROP', 'INDEX', 'ALTER'],
              sql             => '/root/moodle-database.sql',
              require         => File['/root/moodle-database.sql']
        }
        
        # Import moodle-database
        file { "/root/moodle-database.sql":
              ensure    => present,
              source    => "puppet:///modules/mysql/moodle-database.sql",
        }
  
  
        # Create Silverstripe db
        
        
        
        
        
        # Mediawiki
        
	
	
	
	
  }