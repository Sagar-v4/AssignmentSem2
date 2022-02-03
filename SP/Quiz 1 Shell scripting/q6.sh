
#!bin/bash

dir=${PWD}
cd $1
ls *.out >> 1.txt

i=0
name[0]=''

while read -r line
do 
	./$line
	name[$i]=$?
	i=`expr $i + 1`
done < 1.txt
rm 1.txt
echo ${name[*]}
cd $dir
