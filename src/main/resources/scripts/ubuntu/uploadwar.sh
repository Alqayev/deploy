#!/bin/bash
sudopass_=`echo $1`
modulname=`echo $2`
modulpass_=`echo $3`
deployhost_=`echo $4`
hostport_=`echo $5`
modulwar_=`echo $6`
catalinahome_=`echo $7`
warpath_=`echo $8`
foldername_=`echo $9`
bin_=$catalinahome_/bin
underscore_=_
isexistfile_=0
modulrenamewar_=$modulwar_$underscore_
webapps_=$catalinahome_/webapps

echo $sudopass_ | sudo -S apt-get install sshpass; # autoinsert parol ucun
echo $modulpass_ >file
sshpass -f file ssh -p $hostport_ $modulname@$deployhost_ "echo 2>&1" && checkedconnection_=0 || checkedconnection_=1

if [ "$checkedconnection_" = "0" ]
then
sshpass -f file ssh -p $hostport_ $modulname@$deployhost_ "./autokill.sh "$modulname
sshpass -f file ssh -p $hostport_ $modulname@$deployhost_ "rm -rf "$webapps_"/"$foldername_
sshpass -f file ssh -q -p $hostport_ $modulname@$deployhost_ [[ -f $webapps_"/"$modulwar_ ]] && isexistfile_=1 || isexistfile_=0;
if [ "$isexistfile_" = "1" ]
then
echo $isexistfile_
sshpass -f file ssh -p $hostport_ $modulname@$deployhost_ "mv "$webapps_"/"$modulwar_" "$webapps_"/"$modulrenamewar_
fi
sshpass -f file scp -P $hostport_ $warpath_ $modulname@$deployhost_:$webapps_
sshpass -f file ssh -p $hostport_ $modulname@$deployhost_ "./start.sh"
else
echo 'Connection refused'
fi
rm file