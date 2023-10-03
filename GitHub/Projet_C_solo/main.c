#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <SDL2/SDL_ttf.h>
#include "projet.h"

#define nbr 40
#define nb_ligne 100
#define nb 232
#define HAUTEUR 320
#define LARGEUR 640

AVL *arbre=NULL;

int main(int argc, char *argv[]){

  SDL_Window *window = NULL;
  SDL_Renderer *renderer = NULL;
  SDL_Texture *texture = NULL;
  int statut = EXIT_FAILURE;

  SDL_bool done = SDL_FALSE;

  SDL_StartTextInput();

  if(0 != SDL_Init(SDL_INIT_VIDEO))
  {
      fprintf(stderr, "Erreur SDL_Init : %s", SDL_GetError());
      goto Quit;
  }
  if(0 != TTF_Init())
  {
      fprintf(stderr, "Erreur SDL_Init : %s", TTF_GetError());
      goto Quit;
  }
  window = SDL_CreateWindow("Projet", SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,1920,960 , SDL_WINDOW_SHOWN);
  if(NULL == window)
  {
      fprintf(stderr, "Erreur SDL_CreateWindow : %s", SDL_GetError());
      goto Quit;
  }
  renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);
  if(NULL == renderer)
  {
      fprintf(stderr, "Erreur SDL_CreateRenderer : %s", SDL_GetError());
      goto Quit;
  }

  SDL_Rect drapeau = {0,0, 1920, 960};
  SDL_Rect image = {620, 70, 640, 427};
  SDL_Rect bulle = { 460,600 , 970, 210};
  SDL_Rect texte = { 630,670 ,610, 70};
  SDL_Rect boutonR = { 120,280 ,640, 320};
  SDL_Rect boutonJ = { 1160,280 ,640, 320};
  SDL_Rect boutonC = { 120,20,640, 320};
  SDL_Rect boutonVe = { 1160,20 ,640, 320};
  SDL_Rect boutonO = { 1550,740 ,320, 160};
  SDL_Rect boutonVe1 = { 1550,580 ,320, 160};
  SDL_Rect boutonV = { 50,570 ,320, 160};
  SDL_Rect boutonC1 = { 50,750,320, 160};
  SDL_Rect boutonR1 = { 380,740 ,320, 160};
  SDL_Rect boutonG = { 780,440 ,320, 160};
  SDL_Rect boutonG1 = { 780,620 ,320, 160};
  SDL_Rect boutonG2 = { 770,770 ,320, 160};
  SDL_Rect boutonB = { 1300,660 ,160, 80};
  SDL_Rect boutonJ1 = { 1150,770,320, 160};
  SDL_Rect cate1 = {400,320 ,1100, 80};

  SDL_Event event;

  TTF_Font* Sans = TTF_OpenFont("GothamMedium.ttf", 100);
  if (Sans == NULL){
    fprintf(stderr, "Erreur SDL_Init : %s", TTF_GetError());
  }
  SDL_Color noir = {0, 0, 0};
  SDL_Surface* surfaceMessage;
  SDL_Texture* Message;
  char *text= (char*)malloc(nb_ligne*sizeof(char));

  char tab[nb][nbr];
  char *x=(char*)malloc(nbr*sizeof(char));
  char c[nbr], cate[nbr], m[nbr], score[30];
  char y[60]="Traduction mauvaise, la reponse est: ";
  char scr[30]="Score : ";

  int i=0, j=0, l=0, k=0, s=0, ca=0;

  char motf1[nbr];
  char mota1[nbr];
  char motp1[nbr];
  char img1[nbr];
  char cat1[nbr];

  FILE* fic ;
   fic = fopen( "fichier.csv", "r") ;
  if (fic==NULL) {
    printf("Ouverture fichier impossible !");
    exit(0);
  }

  char *ligne= (char*)malloc(nb_ligne*sizeof(char));
  char *ptr_chaine =(char*)malloc(nb_ligne*sizeof(char));
  char *motf=(char*)malloc(nbr*sizeof(char));
  char *mota=(char*)malloc(nbr*sizeof(char));
  char *motp=(char*)malloc(nbr*sizeof(char));
  char *img=(char*)malloc(nbr*sizeof(char));
  char *cat=(char*)malloc(nbr*sizeof(char));
  int num_ligne = 1 ;

  //permet de lire le fichier csv et d'inserer les information d'une ligne dans un noueud d'un AVL
  while ( fgets( ligne, nb_ligne, fic) != NULL )
  {
    AVL *elem = (AVL *)malloc(sizeof(AVL));
    ptr_chaine = strtok (ligne, ",");
    motf= ptr_chaine;
    strcpy(tab[i],motf);

    ptr_chaine = strtok (NULL, ",");
    mota= ptr_chaine;

    ptr_chaine = strtok (NULL, ",");
    motp= ptr_chaine;

    ptr_chaine = strtok (NULL, ",");
    img= ptr_chaine;

    ptr_chaine = strtok (NULL, "\n");
    cat = ptr_chaine;

    num_ligne++ ;
    i++;

    inserer(motf, mota, motp, img, cat);

  }
  //preOrder(arbre);

  //affiche le menu a l'ouverture de la fenetre
  afficherD(renderer, texture, drapeau);
  afficherBouton(renderer, texture, boutonR, "image/bouton_rouge.png");
  afficherBouton(renderer, texture, boutonJ, "image/bouton_jaune.png");
  afficherMessage(renderer, Message, surfaceMessage,140,405 ,590, 60, "Mode 1 joueur");
  afficherMessage(renderer, Message, surfaceMessage,1190,405 ,590, 60, "Mode 2 joueurs");
  afficherBulle(renderer, texture, "image/titre.png" , 0,-50 ,1850, 1000);


  SDL_RenderPresent(renderer);

  while (!done) {
    if (SDL_PollEvent(&event)) {
      switch (event.type) {
        case SDL_QUIT:
          //permet de quitter le jeu
          done = SDL_TRUE;
          break;

        case SDL_KEYDOWN:

          //permet de vérifier si la traduction en anglais est correct pour le mode aléatoire
          if (event.key.keysym.sym==SDLK_RETURN){
            l=verif(mota1, text);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherI(renderer, texture, image, img1);
            afficherBouton(renderer, texture, boutonO, "image/bouton_orange.png");
            afficherMessage(renderer, Message, surfaceMessage,1570,780 ,280, 80, "image suivante");
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            SDL_RenderPresent(renderer);
            if(l == 1){
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "la traduction est bonne");
              s=s+1;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            else{
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, strcat(y,motp1));
              strcpy(y,"traduction est mauvaise,la reponse est : ");
              s=0;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            strcpy(text,"");
          }

          //permet de vérifier si la traduction en anglais est correct pour le mode 2 joueurs
          if (event.key.keysym.sym==SDLK_TAB){
            l=verif(mota1, text);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherI(renderer, texture, image, img1);
            afficherBouton(renderer, texture, boutonVe1, "image/bouton_vert.png");
            afficherMessage(renderer, Message, surfaceMessage,1570,620 ,280, 80, "image suivante");
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            SDL_RenderPresent(renderer);

            if(l == 1){
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "la traduction est bonne");
              s=s+1;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            else{
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, strcat(y,motp1));
              strcpy(y,"traduction est mauvaise,la reponse est : ");
              s=0;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            strcpy(text,"");
          }

          //permet de vérifier si la traduction en anglais est correct pour le mode catégorie
          if (event.key.keysym.sym==SDLK_RALT){
            l=verif(mota1, text);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherI(renderer, texture, image, img1);
            afficherBouton(renderer, texture, boutonJ1, "image/bouton_jaune.png");
            afficherMessage(renderer, Message, surfaceMessage,1170,810 ,280, 80, "image suivante");
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            SDL_RenderPresent(renderer);
            if(l == 1){
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "la traduction est bonne");
              s=s+1;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            else{
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, strcat(y,motp1));
              strcpy(y,"traduction est mauvaise,la reponse est : ");
              s=0;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            strcpy(text,"");
          }

          //permet de vérifier si la traduction en portugais est correct pour le mode aléatoire
          if (event.key.keysym.sym==SDLK_RSHIFT){
            j=verif(motp1, text);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherI(renderer, texture, image, img1);
            afficherBouton(renderer, texture, boutonO, "image/bouton_orange.png");
            afficherMessage(renderer, Message, surfaceMessage,1570,780 ,280, 80, "image suivante");
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            SDL_RenderPresent(renderer);
            if(j == 1){
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "la traduction est bonne");
              s=s+1;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            else{
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, strcat(y,motp1));
              strcpy(y,"traduction est mauvaise,la reponse est : ");
              s=0;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            strcpy(text,"");
          }

          //permet de vérifier si la traduction en portugais est correct pour le mode 2 joueurs
          if (event.key.keysym.sym==SDLK_LSHIFT){
            j=verif(motp1, text);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherI(renderer, texture, image, img1);
            afficherBouton(renderer, texture, boutonVe1, "image/bouton_vert.png");
            afficherMessage(renderer, Message, surfaceMessage,1570,620 ,280, 80, "image suivante");
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            SDL_RenderPresent(renderer);
            if(j == 1){
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "la traduction est bonne");
              s=s+1;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            else{
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, strcat(y,motp1));
              strcpy(y,"traduction est mauvaise,la reponse est : ");
              s=0;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            strcpy(text,"");
          }

          //permet de vérifier si la traduction en portugais est correct pour le mode catégorie
          if (event.key.keysym.sym==SDLK_LALT){
            j=verif(motp1, text);
            afficherM(renderer, texture,drapeau, bulle);
            afficherI(renderer, texture, image, img1);
            afficherBouton(renderer, texture, boutonJ1, "image/bouton_jaune.png");
            afficherMessage(renderer, Message, surfaceMessage,1170,810 ,280, 80, "image suivante");
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            SDL_RenderPresent(renderer);
            if(j == 1){
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "la traduction est bonne");
              s=s+1;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            else{
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, strcat(y,motp1));
              strcpy(y,"traduction est mauvaise,la reponse est : ");
              s=0;
              sprintf(score,"Score : %d", s);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              SDL_RenderPresent(renderer);
            }
            strcpy(text,"");
          }

          if (event.key.keysym.sym==SDLK_BACKSPACE){
            strcpy(text,"");
            afficherB(renderer, texture, bulle);
            SDL_RenderPresent(renderer);
          }
          break;

        case SDL_TEXTINPUT :

          strcat(text, event.text.text);
          afficherB(renderer, texture, bulle);
          afficherText(renderer, texture, surfaceMessage, texte, text);
          SDL_RenderPresent(renderer);
          break;

        case SDL_MOUSEBUTTONUP:
          //mode 1 joueur
          //affiche 2 bouton: mode aléatoire, mode catégorie
          if ( event.button.y > 280&& event.button.y <= 240+HAUTEUR  && event.button.x > 120  && event.button.x <= 120+LARGEUR ){
            afficherD(renderer, texture, drapeau);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBouton(renderer, texture, boutonC, "image/bouton_cyan.png");
            afficherBouton(renderer, texture, boutonVe, "image/bouton_vert.png");
            afficherMessage(renderer, Message, surfaceMessage, 160,130 ,580, 80, "Mode aleatoire");
            afficherMessage(renderer, Message, surfaceMessage, 1200,130 ,580, 80, "Mode categorie");
            SDL_RenderPresent(renderer);
          }

          //choix langue pour mode aleatoire
          if ( event.button.y > 20&& event.button.y <= 20+HAUTEUR  && event.button.x > 120  && event.button.x <= 120+LARGEUR ){
            afficherD(renderer, texture, drapeau);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBulle(renderer, texture, "image/bulleg1.png" , 40,180 ,760, 450);
            afficherBulle(renderer, texture, "image/bulled1.png" , 1120,150 ,740, 533);
            afficherBouton(renderer, texture, boutonG, "image/bouton_gris.png");
            afficherMessage(renderer, Message, surfaceMessage, 165,290 ,560, 60, "Pour jouer en Anglais,");
            afficherMessage(renderer, Message, surfaceMessage, 120,360 ,640, 60, "taper sur ENTREE pour verfier le mot");
            afficherMessage(renderer, Message, surfaceMessage, 1200,290 ,560, 60, "Pour jouer en Portugais,");
            afficherMessage(renderer, Message, surfaceMessage, 1160,360 ,640, 60, "taper sur SHIFT DROIT pour verfier le mot");
            afficherMessage(renderer, Message, surfaceMessage, 800,490 ,280, 70, "Jouer");
            SDL_RenderPresent(renderer);
          }

          //permet de choissir une catégorie
          if ( event.button.y > 20&& event.button.y <= 20+HAUTEUR  && event.button.x > 1160  && event.button.x <= 1160+LARGEUR ){
            strcpy(text,"");
            afficherM(renderer, texture, drapeau, bulle);
            afficherBulle(renderer, texture, "image/rectangle.jpg" ,310 ,160 ,1280, 400);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBouton(renderer, texture, boutonB, "image/bouton_bleu.png");
            afficherMessage(renderer, Message, surfaceMessage, 1320,680 ,120, 50, "Valider");
            SDL_Color blanc = {255, 255, 255};
            surfaceMessage = TTF_RenderText_Solid(Sans, "Il y a trois categorie : nature, animaux, objet ", blanc);
            Message = SDL_CreateTextureFromSurface(renderer, surfaceMessage);
            SDL_RenderCopy(renderer, Message, NULL, &cate1);
            afficherMessage(renderer, Message, surfaceMessage, 630,670 ,610, 70, "Entrer la categorie choisie");
            SDL_RenderPresent(renderer);
          }

          //choix langue catégorie
          if ( event.button.y > 660&& event.button.y <= 660+HAUTEUR/4  && event.button.x > 1300  && event.button.x <= 1300+LARGEUR/4 ){
            afficherD(renderer, texture, drapeau);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBulle(renderer, texture, "image/bulleg3.png" , 30,190 ,770, 500);
            afficherBulle(renderer, texture, "image/bulled3.png" , 1100,130 ,740, 533);
            afficherBouton(renderer, texture, boutonG2, "image/bouton_gris.png");
            afficherMessage(renderer, Message, surfaceMessage, 125,290 ,560, 60, "Pour jouer en Anglais,");
            afficherMessage(renderer, Message, surfaceMessage, 90,360 ,640, 60, "taper sur ALT DROITE pour verfier le mot");
            afficherMessage(renderer, Message, surfaceMessage, 1200,290 ,560, 60, "Pour jouer en Portugais,");
            afficherMessage(renderer, Message, surfaceMessage, 1160,360 ,640, 60, "taper sur ALT GAUCHE pour verfier le mot");
            afficherMessage(renderer, Message, surfaceMessage, 790,810 ,280, 80, "Jouer");
            SDL_RenderPresent(renderer);
          }

          //vérifie si la catégorie choisie est la meme qu'un mot choisie aléatoirement
          //si la catégorie choisie n'est pas valide on redemande de choisir une catégorie
          if ( event.button.y > 770&& event.button.y <= 770+HAUTEUR/2  && event.button.x > 770  && event.button.x <= 770+LARGEUR/2 ){
            strcpy(c, text);
            if (strcmp(c,"nature")==0 || strcmp(c,"animaux")==0 || strcmp(c,"objet")==0){
              strcpy(text,"");
              afficherM(renderer, texture, drapeau, bulle);
              afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
              afficherBouton(renderer, texture, boutonG2, "image/bouton_gris.png");
              while(ca !=1){
                x = recherche_tab_ale(tab);
                recherche_ale(arbre, x, mota1, motp1, img1, cat1);
                ca = recherche_cat( cat1, c);
              }
              if(ca==1){
                afficherM(renderer, texture,drapeau, bulle);
                afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
                afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
                afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
                afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
                afficherI(renderer, texture, image, img1);
                afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
                afficherBouton(renderer, texture, boutonJ1, "image/bouton_jaune.png");
                afficherMessage(renderer, Message, surfaceMessage, 1170,815 ,280, 80, "Image suivante");
                SDL_RenderPresent(renderer);
                ca=0;
                strcpy(cate, c);
              }
            }
            else {
              strcpy(text,"");
              afficherM(renderer, texture, drapeau, bulle);
              afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
              afficherBouton(renderer, texture, boutonB, "image/bouton_bleu.png");
              afficherMessage(renderer, Message, surfaceMessage, 400,320 ,1100, 80, "Il y a trois categorie : nature, animaux, objet ");
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "Re entrer une categorie");
              SDL_RenderPresent(renderer);
            }
          }

          //bouton "image suivante" pour le mode catégorie
          //affiche une autre image de la catégorie choisie
          if ( event.button.y > 770&& event.button.y <= 770+HAUTEUR/2  && event.button.x > 1150  && event.button.x <= 1150+LARGEUR/2 ){
            strcpy(text,"");
            afficherM(renderer, texture, drapeau, bulle);
            afficherBouton(renderer, texture, boutonG2, "image/bouton_gris.png");
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            while(ca !=1){
              x = recherche_tab_ale(tab);
              recherche_ale(arbre, x, mota1, motp1, img1, cat1);
              ca = recherche_cat( cat1, cate);
            }
            if(ca==1){
              afficherM(renderer, texture,drapeau, bulle);
              afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
              afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
              afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
              afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
              afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
              afficherI(renderer, texture, image, img1);
              afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
              afficherBouton(renderer, texture, boutonJ1, "image/bouton_jaune.png");
              afficherMessage(renderer, Message, surfaceMessage, 1170,815 ,280, 80, "Image suivante");
              SDL_RenderPresent(renderer);
              ca=0;
            }
          }
          //bouton jouer aleatoire
          //affiche une image choisie semi-aléatoirement
          if ( event.button.y > 440&& event.button.y <= 440+HAUTEUR/2  && event.button.x > 780  && event.button.x <= 780+LARGEUR/2 ){
            strcpy(text,"");


            x = recherche_tab_ale(tab);
            recherche_ale(arbre, x, mota1, motp1, img1, cat1);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            afficherI(renderer, texture, image, img1);
            afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, scr);
            afficherBouton(renderer, texture, boutonO, "image/bouton_orange.png");
            afficherMessage(renderer, Message, surfaceMessage,1570,780 ,280, 80, "image suivante");
            SDL_RenderPresent(renderer);
          }

          //bouton image suivante mode aleatoire
          //affiche une image choisie semi-aléatoirement
          if ( event.button.y > 740&& event.button.y <= 740+HAUTEUR/2  && event.button.x > 1550  && event.button.x <= 1550+LARGEUR/2 ){
            strcpy(text,"");
            x = recherche_tab_ale(tab);
            recherche_ale(arbre, x, mota1, motp1, img1, cat1);
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
            afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
            afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
            afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
            afficherI(renderer, texture, image, img1);
            afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
            afficherBouton(renderer, texture, boutonO, "image/bouton_orange.png");
            afficherMessage(renderer, Message, surfaceMessage,1570,780 ,280, 80, "image suivante");
            SDL_RenderPresent(renderer);
          }

          //choix langue mode 2 joueurs
          if ( event.button.y > 280&& event.button.y <= 280+HAUTEUR  && event.button.x > 1160  && event.button.x <= 1160+LARGEUR ){
            afficherD(renderer, texture, drapeau);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBulle(renderer, texture, "image/bulleg2.png" , 50,190 ,770, 500);
            afficherBulle(renderer, texture, "image/bulled2.png" , 1120,200 ,750, 433);
            afficherBouton(renderer, texture, boutonG1, "image/bouton_gris.png");
            afficherMessage(renderer, Message, surfaceMessage, 125,290 ,560, 60, "Pour jouer en Anglais,");
            afficherMessage(renderer, Message, surfaceMessage, 100,360 ,640, 60, "taper sur TAB pour verfier le mot");
            afficherMessage(renderer, Message, surfaceMessage, 1200,290 ,560, 60, "Pour jouer en Portugais,");
            afficherMessage(renderer, Message, surfaceMessage, 1160,360 ,640, 60, "taper sur SHIFT GAUCHE pour verfier le mot");
            afficherMessage(renderer, Message, surfaceMessage,800,660 ,280, 80, "Jouer");
            SDL_RenderPresent(renderer);
          }

          //demande a un joueur de saisir un mot
          if ( event.button.y > 620&& event.button.y <= 620+HAUTEUR/2  && event.button.x > 780  && event.button.x <= 780+LARGEUR/2 ){
            strcpy(text,"");
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBouton(renderer, texture, boutonR1, "image/bouton_rouge.png");
            afficherText(renderer, texture, surfaceMessage, texte, text);
            afficherMessage(renderer, Message, surfaceMessage,400,780 ,280, 80, "verifier mot");
            SDL_RenderPresent(renderer);
          }

          //verifie si le mot donné est présent dans le tableau
          if ( event.button.y > 740&& event.button.y <= 740+HAUTEUR/2  && event.button.x > 380  && event.button.x <= 380+LARGEUR/2 ){
            k=recherche_tab(tab, text);
            afficherD(renderer, texture, drapeau);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            if(k == 1){
              recherche_ale(arbre, text, mota1, motp1, img1, cat1);
              afficherI(renderer, texture, image, img1);
              afficherB(renderer, texture, bulle);
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "le mot est present");
              afficherBouton(renderer, texture, boutonV, "image/bouton_violet.png");
              afficherMessage(renderer, Message, surfaceMessage,70,620 ,280, 60, "aide Anglais");
              afficherBouton(renderer, texture, boutonC1, "image/bouton_cyan.png");
              afficherMessage(renderer, Message, surfaceMessage,70,800 ,280, 60, "aide Portugais");
              SDL_RenderPresent(renderer);
              strcpy(text,"");
              k=0;
            }
            else{
              strcpy(text,"");
              afficherB(renderer, texture, bulle);
              afficherMessage(renderer, Message, surfaceMessage,630,670 ,610, 70, "le mot n'est pas present");
              afficherBouton(renderer, texture, boutonR1, "image/bouton_rouge.png");
              afficherMessage(renderer, Message, surfaceMessage,400,780 ,280, 80, "verifier mot");
              SDL_RenderPresent(renderer);
              strcpy(text,"");
            }
          }

          //image suivante mode 2 joueurs
          //permet de demander un autre mot a un joueur
          if ( event.button.y > 580&& event.button.y <= 580+HAUTEUR/2  && event.button.x > 1550  && event.button.x <= 1550+LARGEUR/2 ){
            strcpy(text,"");
            afficherM(renderer, texture,drapeau, bulle);
            afficherBulle(renderer, texture, "image/croix.png" , 20,10 ,50, 70);
            afficherBouton(renderer, texture, boutonR1, "image/bouton_rouge.png");
            afficherText(renderer, texture, surfaceMessage, texte, text);
            afficherMessage(renderer, Message, surfaceMessage,380,780 ,320, 80, "verifier mot");
            afficherMessage(renderer, Message, surfaceMessage,80,80 ,350, 60, score);
            SDL_RenderPresent(renderer);
          }

          //bouton aide anglais
          //permet d'afficher la première lettre de la traduction rechercher
          if ( event.button.y > 570&& event.button.y <= 570+HAUTEUR/2  && event.button.x > 50  && event.button.x <= 50+LARGEUR/2 ){
            strcpy(text,"");
            char z[30];
            z[0]= mota1[0];
            strcat(text,z);
            afficherB(renderer, texture, bulle);
            afficherText(renderer, texture, surfaceMessage, texte, text);
            SDL_RenderPresent(renderer);
          }

          //bouton aide anglais
          //permet d'afficher la première lettre de la traduction rechercher
          if ( event.button.y > 750&& event.button.y <= 750+HAUTEUR/2  && event.button.x > 50  && event.button.x <= 50+LARGEUR/2 ){
            strcpy(text,"");
            char w[30];
            w[0]= motp1[0];
            strcat(text,w);
            afficherB(renderer, texture, bulle);
            afficherText(renderer, texture, surfaceMessage, texte, text);
            SDL_RenderPresent(renderer);
          }

          //bouton croix
          //permet de revenir au menu
          if ( event.button.y > 10&& event.button.y <= 10+70  && event.button.x > 20  && event.button.x <= 20+50 ){
            afficherD(renderer, texture, drapeau);
            afficherBouton(renderer, texture, boutonR, "image/bouton_rouge.png");
            afficherBouton(renderer, texture, boutonJ, "image/bouton_jaune.png");
            afficherMessage(renderer, Message, surfaceMessage,140,400 ,590, 60, "Mode 1 joueur");
            afficherMessage(renderer, Message, surfaceMessage,1190,400 ,590, 60, "Mode 2 joueurs");
            afficherBulle(renderer, texture, "image/titre.png" , 0,-50 ,1850, 1000);
            SDL_RenderPresent(renderer);
          }
          break;
      }
    }
  }


  statut = EXIT_SUCCESS;
  SDL_FreeSurface(surfaceMessage);
  SDL_DestroyTexture(Message);
  SDL_DestroyWindow(window);
  TTF_CloseFont(Sans);
  TTF_Quit();
  Quit:
    if(NULL != texture)
      SDL_DestroyTexture(texture);
    if(NULL != renderer)
      SDL_DestroyRenderer(renderer);
    if(NULL != window)
      SDL_DestroyWindow(window);
    SDL_Quit();
    return statut;
  free(motf);
  free(mota);
  free(motp);
  free(img);
  free(cat);
  free(ptr_chaine);
  free(ligne);
  free(text);
  free(x);
  fclose(fic);
}
