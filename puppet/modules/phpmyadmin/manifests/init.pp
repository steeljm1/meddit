# Class: phpmyadmin
#
# This module manages phpmyadmin
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class phpmyadmin {

        include phpmyadmin::install, phpmyadmin::config, phpmyadmin::service

}
