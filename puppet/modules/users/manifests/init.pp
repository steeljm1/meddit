class users {
                user { 'steeljm1':
                        ensure                  => 'present',
                        managehome              => true,
                        password                => '$6$hpf9cHH8$PgXqxmqqetlqkV/AmrEWiXDiRMzmaSwkGZJz6.aBeEAFVnzeK4.RybizSPZInBWWeAYNSB8uNKTBLRpcujnHg/',
                        shell                   => '/bin/bash',
                }
             
                
}

