##  This is the root directory of Puppet for the use of creating a Generic Learning Framework System.  ##

### Install Puppet

1. apt-get install puppetmaster puppet
2. Copy the entire generic puppet directory to /etc/ - overwrite existing directory 
3. Note - Before you run puppet, ensure to place moodleroot-2014-10-25-154024.tar.gz in /etc/puppet/modules/moodle/files/

## Edit the following files to suit the new system:

### /etc/puppet/puppet.conf

 [main]
 
 certname=generic.ict.op.ac.nz   # Server domain name
 
 server=generic.ict.op.ac.nz   # Server domain name

--------------------------------------------------------- 
 
### /etc/puppet/manifests/nodes.pp
 
 node 'generic.ict.op.ac.nz'{........}
 
--------------------------------------------------------- 
  
### /etc/puppet/modules/hosts_file/templates/debhosts.erb
 
 127.0.0.1 localhost <%= hostname %>

 127.0.0.1 generic.ict.op.ac.nz generic   # Server domain name

 Production static ip
 
 10.25.1.160 generic.ict.op.ac.nz generic   # Server domain name and IP

----------------------------------------------------------

### /etc/puppet/modules/sudo/files/sudoers

#####User privilege specification - Define Sudoers

root ALL=(ALL:ALL) ALL

Otago MeddIT Sys Admin

steeljm1 ALL=(ALL:ALL) ALL

Default System Admin Account

puppetmaster ALL=(ALL:ALL) ALL

----------------------------------------------------------

## Run the puppet agent to setup the system

As root execute the following:

puppet agent --server=generic.ict.op.ac.nz --no-daemonize --verbose

------------------------------------------------------------

### All modules contained within are licensed under the following:

Visual Midwifery by Otago MeddIT is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.

You are free to:
Share — copy and redistribute the material in any medium or format.

The licensor cannot revoke these freedoms as long as you follow the license terms.

Under the following terms:
* Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.

* NonCommercial — You may not use the material for commercial purposes.

* NoDerivatives — If you remix, transform, or build upon the material, you may not distribute the modified material. 
