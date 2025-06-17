package iut.sae.algo.efficacite.etu46;

public class Recherche {
    
    /**
     * Recherche un mot dans toutes les directions possibles avec un minimum de boucles.
     * 
     * @param botteDeFoin La grille de texte dans laquelle rechercher
     * @param aiguille Le mot à rechercher dans la grille
     * @return Le nombre total d'occurrences trouvées, ou -1 en cas d'erreur
     */
    public static int chercheMot(String botteDeFoin, String aiguille){
        // Validation des paramètres d'entrée
        if(botteDeFoin == null || aiguille == null || aiguille.isEmpty()){
            return -1; // Retourner -1 en cas de paramètres invalides
        }
        
        // Si la grille est vide, retourner 0
        if(botteDeFoin.isEmpty()){
            return 0;
        }

        // Division de la chaîne en lignes
        String[] lignes = botteDeFoin.split("\n");
        int hauteur = lignes.length; // Nombre de lignes dans la grille
        
        // Cas spécial : grille avec une seule ligne vide
        if(hauteur == 1 && lignes[0].isEmpty()){
            return 0;
        }
        
        int largeur = lignes[0].length(); // Largeur basée sur la première ligne
        
        // Vérification que toutes les lignes ont la même longueur
        for(String ligne : lignes){
            if (ligne.length() != largeur){ // Si une ligne a une longueur différente
                return -1; // Grille irrégulière, retourner -1
            }
        }

        // Création de la grille 2D de caractères
        char[][] grille = new char[hauteur][largeur];
        for(int i = 0; i < hauteur; i++){
            grille[i] = lignes[i].toCharArray(); // Conversion de chaque ligne en tableau de caractères
        }

        // Cas spécial pour un caractère unique
        if(aiguille.length() == 1){
            return compterCaractereUnique(grille, aiguille.charAt(0), hauteur, largeur);
        }

        // Recherche générale pour les mots de plusieurs caractères
        return rechercheUnifiee(grille, aiguille, hauteur, largeur);
    }

    /**
     * Compte les occurrences d'un caractère unique dans la grille.
     */
    private static int compterCaractereUnique(char[][] grille, char c, int hauteur, int largeur){
        int compteur = 0; // Compteur d'occurrences
        // Parcours de toute la grille
        for(int i = 0; i < hauteur; i++){
            for(int j = 0; j < largeur; j++){
                if(grille[i][j] == c) { // Si le caractère correspond
                    compteur++; // Incrémenter le compteur
                }
            }
        }
        return compteur; // Retourner le nombre total d'occurrences
    }

    // Tableau des 8 directions possibles de recherche
    private static final int[][] DIRECTIONS = {
        {0, 1},   // Horizontal droite
        {0, -1},  // Horizontal gauche
        {1, 0},   // Vertical bas
        {-1, 0},  // Vertical haut
        {1, 1},   // Diagonale principale bas-droite
        {-1, -1}, // Diagonale principale haut-gauche
        {1, -1},  // Diagonale secondaire bas-gauche
        {-1, 1}   // Diagonale secondaire haut-droite
    };

    /**
     * Recherche unifiée utilisant une seule boucle pour toutes les directions.
     */
    private static int rechercheUnifiee(char[][] grille, String aiguille, int hauteur, int largeur){
        int compteurTotal = 0; // Compteur total d'occurrences trouvées
        boolean estPalindrome = estPalindrome(aiguille); // Vérifier si le mot est palindrome
        
        // Boucle principale pour parcourir toutes les positions
        for(int ligne = 0; ligne < hauteur; ligne++){
            for(int colonne = 0; colonne < largeur; colonne++) {
                // Test de chaque direction possible
                for(int dirIndex = 0; dirIndex < DIRECTIONS.length; dirIndex++){
                    int[] direction = DIRECTIONS[dirIndex]; // Direction courante
                    
                    // Optimisation palindrome : éviter les directions opposées
                    if(estPalindrome && dirIndex % 2 == 1){
                        continue; // Passer à la direction suivante
                    }
                    
                    // Rechercher le mot dans cette direction
                    if(chercherDansDirection(grille, aiguille, ligne, colonne, 
                                            direction[0], direction[1], hauteur, largeur)){
                        compteurTotal++; // Incrémenter si le mot est trouvé
                    }
                }
            }
        }
        
        return compteurTotal; // Retourner le nombre total d'occurrences
    }

    /**
     * Vérifie si un mot est un palindrome.
     */
    private static boolean estPalindrome(String mot){
        int longueur = mot.length(); // Longueur du mot
        // Comparer les caractères symétriques depuis les extrémités
        for(int i = 0; i < longueur / 2; i++){
            if (mot.charAt(i) != mot.charAt(longueur - 1 - i)){ // Si les caractères diffèrent
                return false; // Le mot n'est pas un palindrome
            }
        }
        return true; // Le mot est un palindrome
    }

    /**
     * Recherche un mot dans une direction spécifique à partir d'une position donnée.
     * Cette méthode remplace toutes les méthodes de recherche spécialisées.
     */
    private static boolean chercherDansDirection(char[][] grille, String mot, int ligneStart, 
                                               int colonneStart, int deltaLigne, int deltaColonne, 
                                               int hauteur, int largeur){
        int longueurMot = mot.length(); // Longueur du mot à chercher
        
        // Calcul des positions finales dans cette direction
        int ligneFin = ligneStart + (longueurMot - 1) * deltaLigne;
        int colonneFin = colonneStart + (longueurMot - 1) * deltaColonne;
        
        // Vérification que le mot reste dans les limites de la grille
        if(ligneFin < 0 || ligneFin >= hauteur || colonneFin < 0 || colonneFin >= largeur){
            return false; // Le mot sort des limites
        }
        
        // Vérification caractère par caractère le long de la direction
        for(int k = 0; k < longueurMot; k++){
            int ligneActuelle = ligneStart + k * deltaLigne; // Position ligne du k-ème caractère
            int colonneActuelle = colonneStart + k * deltaColonne; // Position colonne du k-ème caractère
            
            // Comparaison avec le caractère attendu du mot
            if(grille[ligneActuelle][colonneActuelle] != mot.charAt(k)){
                return false; // Le caractère ne correspond pas
            }
        }
        
        return true; // Tous les caractères correspondent, le mot est trouvé
    }
}
