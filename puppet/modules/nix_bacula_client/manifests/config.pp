class nix_bacula_client::config {
  
    # create restore directories  
    file { "/home/bacula":
          ensure => "directory",
          owner  => "bacula",
          group  => "bacula",
          mode   => 0750,
          require   => Class["nix_bacula_client::install"],
          notify   => Class["nix_bacula_client::service"],
    }

    file { "/home/bacula/restores":
          ensure => "directory",
          owner  => "bacula",
          group  => "bacula",
          mode   => 0750,
          require   => Class["nix_bacula_client::install"],
          notify   => Class["nix_bacula_client::service"],
    }


}
