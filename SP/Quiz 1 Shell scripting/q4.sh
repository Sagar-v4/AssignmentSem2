FILE1=/etc/passwd
FILE2=/usr/local/lib
FILE3=/Quiz_2

if test -f "$FILE1";
then
	echo "$FILE1 -> its exists as file."
elif test -d "$FILE1";
then
	echo "$FILE1 -> its exist as directory."
else
        echo "$FILE1 -> does not exists."
fi

if test -f "$FILE2";
then
	echo "$FILE2 -> its exists as file."
elif test -d "$FILE2";
then
	echo "$FILE2 -> its exist as directory."
else
        echo "$FILE2 -> does not exists."
fi

if test -f "$FILE3";
then
	echo "$FILE3 -> its exists as file."
elif test -d "$FILE3";
then
	echo "$FILE3 -> its exist as directory."
else
        echo "$FILE3 -> does not exists."
fi
