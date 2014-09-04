class nix_bacula_client::service{
  
  service { "bacula-fd" :
    ensure    => running,
    hasstatus   => true,
    hasrestart  => true,
    enable    => true,
    require   => Class["nix_bacula_client::config"],
  }
  
}
