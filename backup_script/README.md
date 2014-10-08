Please use visualMidwiferyBackup-ver.1.2.sh.  It is complete and has been tested.

The script will create a log file (/var/backups/meddit.log), add a time stamp, uses the tar.gz format and 
places the files in:  /var/backups.  To maintain same owner and file permissions, -pczf flags are set in 
the tar command.  The script can be run manually from within any directory on the system by the system 
administrator as root.  In addition, the script can be run by cron by placing an entry in /etc/crontab.

The script backs up the following:

 /etc 	#System config directory
 
 /var/log 	#System log directory
 
 moodle-database.sql 	#Moodle database
 
 /var/moodledata 	#Moodledata directory
 
 /var/www/moodle/moodle 	#Moodle root directory
 
 ss-database.sql 	#Silverstripe database
 
 /var/www/ss 	#Silverstripe root directory
 
 mw-database.sql 	#Mediawiki database
 
 /var/www/wiki 	#Mediawiki root directory
 
 /var/www/src 	#Visual Midwifery Android app source directory
 
 /var/www/*.php 	#Web site php files and dependent image directories
 
 
Recovery
The backup script stated above maintains the full path of the directories/files.  It is also important 
to use /bin/tar -xpvzf [fileName] when extracting the tar files so that ownership and file permissions 
are maintained.  Please use the backup script as a guide for establishing directory/file paths and 
MySQL database imports.
