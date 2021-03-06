# Petit projet de jeu textuel en java tres simple :

Vous incarnez un aventurier à la recherche de tresors.  
Des villageois du coin vous ont parler d'un chateau hanté qui abriterait d'apres les legendes de nombreux tresors.  
Les legendes disent aussi que les tresors sont fermements gardées par des monstres plus effrayants les uns que les autres !  
Saurez vous relever le défi ?  
Apres quelques recherches vous avez finalement réussi a localiser le chateau en question.  
Avant de partir, vous devez vous equiper : votre sac peut contenir jusqu'à 3 objets.  

IL existe  types d'objets :

- les armes : elles infligent des dégats aux monstres a chaque utilisation, mais elles s'usent à chaque utilisation et ne peuvent donc être utiliser qu'un nombre limité de fois
- les potions : elles vous rendent des points de vie (usage unique)
- les gemmes : elles donnent des degats bonus a vos armes (usage unique)

Une fois dans le chateau, vous pourrez vous deplacer dans un réseau de pieces, dans chaque pièce se situera un monstre ainsi qu'un coffre.  
Visitez toutes les pièces du chateau, battez tout les monstres et ouvrez tout les coffres du chateau pour gagner la partie.

### Points fonctionnels :
- Gestion d'inventaire
- Gestion de points de vie
- Gestion d'un score
- Gestion d'un menu textuel et gestion de commandes textuels
- Mise en place d'un réseau de pièce (avec un système de sorties laissant à l'utilisateur le choix du chemin qu'il souhaite prendre)

### Points techniques :
- Sauvegarde par serialisation et reprise d'une partie commencée par déserialisation
- Génération d'objets avec des attributs aléatoires parmis une liste
- Généralisation d'objets

### Diagramme de classe :
![Diagramme de classe](https://raw.githubusercontent.com/clementor5/chateauHante/main/img/diagramme.png)

--------------------------------------------------------------------------------------------------------------------------------------------

#TODO ajouter une fonctionnalité de créer son propre niveau :
- choisir des monstres dans la liste ou un créer un nouveau (et l'ajouter à la liste)
- choisir des armes dans la liste ou en créer un nouveau (et l'ajouter à la liste)
- choisir  le nombre de pièce du chateau
- choisir les points de vie du joueur
- choisir les points de vie moyen des monstres
- choisir les points de degats moyen des monstres
- choisir l'attaque moyenne des armes
- choiir les points de vie moyen que rendent les potions
- choisir les points de dégat moyen que donnent les gemmes
- choisir le nombre d'armes que le joueur choisi au début de la partie
