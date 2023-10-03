#define nbr 40
#define nb 232

typedef struct AVL{
    char mot_f[50];
    char anglais[50];
    char portugais[50];
    char image[50];
    char categorie[50];
    struct AVL *gauche;
    struct AVL *droite;
} AVL;

int max (int a, int b);

int hauteur (AVL *A);

void preOrder(AVL *abr);

AVL *ROTD (AVL *A);

AVL *ROTG (AVL *A);



char *recherche_tab_ale(char tab[nb][nbr]);

void recherche_ale(AVL *abr, char *f, char *a, char *p, char *i, char *c);

void inserer(char *f, char *a, char *p, char *i, char *c);

int recherche_tab(char tab[nb][nbr], char *m);

int recherche_cat( char *cat, char *c);

int verif(char *mot, char *motu);



void afficherD(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect);

void afficherI(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect, char *x);

void afficherM(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect, SDL_Rect rect1);

void afficherB(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect);

void afficherBouton(SDL_Renderer *renderer, SDL_Texture *texture, SDL_Rect rect, char *x);

void afficherBulle(SDL_Renderer *renderer, SDL_Texture *texture, char *m, int x, int y, int w, int h);

void afficherMessage(SDL_Renderer *renderer, SDL_Texture *Message, SDL_Surface* surfaceMessage, int x, int y, int w, int h, char *m);

void afficherText(SDL_Renderer *renderer, SDL_Texture *Message, SDL_Surface* surfaceMessage, SDL_Rect mot, char *m);
