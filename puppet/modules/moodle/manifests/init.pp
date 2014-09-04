# Class: moodle
#
# This module manages moodle
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class moodle {
            include moodle::install, moodle::config, moodle::service
}
