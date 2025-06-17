package iut.sae.algo.efficacite.etu76;

public class Recherche {
    
    //Convertie une chaine en matrice pour pouvoir maniopuler plus simplement
    public static char[][] ConversionMatrice(String texte) { 
        if (texte == null || texte.isEmpty()) { 
            return new char[0][0]; 
        }
        
        // Premier parcours : compte lignes et trouve largeur max
        int nbLigne = 1; 
        int largeurMax = 0;
        int largeurCourant = 0;
        
        for (int i = 0; i < texte.length(); i++) {
            char lettre = texte.charAt(i);
            if (lettre== '\n') {
                nbLigne++;
                largeurMax = Math.max(largeurMax, largeurCourant);
                largeurCourant = 0;
            } else {
                largeurCourant++;
            }
        }
        largeurMax = Math.max(largeurMax, largeurCourant);
        
        char[][] matrice = new char[nbLigne][largeurMax];
        
        int ligCourante = 0;
        int colCourante = 0;
        
        for (int i = 0;i < texte.length();i++) {
            char lettre = texte.charAt(i);
            if (lettre == '\n') {
                // Rempli avec espace
                while (colCourante < largeurMax) {
                    matrice[ligCourante][colCourante] = ' ';
                    colCourante++;
                }
                ligCourante++;
                colCourante = 0;
            } else {
                matrice[ligCourante][colCourante] = lettre;
                colCourante++;
            }
        }
        
        // Rempli la derniere ligne
        while (colCourante < largeurMax) {
            matrice[ligCourante][colCourante] = ' ';
            colCourante++;
        }
        return matrice; 
    }
    
    //Verifie si toute les lignes ont la meme longueur
    private static boolean estRegulier(String texte) {
        if (texte == null || texte.isEmpty()) {
            return true;
        }

        int longueurRef = -1;
        int largeurCourante = 0;
        boolean premiereFois = true;
        
        for (int i = 0;i < texte.length(); i++) {
            char lettre = texte.charAt(i);
            if (lettre== '\n') {
                if (premiereFois) {
                    longueurRef = largeurCourante;
                    premiereFois = false;
                } else {
                    if (largeurCourante != longueurRef) {
                        return false;
                    }
                }
                largeurCourante= 0;
            } else {
                largeurCourante++;
            }
        }
        if (!premiereFois && largeurCourante != longueurRef) {
            return false;
        }
        return true;
    }
    
    //Reherche d'un mot dans une matrice
    private static int chercherMotMatrice(char[][] matrice, String mot) {
        if (matrice == null || mot == null || mot.isEmpty()) {
            return 0;
        }
        
        int hauteur = matrice.length;

        if (hauteur == 0) return 0;

        int largeur = matrice[0].length;
        int tailleMotif = mot.length();

        if (tailleMotif > Math.max(hauteur, largeur)) {
            return 0;
        }
        char premiereLettre = mot.charAt(0);
        int total = 0;
        
        for (int ligne = 0;ligne < hauteur; ligne++) {
            for (int colonne= 0; colonne < largeur; colonne++) {
                if (matrice[ligne][colonne]== premiereLettre) {
                    if (PeutTenir(ligne, colonne, hauteur, largeur, tailleMotif)) {
                        total = total+ CompteurPosition(matrice, mot,ligne,colonne);
                    }
                }
            }
        }
        return total;
    }
    
    //Verifie si le mot peut tenir quelque part
    private static boolean PeutTenir(int lig, int col, int hauteur, int largeur, int taille) {
        return (col + taille <= largeur)|| (lig + taille <= hauteur) ||(col - taille + 1>= 0) ||(lig - taille + 1>= 0) ||(lig + taille <= hauteur &&col + taille <= largeur) || (lig+ taille <= hauteur && col - taille+ 1>=0) || (lig -taille + 1 >= 0 &&col +taille <= largeur) || (lig -taille + 1 >= 0 &&col - taille + 1 >=0);        
    }
    
    //Compte depuis une position dans toute les directions
    private static int CompteurPosition(char[][] matrice, String mot, int ligneDepart, int colDepart) {
        if (mot.length() == 1) {
            return 1; 
        }
        
        int hauteur = matrice.length;
        int largeur = matrice[0].length;
        int longueurMotif = mot.length();
        int comptage = 0;
        
        // 8 direction possible
        int[][] sens = {
            {0, 1}, {1, 1}, {1, 0}, {1, -1},
            {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}
        };
        
        for (int[] direction : sens) {
            int deltaLig = direction[0];
            int deltaCol = direction[1];
            
            boolean peutAller = true;
            int ligFin = ligneDepart + (longueurMotif - 1) * deltaLig;
            int colFin = colDepart + (longueurMotif - 1) * deltaCol;
            if (ligFin < 0 || ligFin >= hauteur || colFin < 0 || colFin >= largeur) {
                peutAller = false;
            }
            if (peutAller) {
                int ligSuiv = ligneDepart + deltaLig;
                int colSuiv = colDepart + deltaCol;
                
                if (matrice[ligSuiv][colSuiv] == mot.charAt(1)) {
                    if (chercherDansDirection(matrice, mot, ligneDepart, colDepart, deltaLig, deltaCol)) {
                        comptage++;
                    }
                }
            }
        }
        
        return comptage;
    }
    
    //Cherche le mot complet dans une direction
    private static boolean chercherDansDirection(char[][] matrice, String mot, int ligDebut, int colDebut, int pasLig, int pasCol) {
        // Commence a 2 car on a deja verifie 0 et 1
        for (int indice = 2; indice < mot.length(); indice++) {
            int ligActuel = ligDebut +indice * pasLig;
            int colActuel = colDebut + indice * pasCol;
            if (matrice[ligActuel][colActuel] != mot.charAt(indice)) {
                return false;
            }
        }
        return true;
    }

    //Recherche de l'aiguille
    public static int chercheMot(String botteDeFoin, String aiguille) {
        if (botteDeFoin == null || aiguille == null || aiguille.isEmpty()) {
            return -1;
        }
        if (botteDeFoin.isEmpty()) {
            return 0;
        }
        // Verifie si regulier
        if (!estRegulier(botteDeFoin)) {
            return -1;
        }
        char[][] matrice = ConversionMatrice(botteDeFoin);
        return chercherMotMatrice(matrice, aiguille);
    }
}
