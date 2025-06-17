package iut.sae.algo.sobriete.etu24;

public class Recherche {

    /*
     * Cette méthode vérifie si le mot que l'on manipule est un palindrome ou non.
     */
    private static boolean estPalindrome(String mot){
            int longueur = mot.length();
            for(int i = 0; i < longueur /2; i++){
                if(mot.charAt(i)!= mot.charAt(longueur -1 -i)){
                    return false;
                }
            }
            return true;
        }

    /*
     * La méthode chercheMot, sert à chercher un mot dans toute les directions indiqué, les voicis :
     * Horizontal de Gauche vers Droite
     * Horizontale de Droite vers Gauche
     * Verticale de Haut en Bas 
     * Verticale de Bas en Haut 
     * Diagonale de Haut Gauche vers Bas Droite
     * Diagonale de Bas Droite vers Haut Gauche 
     * Diagonale de Haut Droite vers Bas Gauche
     * Diagonale de Bas Gauche vers Haut Droite
     * 
     * @param botteDeFoin  est une grille de texte qui représente des blocs de ligne séparée par des retours à la ligne  
     * @param aiguille   est le mot à rechercher dans botteDeFoin
     * 
     * @return on retourne le nombre de fois que le mot à été trouvé dans toutes les directions dans la grille 
     *         ou bine on return -1 si les paramètres sont pas bon ou bien si la grille est irrégulière 
     */
    public static int chercheMot(String botteDeFoin, String aiguille) {
    
        if(botteDeFoin == null || aiguille == null || aiguille.isEmpty()){
            return -1;
        }

        if(botteDeFoin.isEmpty()){
            return 0;
        }
        
        String[] ligne = botteDeFoin.split("\n");
        int nbLignes = ligne.length;
        int nbColonnes = ligne[0].length();

        for(int i = 1; i < nbLignes; i++){
            if(ligne[i].length() != nbColonnes){
                return -1;
            }
        }

        int compteur = 0;
        int longMot = aiguille.length();

        // Cas à traiter pour un seul caractère 
        if(longMot == 1){
            char c = aiguille.charAt(0);
            for(int Lignes = 0; Lignes < nbLignes; Lignes++ ){
                for(int Colonnes = 0; Colonnes < nbColonnes; Colonnes++){
                    if(ligne[Lignes].charAt(Colonnes) == c){
                        compteur++;
                    }
                }
            }
            return compteur;
        }

        boolean palindrome = estPalindrome(aiguille);

        // Direction affecté pour les tests
        /*
         * Ici j'utilise que 4 directions car cela est plus simple, 
         * c'est moins redondant, 
         * et je trouve que c'est assez claire
         */
        int[][] directions = {
            {0, 1},   // Direction = Droite
            {1, 0},   // Direction = Bas
            {1, 1},   // Direction = Diagonale Principale
            {1, -1}   // Direction = Diagonale Secondaire
        };

        for(int ligneDir = 0; ligneDir < nbLignes; ligneDir++){
            for(int colonneDir = 0; colonneDir < nbColonnes; colonneDir++){
                for(int i = 0; i < directions.length; i++){
                    int [] d = directions[i];
                    compteur += chercherDansDirection(ligne, aiguille, ligneDir, colonneDir, d[0], d[1]);
                }

                /*
                 * Cette boucle à pour but de vérifier les directions INVERSES, 
                 * donc celle que je n'es pas mis dans int[][] directions un peu plus haut 
                 */
                if(!palindrome){
                    for(int i = 0; i < directions.length; i++){
                    int [] d = directions[i];
                    compteur += chercherDansDirection(ligne, aiguille, ligneDir, colonneDir, -d[0], -d[1]);
                }
                }
            }
        }
        return compteur;

    }

    private static int chercherDansDirection(String[] lignes, String aiguille, int ligneD, int colonneD, int deltaLigne, int deltaColonne){
        int nbLignes = lignes.length;
        int nbColonnes = lignes[0].length();
        int longMot = aiguille.length();

        int ligneF = ligneD +(longMot -1)*deltaLigne;
        int colonneF = colonneD +(longMot -1)*deltaColonne;

        if(ligneF < 0 || ligneF >= nbLignes || colonneF < 0 || colonneF >= nbColonnes){
            return 0;
        }

        for(int i = 0; i < longMot; i++){
            int l = ligneD + i * deltaLigne;
            int c = colonneD + i * deltaColonne;
            if(lignes[l].charAt(c)!= aiguille.charAt(i)){
                return 0;
            }
        }
        return 1;

    }
}
