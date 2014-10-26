##  This is the root directory for the Puppet installation of a generic system.  ##

Edit the following files to suit the new system:

/puppet/puppet.conf

|[main]
 certname=generic.ict.op.ac.nz   # Server domain name
 server=generic.ict.op.ac.nz   # Server domain name|
|---|
 
 
 
 /puppet/manifests/nodes.pp
 
 node 'generic.ict.op.ac.nz'{........}
 

 /puppet/modules/hosts_file/templates/debhosts.erb










All modules contained within are licensed under the following:

Visual Midwifery by Otago MeddIT is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License.

You are free to:
Share — copy and redistribute the material in any medium or format.

The licensor cannot revoke these freedoms as long as you follow the license terms.

Under the following terms:
* Attribution — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.

* NonCommercial — You may not use the material for commercial purposes.

* NoDerivatives — If you remix, transform, or build upon the material, you may not distribute the modified material. 
