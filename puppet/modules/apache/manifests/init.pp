# Class: apache
#
# This module manages apache
#
# Parameters: none
#
# Actions: Install apache2 server
#
# Requires: see Modulefile
#
# Sample Usage:
#
class apache {

      include apache::install, apache::config, apache::service

}
