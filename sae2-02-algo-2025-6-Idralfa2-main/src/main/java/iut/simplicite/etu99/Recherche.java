package iut.simplicite.etu99;


public class Recherche {
    public static int chercheMot(String botteDeFoin, String aiguille) {

        //vérifie si la botte est null
        if(botteDeFoin == null){
            System.out.println("La botte de foin doit être différente de null !");
            return -1;
        }

        //vérifie si l'aiguille est null
        if(aiguille == null){
            System.out.println("L'aiguille doit être différente de null !");
            return -1;
        }

        //vérifie si la botte est vide
        if(botteDeFoin == ""){
            System.out.println("La botte est vide !");
            return 0;
        }

        //vérifie si l'aiguille est vide
        if(aiguille == ""){
            System.out.println("L'aiguille doit être différente de null !");
            return -1;
        }

        //verifie si la matrice est carrée ou horizontale
        if(verifMatrice(botteDeFoin) == false){
            return -1;
        }
        
        //convetir la chaine de caractères en matrice dans un tableau
        char[][] matrice = convertionMatrice(botteDeFoin);

        //cherche le nombre d'occurences
        int nbOccurence = rechercheComplete(matrice, aiguille, botteDeFoin);
        return nbOccurence;

      
    }

    //elle vérifie si la matrice est recevable
    public static boolean verifMatrice(String botteDeFoin){
        
        int position1er = botteDeFoin.indexOf('\n');

        if(position1er == -1){
            if(botteDeFoin.length() != 0){
                return true;
            }
        }

        int nbItn = botteDeFoin.length()/(position1er+1);
        int position1erPlus1 = position1er + 1;
        int largeurLigne = position1er;
        int positionDernierN = botteDeFoin.lastIndexOf('\n');
        int longueurDerniereLigne = botteDeFoin.length() - positionDernierN - 1;

        for(int i = 1;i < nbItn; i++){
            if(botteDeFoin.charAt(position1er+position1erPlus1) == '\n'){
                position1er = position1er + position1erPlus1;
            }
            else{
                return false;
            }
        }

        if (longueurDerniereLigne != largeurLigne) {
        return false;
        }
        
        return true;        
    }

    //elle convertit la chaine de caractèes en une matrice dans un tableau
    public static char[][] convertionMatrice(String botteDeFoin) {

        int largeur = botteDeFoin.indexOf('\n');
    
        if (largeur == -1) {
            largeur = botteDeFoin.length();
            char[][] matrice = new char[1][largeur];
            for (int j = 0; j < largeur; j++) {
                matrice[0][j] = botteDeFoin.charAt(j);
            }
            return matrice;
        }
    
        int hauteur = 1;
        for (int i = 0; i < botteDeFoin.length(); i++) {
            if (botteDeFoin.charAt(i) == '\n') {
                hauteur++;
            }
        }
    
        char[][] matrice = new char[hauteur][largeur];
        int index = 0;
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                matrice[i][j] = botteDeFoin.charAt(index);
                index++;
            }
            if (i < hauteur - 1) {
                index++;
            }
        }
        return matrice;
    }

    //cherche l'aiguille horizontalement de gauche à droite
    public static int chercheHorizontal(char[][] matrice, String aiguille, String botteDeFoin){

        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int i = 0; i < hauteur; i++){
            for(int j = 0; j <= largeur - tailleAiguille; j++){ 
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i][j + k] != aiguille.charAt(k)){ 
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille horizontalement de droite à gauche
    public static int chercheHorizontalInverse(char[][] matrice, String aiguille, String botteDeFoin){

        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int i = 0; i < hauteur; i++){
            for(int j = largeur-1; j >= tailleAiguille - 1; j--){ 
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i][j - k] != aiguille.charAt(k)){ 
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille verticalement de haut en bas
    public static int chercheVertical(char[][] matrice, String aiguille, String botteDeFoin){

        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int j = 0; j < largeur; j++){
            for(int i = 0; i <= hauteur - tailleAiguille; i++){
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i + k][j] != aiguille.charAt(k)){
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille verticalement de bas en haut
    public static int chercheVerticalInverse(char[][] matrice, String aiguille, String botteDeFoin){

        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int j = 0; j < largeur; j++){
            for(int i = hauteur - 1; i >= tailleAiguille - 1; i--){
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i - k][j] != aiguille.charAt(k)){
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille dans la premiere diagonale qui part d'en haut à gauche jusqu'à en bas à droite.
    public static int cherchePremiereDiagonale(char[][] matrice, String aiguille, String botteDeFoin){

        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int i = 0; i <= hauteur - tailleAiguille; i++){
            for(int j = 0; j <= largeur - tailleAiguille; j++){
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i + k][j + k] != aiguille.charAt(k)){
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille dans la premiere diagonale qui part d'en bas à droite jusqu'à en haut à gauche
    public static int cherchePremiereDiagonaleInverse(char[][] matrice, String aiguille, String botteDeFoin){
    
        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int i = hauteur - 1; i >= tailleAiguille - 1; i--){
            for(int j = largeur - 1; j >= tailleAiguille - 1; j--){
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i - k][j - k] != aiguille.charAt(k)){
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille dans la seconde diagonale qui part d'en haut à droite jusqu'à en bas à gauche
    public static int chercheSecondeDiagonale(char[][] matrice, String aiguille, String botteDeFoin){
        
        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int i = 0; i <= hauteur - tailleAiguille; i++){
            for(int j = largeur - 1; j >= tailleAiguille - 1; j--){
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i + k][j - k] != aiguille.charAt(k)){
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    //cherche l'aiguille dans la seconde diagonale qui part d'en bas à gauche jusqu'à en haut à droite
    public static int chercheSecondeDiagonaleInverse(char[][] matrice, String aiguille, String botteDeFoin){

        int largeur = matrice[0].length;
        int hauteur = matrice.length;
        int tailleAiguille = aiguille.length();
        int nbPresence = 0;
    
        for(int i = hauteur - 1; i >= tailleAiguille - 1; i--){
            for(int j = 0; j <= largeur - tailleAiguille; j++){
                boolean trouve = true;
                for(int k = 0; k < tailleAiguille; k++){
                    if(matrice[i - k][j + k] != aiguille.charAt(k)){
                        trouve = false;
                        break;
                    }
                }
                if(trouve){
                    nbPresence++;
                }
            }
        }
        return nbPresence;
    }

    public static int rechercheComplete(char[][] matrice, String aiguille, String botteDeFoin){

        int nbPresence = 0;

        nbPresence = nbPresence + chercheHorizontal(matrice, aiguille, botteDeFoin);
        nbPresence = nbPresence + chercheHorizontalInverse(matrice,aiguille,botteDeFoin);
        nbPresence = nbPresence + chercheVertical(matrice,aiguille,botteDeFoin);
        nbPresence = nbPresence + chercheVerticalInverse(matrice,aiguille,botteDeFoin);
        nbPresence = nbPresence + cherchePremiereDiagonale(matrice,aiguille,botteDeFoin);
        nbPresence = nbPresence + cherchePremiereDiagonaleInverse(matrice,aiguille,botteDeFoin);
        nbPresence = nbPresence + chercheSecondeDiagonale(matrice,aiguille,botteDeFoin);
        nbPresence = nbPresence + chercheSecondeDiagonaleInverse(matrice,aiguille,botteDeFoin);

        return nbPresence;
    }


    public static void main(String[] args ){

        String chaine = "IUT";
        String aiguille = "IUT";
        char[][] matriceTest = convertionMatrice(chaine);

        int nbOccu = chercheHorizontal(matriceTest, aiguille, chaine);
        System.out.println(nbOccu);

    }
}
