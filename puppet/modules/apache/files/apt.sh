#!/bin/bash

# cd into /root
cd /root

# download key
wget http://download.opensuse.org/repositories/isv:ownCloud:community/Debian_7.0/Release.key

# add key to apt
apt-key add - < Release.key  

# add source to sources list
echo 'deb http://download.opensuse.org/repositories/isv:/ownCloud:/community/Debian_7.0/ /' >> /etc/apt/sources.list.d/owncloud.list 

# run apt-get update
apt-get update