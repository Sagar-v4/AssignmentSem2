#!/bin/bash

if ! [ -d $1 ]
then
	echo "$1 -> Directory Doesn't exists..."

else
	files=`ls -R $1|find $1 -type d`
	
	for file in $files
	do
		count=0
		if [ -d $file ]
		then
			count=`ls $file|find $file -type f|wc -l`
			echo "$file -> Files count: $count"
		fi
	done
fi
