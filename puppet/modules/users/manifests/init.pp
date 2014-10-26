class users {
                user { 'steeljm1':
                        ensure                  => 'present',
                        managehome              => true,
                        password                => '$6$hpf9cHH8$PgXqxmqqetlqkV/AmrEWiXDiRMzmaSwkGZJz6.aBeEAFVnzeK4.RybizSPZInBWWeAYNSB8uNKTBLRpcujnHg/',
                        shell                   => '/bin/bash',
                }
                
                # Default Admin account
                user { 'puppetmaster':
                        ensure                  => 'present',
                        managehome              => true,
                        password                => '$6$598DeluS9KZfE$iUuSxRrCTQ3Ld1it66978wYSf1oPmmhNrxlAGTsipgR/mefBPkwj7zSfSZ9IZL5wIJpbdHXXSKqr2TQjyd72n.',
                        shell                   => '/bin/bash',
                }
                
             
                
}

