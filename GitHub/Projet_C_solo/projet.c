#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <SDL2/SDL_ttf.h>
#include "projet.h"
#define nbr 40
#define nb 232
AVL *arbre;

int max (int a, int b){
    if(a>b)return a;
    else return b;
}

int hauteur (AVL *A){
    if(A==NULL)
        return -1;
    else
        return max(hauteur (A->gauche),hauteur(A->droite))+1;
}

//permet d'afficher l'arbre
void preOrder(AVL *abr){

  if(abr != NULL){
    printf("francais = %s\n", abr->mot_f);
    printf("anglais = %s\n", abr->anglais);
    printf("portugais = %s\n", abr->portugais);
    printf("image = %s\n", abr->image);
    printf("catégorie = %s\n", abr->categorie);
    preOrder(abr->gauche);
    preOrder(abr->droite);
  }
}

//fait une rotation droite de l'arbre
AVL *ROTD (AVL *A){
    AVL *B;
    B = A->gauche;
    A->gauche = B->droite;
    B->droite = A;
    return B;
}

//fait une rotation gauche de l'arbre
AVL *ROTG (AVL *A){
    AVL *B;
    B = A->droite;
    A->droite = B->gauche;
    B->gauche = A;
    return B;
}


//fait une recherche semi-aléatoire dans le tableau de mot francais
char *recherche_tab_ale(char tab[nb][nbr]){
    srand(time(NULL));
    int n = rand()% 232 + 0;
    for (int i=0; i<=nb;i++){
        if(i == n){
            return tab[i];
        }
    }
}

//fonction qui recherche dans l'AVL la traduction, l'image et la catégorie d'un mot donné en parametre
void recherche_ale(AVL *abr, char *f, char *a, char *p, char *i, char *c){
     if (abr == NULL){
        printf("le mot n'est pas present");
    }
    else if (strcmp(f, abr->mot_f) ==0){
        strcpy(f,abr->mot_f);
        strcpy(a,abr->anglais);
        strcpy(p,abr->portugais);
        strcpy(i,abr->image);
        strcpy(c,abr->categorie);
        return;
    }
    else if (strcmp(f, abr->mot_f) <=0) {
        recherche_ale(abr->gauche, f, a, p, i, c);
        return;
    }
    else{
        recherche_ale(abr->droite, f, a, p, i, c);
        return;
    }
}

//permet d'inserer dans l'AVL un noeud comprenant le mot en francais, la traduction de ce mot en anglasi et en portugais, l'image et la catégorie qui correspond au mot
void inserer (char *f, char *a, char *p, char *i, char *c){
  char motf[nbr];
  char mota [nbr];
  char motp [nbr];
  char img [nbr];
  char cat [nbr];
    AVL *abr;
    AVL *ab = arbre;
    strcpy(motf, f);
    strcpy(mota, a);
    strcpy(motp, p);
    strcpy(img, i);
    strcpy(cat, c);
    AVL *elem = (AVL *)malloc(sizeof(AVL));
    strcpy(elem->mot_f, motf);
    strcpy(elem->anglais, mota);
    strcpy(elem->portugais, motp);
    strcpy(elem->image, img);
    strcpy(elem->categorie, cat);

    elem->gauche = NULL;
    elem->droite = NULL;

    if (arbre == NULL){
        arbre = elem;
    }
    else{
        abr= NULL;
        ab = arbre;
        while(1){
          abr = ab;
          if (strcmp(motf, ab->mot_f) <=0){
            ab = ab->gauche;
            if(ab==NULL){
              abr->gauche = elem;
              return ;
            }
          }
          else if (strcmp(motf, ab->mot_f) >=0){
            ab = ab->droite;
            if(ab==NULL){
              abr->droite = elem;
              return;
            }
          }
        }

    }

    if (hauteur(arbre->gauche)-hauteur(arbre->droite)>=2 && hauteur (arbre->droite)==1)
        ROTG(arbre);

    if (hauteur(arbre->gauche)-hauteur(arbre->droite)<=-2 && hauteur (arbre->gauche)==-1)
        ROTD(arbre);

    if (hauteur(arbre->gauche)-hauteur(arbre->droite)>=2 && hauteur (arbre->droite)==-1){
        arbre->droite= ROTD(arbre->droite);
        ROTG(arbre);
    }

    if (hauteur(arbre->gauche)-hauteur(arbre->droite)<=-2 && hauteur (arbre->gauche)==1){
        arbre->gauche= ROTG(arbre->gauche);
        ROTD(arbre);
    }
}

//vérifie si un mot est présent dans le tableau de mot francais
int recherche_tab(char tab[nb][nbr], char *m){
    for (int i=0; i<=nb;i++){
        if(strcmp(m, tab[i]) ==0){
            return 1;
        }
    }
}

//vérifie si deux catégorie sont identique
int recherche_cat( char *cat, char *c){
  if (strcmp(c, cat)==0){
    return 1;
  }
  else return 0;
}

//verifie si la traduction donner correspond au mot
int verif(char *mot, char *motu){
  if (strcmp(mot, motu) ==0){
    return 1;
  }
  else return 0;
}

//permet d'afficher l'image de fond
void afficherD(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect){
  texture = IMG_LoadTexture(renderer, "image/DrapeauxFred.png");
  SDL_RenderCopy(renderer, texture, NULL, &rect);
}

//permet d'afficher une image d'un mot
void afficherI(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect, char *x){
  texture = IMG_LoadTexture(renderer, x);
  SDL_RenderCopy(renderer, texture, NULL, &rect);
}

//permet d'afficher l'image de fond et la bulle de chat
void afficherM(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect, SDL_Rect rect1){
  texture = IMG_LoadTexture(renderer, "image/DrapeauxFred.png");
  SDL_RenderCopy(renderer, texture, NULL, &rect);
  texture = IMG_LoadTexture(renderer, "image/chatfred.png");
  SDL_RenderCopy(renderer, texture, NULL, &rect1);
}

//permet d'afficher la bulle de chat
void afficherB(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect){
  texture = IMG_LoadTexture(renderer, "image/chatfred.png");
  SDL_RenderCopy(renderer, texture, NULL, &rect);
}

////permet d'afficher un bouton
void afficherBouton(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect, char *x){
  texture = IMG_LoadTexture(renderer, x);
  SDL_RenderCopy(renderer, texture, NULL, &rect);
}

//permet d'afficher une image
void afficherBulle(SDL_Renderer *renderer, SDL_Texture *texture, char *m, int x, int y, int w, int h){
  SDL_Rect mot = {x, y, w, h};
  texture = IMG_LoadTexture(renderer, m);
  SDL_RenderCopy(renderer, texture, NULL, &mot);
}

//permet d'afficher un message
void afficherMessage(SDL_Renderer *renderer, SDL_Texture *Message, SDL_Surface* surfaceMessage, int x, int y, int w, int h, char *m){
  SDL_Color noir = {0, 0, 0};
  TTF_Font* Sans = TTF_OpenFont("GothamMedium.ttf", 100);
  SDL_Rect mot = {x, y, w, h};
  surfaceMessage = TTF_RenderText_Solid(Sans, m, noir);
  Message = SDL_CreateTextureFromSurface(renderer, surfaceMessage);
  SDL_RenderCopy(renderer, Message, NULL, &mot);
}

//permet d'afficher le texte
void afficherText(SDL_Renderer *renderer, SDL_Texture *Message, SDL_Surface* surfaceMessage, SDL_Rect mot, char *m){
  SDL_Color noir = {0, 0, 0};
  TTF_Font* Sans = TTF_OpenFont("GothamMedium.ttf", 100);
  surfaceMessage = TTF_RenderText_Solid(Sans, m, noir);
  Message = SDL_CreateTextureFromSurface(renderer, surfaceMessage);
  SDL_RenderCopy(renderer, Message, NULL, &mot);
}
