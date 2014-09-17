# Class: ntp
#
# This module manages ntp
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class ntp {

           include ntp::install, ntp::config, ntp::service



}
