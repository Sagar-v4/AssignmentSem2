$(CC) = gcc
$home = /home/sv4/Quiz_3/
$PROJ = proj/
$SRC = /home/sv4/Quiz_3/proj/src
$OBJ = proj/src/obj
$INC = proj/include
$LIB = proj/lib
$BIN = proj/bin

makeProj:
	mkdir proj/ proj/src proj/src/obj proj/include proj/lib proj//bin
	cp *.c proj/src/
	cp *.h proj/include/
	cp Makefile proj/src/
	cd proj/src/obj/
	gcc -c /home/sv4/Quiz_3/proj/src/main.c -o /home/sv4/Quiz_3/proj/src/obj/main.o
	gcc -c /home/sv4/Quiz_3/proj/src/lib_digit.c -o /home/sv4/Quiz_3/proj/src/obj/lib_digit.o
	gcc -c /home/sv4/Quiz_3/proj/src/lib_prime.c -o /home/sv4/Quiz_3/proj/src/obj/lib_prime.o
	cd ../../..
	cd proj/lib/
	ar rcs /home/sv4/Quiz_3/proj/lib/lib_static_q1.a /home/sv4/Quiz_3/proj/src/obj/lib_prime.o /home/sv4/Quiz_3/proj/src/obj/lib_digit.o
	gcc /home/sv4/Quiz_3/proj/src/obj/lib_prime.o /home/sv4/Quiz_3/proj/src/obj/lib_digit.o -shared -o /home/sv4/Quiz_3/proj/lib/lib_dynamic_q1.so
	cd ../..
	cd proj/bin/
	gcc -o /home/sv4/Quiz_3/proj/bin/main_static_q2 /home/sv4/Quiz_3/proj/src/obj/main.o -L. lib_static_q1.a
	gcc -o /home/sv4/Quiz_3/proj/bin/main_dynamic_q3 /home/sv4/Quiz_3/proj/src/obj/main.o -L. lib_dynamic_q1.so
	export LD_LIBRARY_PATH=:/home/sv4/Quiz_3/proj/bin

Q2:
	proj/bin/main_static_q2

Q3:
	proj/bin/main_dynamic_q3
