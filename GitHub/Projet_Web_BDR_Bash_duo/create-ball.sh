#!/bin/bash

# Fonction récursive pour parcourir les fichiers
function parcours() {
  for file in "$1"/*; do
    if [ -f "$file" ]; then
      # Traitement des fichiers
      if [ ! -f "$file" ]; then
        echo "Le fichier $file n'existe pas."
        exit 1
      fi
      echo "outdir=$(dirname "$file")" >> my-ball.sh
      echo "if [ ! -d $(dirname "$file") ]; then
        mkdir -p $(dirname "$file") 
      fi" >> my-ball.sh
      echo "uudecode -o \"$file\" <<'EOF'" >> my-ball.sh
      uuencode -m "$file" "$file" >> my-ball.sh
      echo "EOF" >> my-ball.sh
    elif [ -d "$file" ]; then
      # Création du dossier s'il n'existe pas
      if [ ! -d "$file" ]; then
        mkdir "$file"
      fi
      # Appel récursif pour parcourir les sous-répertoires
      parcours "$file"
    fi
  done
}

# Création du fichier d'archive
echo "#!/bin/bash" > my-ball.sh
# Création du dossier racine s'il n'existe pas
if [ ! -d "$1" ]; then
  mkdir "$1"
fi
parcours "$1"
archive_name="my-ball-sae.tar.gz"
tar -czvf "$archive_name" *
chmod +x my-ball.sh
echo "Archive $archive_name créée avec succès."

