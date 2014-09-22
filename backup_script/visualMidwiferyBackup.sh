#!/bin/bash

############################################################################################################
#                                                                                                          #
#                                   Visual Midwifery - Backup Script                                       #
#                                                                                                          #
# This script will backup the following:                                                                   #
#       *moodle database                                                                                   #
#       *moodledata directory                                                                              #
#       *moodleroot directory                                                                              #
#       *Silverstripe database                                                                             #
#       *Silverstripe assets directory                                                                     #
#       *Silverstripe root directory                                                                       #
#                                                                                                          #
# Add this script to cron if you want this to run on a schedule.                                           #
#                                                                                                          #
#                                                                                                          #
############################################################################################################
######################################      Variables      #################################################
#
# Root folder for backups
rootBackupFolder=/var/backups

# Get the backup date
backupDate=`date +%Y-%m-%d-%H%M%S`

# Number of backups to keep. Make this one more than the number you want to keep!
backupsToKeep=3
######################################    Moodle Backup    #################################################
#
# Check if moodle directory in rootBackupFolder exists. If not, create it
rootMoodleBackupFolder="$rootBackupFolder/moodle"

if [ ! -d $rootMoodleBackupFolder ]; then
        mkdir -p $rootMoodleBackupFolder
fi

# Backup old backup then dump current DB
#mv $rootMoodleBackupFolder/moodle-database-$backupDate.sql.gz $rootMoodleBackupFolder/moodle-database-$backupDate-old.sql.gz

mysqldump -h localhost -u root --password=M3dL@mP -C -Q -e --create-options moodle > $rootMoodleBackupFolder/moodle-database.sql

# Zip moodle DB
tar -pczf $rootMoodleBackupFolder/moodleDB-$backupDate.tar.gz $rootMoodleBackupFolder/moodle-database.sql

# Backup moodledata
#mv $rootMoodleBackupFolder/moodledata-$backupDate.tar.gz $rootMoodleBackupFolder/moodledata-$backupDate-old.tar.gz
tar -pczf $rootMoodleBackupFolder/moodledata-$backupDate.tar.gz /var/moodledata

# Backup moodleroot
#mv $rootMoodleBackupFolder/moodleroot-$backupDate.tar.gz $rootMoodleBackupFolder/moodleroot-$backupDate-old.tar.gz
tar -pczf $rootMoodleBackupFolder/moodleroot-$backupDate.tar.gz /var/www/moodle/moodle

####################################    Silverstripe Backup   #############################################
#
# Check if Silverstripe directory in rootBackupFolder exists. If not, create it
rootSilverStripeBackupFolder="$rootBackupFolder/silverstripe"

if [ ! -d $rootSilverStripeBackupFolder ]; then
        mkdir -p $rootSilverStripeBackupFolder
fi

# Dump Silverstripe DB

mysqldump -h localhost -u root --password=M3dL@mP SS_mysite > $rootSilverStripeBackupFolder/ss-database.sql

# Tar Silverstripe DB
tar -pczf $rootSilverStripeBackupFolder/ss-DB-$backupDate.tar.gz $rootSilverStripeBackupBackupFolder/ss-database.sql

####################################    Clean Up   ###########################################################
# Remove old backups

find $rootMoodleBackupFolder/*.tar.gz -mtime +7 | xargs -I{} rm {}

find $rootSilverStripeBackupFolder/*.tar.gz -mtime +7 | xargs -I{} rm {}

