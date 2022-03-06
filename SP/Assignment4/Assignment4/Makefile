
CC=gcc
CFLAGS=-I .

LIBS=-lm

DEPS = 202112114_Lab4_1_myheader.h

LIBOBJ = 202112114_Lab4_1_fact.o 202112114_Lab4_1_pow.o
LIBSRC = 202112114_Lab4_1_fact.c 202112114_Lab4_1_pow.c
APPOBJ = 202112114_Lab4_1_main.o

#Q1
202112114_Lab4_1_pow.o: 202112114_Lab4_1_pow.c
	$(CC) -c -o $@ $< $(CFLAGS)

202112114_Lab4_1_fact.o: 202112114_Lab4_1_fact.c
	$(CC) -c -o $@ $< $(CFLAGS)

$(APPOBJ): 202112114_Lab4_1_main.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

202112114_Lab4_1_main_q4_q1.out: $(APPOBJ) $(LIBOBJ)
	$(CC) -o $@ $^ $(CFLAGS)


#static files
Q4_lib_static.a:$(LIBOBJ)
	ar rs -o $@ $^

202112114_Lab4_1_main_q4_static.out: $(APPOBJ) Q4_lib_static.a
	$(CC) -o $@ $^ $(CFLAGS) $(LIBS) Q4_lib_static.a

202112114_Lab4_1_main_q4_static_withl.out: $(APPOBJ) Q4_lib_static.a
	$(CC) -o $@ $^ $(CFLAGS) -L -lstatic

#dynamic files
202112114_Lab4_1_pow.o: 202112114_Lab4_1_pow.c
	$(CC) -fPIC -c -o $@ $< $(CFLAGS)

202112114_Lab4_1_fact.o: 202112114_Lab4_1_fact.c
	$(CC) -fPIC -c -o $@ $< $(CFLAGS)

Q4_lib_dynamic.so:$(LIBOBJ)
	$(CC) -shared -o $@ $^

$(APPOBJ): 202112114_Lab4_1_main.c $(DEPS)
	$(CC) -c -o $@ $< $(CFLAGS)

#Shared Library Path
	export LD_LIBRARY_PATH=.

202112114_Lab4_1_main_q4_dynamic.out: $(APPOBJ) Q4_lib_dynamic.so
	$(CC) -o $@ $^ $(CFLAGS) $(LIBS) Q4_lib_dynamic.so

202112114_Lab4_1_main_q4_dynamic_withl.out: $(APPOBJ) Q4_lib_dynamic.so
	$(CC) -o $@ $^ $(CFLAGS) -L -ldynamic

.PHONY: clean

clean: 
	rm -f *.o *~ core *~
	rm -f Q4_lib_static.a 202112114_Lab4_1_main_q4_static.out 202112114_Lab4_1_main_q4_static_withl.out
	rm -f Q4_lib_dynamic.so 202112114_Lab4_1_main_q4_dynamic.out 202112114_Lab4_1_main_q4_dynamic_withl.out
	rm -f 202112114_Lab4_1_main_q4_q1.out


.PHONY: all
all: Q4_lib_static.a 202112114_Lab4_1_main_q4_static.out 202112114_Lab4_1_main_q4_static_withl.out 202112114_Lab4_1_main_q4_dynamic.out 202112114_Lab4_1_main_q4_dynamic_withl.out 202112114_Lab4_1_main_q4_q1.out
