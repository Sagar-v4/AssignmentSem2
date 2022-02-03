cd /var/log
sudo

for i in ls
do
	grep warning * >> /home/sv4/Quiz_1/warnings.log
done

cat /home/sv4/Quiz_1/warnings.log
