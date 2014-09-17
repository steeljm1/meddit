class ntp::config {
  
            file { "/etc/ntp.conf":
                    ensure    => present,
                    owner     => root,
                    group     => root,
                    mode      => 644,
                    #backup    => false,
                    source => "puppet:///modules/ntp/ntp.conf",
                    require   => Class["ntp::install"],
            }
           
  
}