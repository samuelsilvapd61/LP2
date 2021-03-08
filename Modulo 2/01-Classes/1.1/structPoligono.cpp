#include <stdio.h>

typedef struct {
	int x, y;
	int h, w;
	int numLados;
}Poligono;

void print (Poligono * p){
	printf("Tamanho (%d, %d) / Posicao (%d, %d) / Numero de lados (%d)\n", p->x, p->y, p->h, p->w, p->numLados);
}

int main (){
	Poligono p1 = {2, 2, 4, 4, 5};
	print(&p1);
	
	return 0;
}
