package iut.simplicite.etu37;



/**
 * Classe utilitaire pour rechercher un mot dans une grille de caractères.
 */
public class Recherche {

    /**
     * Cherche le nombre d'occurrences d'un mot (aiguille) dans une grille (botteDeFoin).
     * Les recherches se font horizontalement, verticalement et en diagonale selon la taille du mot.
     * Prend en compte les palindromes (mot lu à l'endroit et à l'envers).
     *
     * @param botteDeFoin Grille de caractères, chaque ligne séparée par un retour à la ligne.
     * @param aiguille Mot à rechercher dans la grille.
     * @return Nombre d'occurrences du mot dans la grille, ou -1 si la grille est invalide.
     */
    public static int chercheMot(String botteDeFoin, String aiguille) {
        if (botteDeFoin == null || aiguille == null || aiguille.isEmpty()) return -1;

        String[] lignes = botteDeFoin.split("\n");
        int lignesNb = lignes.length;
        int colonnesNb = lignes[0].length();

        // Vérifie que toutes les lignes ont la même taille
        for (String ligne : lignes) {
            if (ligne.length() != colonnesNb) return -1;
        }

        // Cas spécial : aiguille d'un seul caractère → on ne cherche que ligne par ligne
        if (aiguille.length() == 1) {
            int compteur = 0;
            for (String ligne : lignes) {
                for (int j = 0; j < colonnesNb; j++) {
                    if (ligne.charAt(j) == aiguille.charAt(0)) compteur++;
                }
            }
            return compteur;
        }

        boolean palindrome = estUnPalindrome(aiguille);
        int total = 0;

        // Parcours chaque position de départ possible
        for (int i = 0; i < lignesNb; i++) {
            for (int j = 0; j < colonnesNb; j++) {

                // Toujours horizontal
                total += chercherDirection(lignes, aiguille, i, j, 0, 1, palindrome);

                // Toujours vertical
                total += chercherDirection(lignes, aiguille, i, j, 1, 0, palindrome);

                if (aiguille.length() == 2) {
                    // Uniquement diagonale descendante droite pour les aiguilles de 2 lettres
                    total += chercherDirection(lignes, aiguille, i, j, 1, 1, palindrome);
                } else if (aiguille.length() > 2) {
                    // Diagonale descendante droite
                    total += chercherDirection(lignes, aiguille, i, j, 1, 1, palindrome);
                    // Diagonale descendante gauche
                    total += chercherDirection(lignes, aiguille, i, j, 1, -1, palindrome);
                }
            }
        }

        return total;
    }

    /**
     * Cherche si le mot ou son inverse est présent dans une direction donnée à partir d'une position.
     *
     * @param lignes Grille de caractères.
     * @param aiguille Mot à rechercher.
     * @param x Ligne de départ.
     * @param y Colonne de départ.
     * @param dx Pas de déplacement en ligne.
     * @param dy Pas de déplacement en colonne.
     * @param palindrome Indique si le mot est un palindrome.
     * @return 1 si le mot est trouvé, 2 si le mot et son inverse sont trouvés (et non palindrome), 0 sinon.
     */
    private static int chercherDirection(String[] lignes, String aiguille, int x, int y, int dx, int dy, boolean palindrome) {
        int len = aiguille.length();
        int maxX = lignes.length;
        int maxY = lignes[0].length();

        int finX = x + (len - 1) * dx;
        int finY = y + (len - 1) * dy;

        // Si on dépasse les limites de la grille
        if (finX < 0 || finX >= maxX || finY < 0 || finY >= maxY) return 0;

        boolean normal = true;
        boolean inverse = !palindrome;

        // Comparaison caractère par caractère
        for (int i = 0; i < len; i++) {
            char c = lignes[x + i * dx].charAt(y + i * dy);
            if (c != aiguille.charAt(i)) normal = false;
            if (inverse && c != aiguille.charAt(len - 1 - i)) inverse = false;
        }

        int res = 0;
        if (normal) res++;
        if (!palindrome && inverse) res++;
        return res;
    }

    /**
     * Vérifie si le mot est un palindrome.
     *
     * @param aiguille Mot à tester.
     * @return true si le mot est un palindrome, false sinon.
     */
    private static boolean estUnPalindrome(String aiguille) {
        for (int i = 0; i < aiguille.length() / 2; i++) {
            if (aiguille.charAt(i) != aiguille.charAt(aiguille.length() - 1 - i)) return false;
        }
        return true;
    }
}
