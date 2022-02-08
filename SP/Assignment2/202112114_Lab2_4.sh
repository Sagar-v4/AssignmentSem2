#!/bin/bash

getunique() {
	sort $1 | uniq >> unique.txt

}

getunique $@
