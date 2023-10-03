CC = gcc
OBJ = main.o projet.o
CFLAGS = -lSDL2 -lSDL2_image -lSDL2_ttf

.silent: projet run clean $(OBJ)

projet: $(OBJ)
		$(CC) $(OBJ) -o projet $(CFLAGS)
main.o:
		$(CC) -c main.c $(CFLAGS)
projet.o: projet.c projet.h
		$(CC) -c projet.c $(CFLAGS)

run:
		./projet

clean:
	 	rm -f projet*.o
		echo "fichiers effacés"
