class ad_users {
  
        file {'C:/puppet/users.csv':
                ensure   => file,
                source   => 'puppet:///modules/ad_users/users.csv',
               
        }
        
        
        file {'C:/puppet/AddAdUser.ps1':
                ensure   => file,
                source   => 'puppet:///modules/ad_users/AddAdUser.ps1',
                
        }
        
        
        
  

}
