#!/bin/bash

sum=0
count=0

for i in "$@"
do
	if [ $count -lt 9 ]
	then
		sum=`expr $sum + $i`
		count=`expr $count + 1`
	else
		break
	fi
done
	
echo "Sum (up to 9) command line integer arguments[$count]: $sum"
