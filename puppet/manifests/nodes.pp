node 'generic.ict.op.ac.nz'{
# Packages
	package { 'mc': ensure => present }
	package { 'vim': ensure => present }
	package { 'tree': ensure => present }
	package { 'ruby-shadow': ensure => present }

# Modules	
	include bootstrap
	include apt
	include hosts_file
	include sudo
	include users
	include apache
	include mysql
	include fail2ban
	include sshd
	include phpmyadmin
	include moodle
	include ntp
	include ss
	include mediawiki
#	include openssl
}

# Backup server 
#node 'backup'{
#	package { 'ruby-shadow': ensure => present }
#	include users
#       package { 'vim': ensure => present }
#       package { 'tree': ensure => present }
#	include sudo
#       include hosts_file
#	include nagios-nrpe-server
#	include mc
#	include nix_bacula_client
#}
#
