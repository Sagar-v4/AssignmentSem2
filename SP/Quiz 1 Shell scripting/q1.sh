sum=0

for i in $@
do sum=$((sum+i));
done

echo "Sum of all command line integer arguments: $sum"
