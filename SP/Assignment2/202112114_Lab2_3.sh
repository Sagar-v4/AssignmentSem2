#!/bin/bash

if ! [ -d $1 ]
then
	echo "$1 -> Directory Doesn't exists..."
else
	cd $1

	rm -d * 2>/dev/null
	
	echo "All empty directory deleted inside -> $1 path..."
fi
