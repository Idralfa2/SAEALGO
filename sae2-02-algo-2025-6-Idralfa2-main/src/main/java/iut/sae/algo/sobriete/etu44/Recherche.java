package iut.sae.algo.sobriete.etu44;

public class Recherche{
    public static int chercheMot(String botteDeFoin, String aiguille) {
        if (aiguille == null || aiguille.isEmpty() || botteDeFoin == null){
            return -1;
        }

        String[] lignes = botteDeFoin.split("\n");
        int nbLignes = lignes.length;
        int nbColonnes = lignes[0].length();
        //Vérification si la botte a des lignes de même longueur
        for (String ligne : lignes)
            if (ligne.length() != nbColonnes){
                return -1;
            }
                
        int compteur = 0;
        boolean estPal = estPalindrome(aiguille);

        if (aiguille.length() == 1){
            char car = aiguille.charAt(0);
            for (String ligne : lignes)
                for (char lettre : ligne.toCharArray())
                    if (lettre == car){
                        compteur++;
                    }
            return compteur;
        }

        String aiguilleEnvers = new StringBuilder(aiguille).reverse().toString();

        for (int x=0; x<nbLignes;x++){
            for (int y=0;y<nbColonnes;y++){
                // G-D
                if (peutLireSansSortir(x,y,0,1,aiguille.length(),nbLignes,nbColonnes)){
                    if (correspond(lignes,x,y,0,1,aiguille)){
                        compteur++;
                    }
                    if (!estPal && correspond(lignes,x,y,0,1,aiguilleEnvers)){
                        compteur++;
                    }
                }
                // H-B
                if (peutLireSansSortir(x,y,1,0,aiguille.length(),nbLignes,nbColonnes)){
                    if (correspond(lignes,x,y,1,0, aiguille)){
                        compteur++;
                    }
                    if (!estPal && correspond(lignes,x,y,1,0,aiguilleEnvers)){
                        compteur++;
                    }
                }
                // HG-BD
                if (peutLireSansSortir(x,y,1,1,aiguille.length(),nbLignes,nbColonnes)){
                    if (correspond(lignes,x,y,1,1,aiguille)){
                        compteur++;
                    }
                    if (!estPal && correspond(lignes,x,y,1,1,aiguilleEnvers)){
                        compteur++;
                    }
                }
                // HD-BG
                if (peutLireSansSortir(x,y,1,-1,aiguille.length(),nbLignes,nbColonnes)){
                    if (correspond(lignes,x,y,1,-1, aiguille)){
                        compteur++;
                    }
                    if (!estPal && correspond(lignes,x,y,1,-1,aiguilleEnvers)){
                        compteur++;
                    }
                }
            }
        }
        return compteur;
    }

    /**
     * Vérifie si on peut lire un mot sans sortir des limites de la grille à partir d'une position.
     *
     * @param x ligne de départ
     * @param y colonne de départ
     * @param dirx déplacement en ligne
     * @param diry déplacement en colonne
     * @param longueur longueur du mot à lire
     * @param lignes nombre de lignes de la grille
     * @param colonnes nombre de colonnes de la grille
     * @return true si le mot peut être lu sans sortir de la grille, false sinon
     */
    private static boolean peutLireSansSortir(int x, int y, int dirx, int diry, int longueur, int lignes, int colonnes){
        int endirx = x+dirx*(longueur-1);
        int endiry = y+diry*(longueur-1);
        return endirx>=0 && endirx<lignes && endiry>=0 && endiry<colonnes;
    }

    /**
     * Vérifie si un mot correspond à une séquence de lettres dans la grille,
     * à partir d'une position et dans une direction donnée.
     *
     * @param grille la grille de lettres
     * @param x ligne de départ
     * @param y colonne de départ
     * @param dirx déplacement en ligne
     * @param diry déplacement en colonne
     * @param mot le mot à vérifier
     * @return true si le mot correspond, false sinon
     */
    private static boolean correspond(String[] grille, int x, int y, int dirx, int diry, String mot){
        for (int i = 0;i<mot.length();i++){
            if (grille[x+i*dirx].charAt(y+i*diry)!=mot.charAt(i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Vérifie si une chaîne de caractères est un palindrome.
     *
     * @param mot la chaîne à tester
     * @return true si la chaîne est un palindrome, false sinon
     */
    private static boolean estPalindrome(String mot){
        int i = 0;
        int j = mot.length()-1;
        while (i < j){
            if (mot.charAt(i++) != mot.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
