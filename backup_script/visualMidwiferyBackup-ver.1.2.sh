#!/bin/bash

############################################################################################################
#                                                                                                          #
#                                   Visual Midwifery - Backup Script                                       #
#                                                                                                          #
# This script will backup the following:                                                                   #
# 	* /etc /var/log										           #		   
#       * Web site files + /var/www/src                                                                    # 
#  	* Moodle database                                                                                  # 
#       * Moodledata directory                                                                             # 
#	* Moodleroot directory                                                                             # 
#	* Silverstripe database                                                                            # 
#	* Silverstripe assets directory                                                                    # 
#	* Silverstripe root directory                                                                      # 
#                                                                                                          #
# Add this script to cron if you want this to run on a schedule.                                           #
#                                                                                                          #
#                                                                                                          #
############################################################################################################
######################################      Variables      #################################################
#
# Root folder for backups
rootBackupFolder=/var/backups

# log
logfile="meddit.log"
logfilePath=$rootBackupFolder

if [ ! -f $logfilePath/$logfile ]; then
        touch $logfilePath/$logfile
fi


# Get the backup date
backupDate=`date +%Y-%m-%d-%H%M%S`

# Number of backups to keep. Make this one more than the number you want to keep!
backupsToKeep=3

######################################    Begin Logging    ################################################

echo "#############################################################################" >> $logfilePath/$logfile
echo "Beginning site backup using visualMidwiferyBackup.sh. Starting at $backupDate" >> $logfilePath/$logfile

###########################################  System  #######################################################
#

echo " Creating final compressed (tar.gz) TAR file of /etc /var/log..." >> $logfilePath/$logfile

# Paths to backup
pathsToBackup="/etc /var/log"

# Check if system directory in rootBackupFolder exists. If not, create it
rootSystemBackupFolder="$rootBackupFolder/visualMidwiferySystem"

if [ ! -d $rootSystemBackupFolder ]; then
        mkdir -p $rootSystemBackupFolder
fi

tar -pczf $rootSystemBackupFolder/system-$backupDate.tar.gz $pathsToBackup


###################################    Web Site files/App src    ###########################################

echo " Creating final compressed (tar.gz) TAR file of web files..." >> $logfilePath/$logfile

# Files to backup
filesToBackup="/var/www/images /var/www/src /var/www/index.php /var/www/style.css /var/www/download.php /var/www/download_proc.php /var/www/download_src.php /var/www/admin.php /var/www/source.php"

# Check if web files directory in rootBackupFolder exists. If not, create it
rootWebBackupFolder="$rootBackupFolder/visualMidwiferyWeb"

if [ ! -d $rootWebBackupFolder ]; then
        mkdir -p $rootWebBackupFolder
fi

tar -pczf $rootWebBackupFolder/web-$backupDate.tar.gz $filesToBackup


######################################    Moodle Backup    #################################################
#

echo " Dumping moodle database, using ..." >> $logfilePath/$logfile
echo " user:root database:moodle host:localhost " >> $logfilePath/$logfile

# Check if moodle directory in rootBackupFolder exists. If not, create it
rootMoodleBackupFolder="$rootBackupFolder/moodle"

if [ ! -d $rootMoodleBackupFolder ]; then
	mkdir -p $rootMoodleBackupFolder
fi

# Backup old backup then dump current DB
#mv $rootMoodleBackupFolder/moodle-database-$backupDate.sql.gz $rootMoodleBackupFolder/moodle-database-$backupDate-old.sql.gz

mysqldump -h localhost -u root --password=M3dL@mP -C -Q -e --create-options moodle > $rootMoodleBackupFolder/moodle-database.sql

# Tar moodle DB
#
echo " Creating final compressed (tar.gz) TAR file of Moodle Database..." >> $logfilePath/$logfile

tar -pczf $rootMoodleBackupFolder/moodleDB-$backupDate.tar.gz $rootMoodleBackupFolder/moodle-database.sql

# Backup moodledata
#
echo " Creating final compressed (tar.gz) TAR file of moodledata directory..." >> $logfilePath/$logfile
#mv $rootMoodleBackupFolder/moodledata-$backupDate.tar.gz $rootMoodleBackupFolder/moodledata-$backupDate-old.tar.gz
tar -pczf $rootMoodleBackupFolder/moodledata-$backupDate.tar.gz /var/moodledata

# Backup moodleroot
echo " Creating final compressed (tar.gz) TAR file of moodleroot directory..." >> $logfilePath/$logfile
#mv $rootMoodleBackupFolder/moodleroot-$backupDate.tar.gz $rootMoodleBackupFolder/moodleroot-$backupDate-old.tar.gz
tar -pczf $rootMoodleBackupFolder/moodleroot-$backupDate.tar.gz /var/www/moodle/moodle

####################################    Silverstripe Backup   #############################################
#

echo " Dumping silverstripe database, using ..." >> $logfilePath/$logfile
echo " user:root database:SS_mysite host:localhost " >> $logfilePath/$logfile

# Check if Silverstripe directory in rootBackupFolder exists. If not, create it
rootSilverStripeBackupFolder="$rootBackupFolder/silverstripe"

if [ ! -d $rootSilverStripeBackupFolder ]; then
        mkdir -p $rootSilverStripeBackupFolder
fi

# Dump Silverstripe DB

mysqldump -h localhost -u root --password=M3dL@mP SS_mysite > $rootSilverStripeBackupFolder/ss-database.sql

# Tar Silverstripe DB
#

echo " Creating final compressed (tar.gz) TAR file of Silverstripe Database..." >> $logfilePath/$logfile
tar -pczf $rootSilverStripeBackupFolder/ss-DB-$backupDate.tar.gz $rootSilverStripeBackupBackupFolder/ss-database.sql

# Tar Silverstrip root dir
echo " Creating final compressed (tar.gz) TAR file of silverstripe root directory..." >> $logfilePath/$logfile

tar -pczf $rootSilverStripeBackupFolder/ss-root-$backupDate.tar.gz /var/www/ss

########################################  MediaWiki      ###################################################
#
#

echo " Dumping mediawiki database, using ..." >> $logfilePath/$logfile
echo " user:root database:my_wiki host:localhost " >> $logfilePath/$logfile

# Check if Mediawiki directory in rootBackupFolder exists. If not, create it
rootMediawikiBackupFolder="$rootBackupFolder/mediawiki"

if [ ! -d $rootMediawikiBackupFolder ]; then
        mkdir -p $rootMediawikiBackupFolder
fi

# Dump MediaWiki DB 

mysqldump -h localhost -u root --password=M3dL@mP --default-character-set=utf8 my_wiki > $rootMediawikiBackupFolder/mw-database.sql

# Tar Mediwiki DB
#

echo " Creating final compressed (tar.gz) TAR file of Mediawiki Database..." >> $logfilePath/$logfile

tar -pczf $rootMediawikiBackupFolder/mw-DB-$backupDate.tar.gz $rootMediawikiBackupFolder/mw-database.sql

# tar Mediawiki root dir

echo " Creating final compressed (tar.gz) TAR file of mediawiki root directory..." >> $logfilePath/$logfile

tar -pczf $rootMediawikiBackupFolder/mw-root-$backupDate.tar.gz /var/www/wiki



####################################    Clean Up   ###########################################################
# Remove old backups

find $rootMoodleBackupFolder/*.tar.gz -mtime +7 | xargs -I{} rm {} 

find $rootSilverStripeBackupFolder/*.tar.gz -mtime +7 | xargs -I{} rm {}


# Exit banner
#
endtime=`date +%Y-%m-%d-%H%M%S`
echo "Backup completed $endtime" >> $logfilePath/$logfile


