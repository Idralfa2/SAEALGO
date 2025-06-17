package iut.simplicite.etu26;



public class Recherche {
    public static int chercheMot(String botteDeFoin, String aiguille) {
        // Vérification des erreurs
        if (botteDeFoin == null || aiguille == null || aiguille.isEmpty()) {
            return -1;
        }

        String[] lignes = botteDeFoin.split("\n");
        int nbLignes = lignes.length;
        if (nbLignes == 0) return 0;

        int nbColonnes = lignes[0].length();

        for (String ligne : lignes) {
            if (ligne.length() != nbColonnes) return -1; // Lignes de tailles différentes
        }

        // Création de la matrice
        char[][] grille = new char[nbLignes][nbColonnes];
        for (int i = 0; i < nbLignes; i++) {
            grille[i] = lignes[i].toCharArray();
        }

        int count = 0;
        int len = aiguille.length();
        char[] mot = aiguille.toCharArray();
        char[] motInverse = new char[len];
        for (int i = 0; i < len; i++) {
            motInverse[i] = mot[len - 1 - i];
        }

        // On considère seulement 4 directions (droite, bas, diag bas-droite, diag bas-gauche)
        int[][] directions = {
            {0, 1},   // droite
            {1, 0},   // bas
            {1, 1},   // diagonale bas-droite
            {1, -1}   // diagonale bas-gauche
        };

        for (int x = 0; x < nbLignes; x++) {
            for (int y = 0; y < nbColonnes; y++) {
                for (int[] dir : directions) {
                    int dx = dir[0];
                    int dy = dir[1];

                    // Vérifier si mot est trouvé dans cette direction
                    boolean matchMot = true;
                    for (int k = 0; k < len; k++) {
                        int nx = x + dx * k;
                        int ny = y + dy * k;
                        if (nx < 0 || ny < 0 || nx >= nbLignes || ny >= nbColonnes || grille[nx][ny] != mot[k]) {
                            matchMot = false;
                            break;
                        }
                    }

                    // Vérifier si motInverse est trouvé dans cette direction
                    boolean matchInverse = true;
                    for (int k = 0; k < len; k++) {
                        int nx = x + dx * k;
                        int ny = y + dy * k;
                        if (nx < 0 || ny < 0 || nx >= nbLignes || ny >= nbColonnes || grille[nx][ny] != motInverse[k]) {
                            matchInverse = false;
                            break;
                        }
                    }

                    if (matchMot || matchInverse) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
