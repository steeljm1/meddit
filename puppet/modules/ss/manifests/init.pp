# Class: ss
#
# This module manages ss
#
# Parameters: none
#
# Actions:
#
# Requires: see Modulefile
#
# Sample Usage:
#
class ss {
  
  include ss::install, ss::config, ss::service

}
