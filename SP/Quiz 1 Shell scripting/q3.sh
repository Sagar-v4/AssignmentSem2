echo "Enter day(Week starts from monday): "
read n

case $n in
	
	1) echo "Monday" ;;
	2) echo "Tuesday" ;;
	3) echo "Wednesday" ;;
	4) echo "Thusrday" ;;
	5) echo "Friday" ;;
	6) echo "Saturday" ;;
	7) echo "Sunday" ;;
	*) echo "Invalid day.. day should between 1 to 7" ;;
	
esac
