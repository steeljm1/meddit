params (
[Parameter(Mandatory=$true)][string]$fileName
)
# Import names
$userList = Import-CSV $fileName

# User password
$password = ConvertTo-SecureString 'P@ssw0rd' -AsPlainText -Force

# Domain
$domain='sqrawler.com'

# Container
$container = 'Users'

# New-ADUser
FOREACH ($user in $userList) {
$Username=$user.FirstName+"."+$user.LastName.TrimEnd()
$Username = $Username.ToLower()
$UPN = $Username+"@"+$Domain
$Name =$user.Firstname+" "+$user.Lastname

New-ADUser -SamAccountName $Username -GivenName $user.Firstname -Surname $user.Lastname -DisplayName $Name -AccountPassword $password -UserPrincipalName $UPN -Name $Name -Organization $container
Enable-ADAccount -Identity $Username
}