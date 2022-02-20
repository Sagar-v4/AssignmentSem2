$(CC) = gcc
$home = /home/sv4/Quiz_3
$PROJ = proj
$SRC = proj/src
$OBJ = proj/scr/obj
$INC = proj/include
$LIB = proj/lib
$BIN = proj/bin

make:
	mkdir proj/ proj/src proj/src/obj proj/include proj/lib proj//bin
	cp *.c $SRC
	cp *.h $INC
	cp Makefile $SRC
	cd $OBJ
	$(CC) -c ../*.c -fpic
	cd ../../..
	cd $LIB
	ar rcs lib_static_q1.a ../src/obj/lib_prime.o ../src/objlib_digit.o
	$(CC) ../src/obj/lib_prime.o ../src/objlib_digit.o -shared -o lib_dynamic_q1.so
	cd ../..
	cd $BIN
	$(CC) -o main_static_q2 ../src/obj/main.o -L. lib_static_q1.a
	$(CC) -o main_dynamic_q3 ../src/obj/main.o -L. lib_dynamic_q1.so
	export LD_LIBRARY_PATH=:$home/$BIN

Q2:
	cd $BIN
	./main_static_q2

Q3:
	cd $BIN
	./main_dynamic_q3
