echo "Enter number to find its factorial: "
read num

n=$num
fact=1

fact() {
	while [ $n -gt 1 ]
	do
		fact=$((fact * n))
		n=$((n - 1))
	done
	
	echo "Factorial of $num is: $fact"
}

fact
