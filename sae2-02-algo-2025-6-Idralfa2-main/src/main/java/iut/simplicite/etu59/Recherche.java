// public class chercheMot {

//     /**
//      * @param texteComplet 
//      * @param aiguille
//      * @return Le nombre d'apparitions de l'aiguille.
//      */
//     public static int trouverEtCompterMot(String texteComplet, String aiguille) {
//         // on vérifie les paramètres
//         if (texteComplet == null || aiguille == null || aiguille.isEmpty()) {
//             return 0;
//         }
//         int totalOccurrences = 0;
//         String texteMin = texteComplet.toLowerCase();
//         String aiguilleMin = aiguille.toLowerCase();

//         // cherche l'aiguille dans le sens de base
//         totalOccurrences += compterApparitionsSimples(texteMin, aiguilleMin);

//         // cherche l'aiguille dans le sens inverse
//         String aiguilleInverseeMin = inverser(aiguilleMin);
//         totalOccurrences += compterApparitionsSimples(texteMin, aiguilleInverseeMin);

//         return totalOccurrences;
//     }

//     /**
//      * @param grandTexte Le texte 
//      * @param petitMot Le mot cherché.
//      * @return
//      */
//     private static int compterApparitionsSimples(String grandTexte, String petitMot) {
//         int compteurLocal = 0;
//         int indexActuel = 0; // début du texte

//         while (indexActuel != -1) {
//             indexActuel = grandTexte.indexOf(petitMot, indexActuel); // recherche le mot à partir de l'index actuel

//             if (indexActuel != -1) { // il est trouvé
//                 compteurLocal++; 
//                 indexActuel += petitMot.length(); 
//             }
//         }
//         return compteurLocal;
//     }

//     /**
//      * @param chaineAInverser La chaîne à inverser
//      * @return 
//      */
//     private static String inverser(String chaineAInverser) {
//         return new StringBuilder(chaineAInverser).reverse().toString();
//     }

//     /**
//      * @param grandTexte
//      * @param motCible 
//      * @return 
//      */
//     public static int compteOccurrences(String grandTexte, String motCible) {
//         if (motCible == null || motCible.isEmpty()) {
//             return 0;
//         }

//         int compteurFinal = 0;
//         String texteMin = grandTexte.toLowerCase();
//         String motCibleMin = motCible.toLowerCase();
//         compteurFinal += chercheOccurrencesManu(texteMin, motCibleMin);
//         String motCibleInverseMin = creerInverse(motCibleMin);
//         compteurFinal += chercheOccurrencesManu(texteMin, motCibleInverseMin);

//         return compteurFinal;
//     }

//     /**
//      * @param texte 
//      * @param mot 
//      * @return 
//      */
//     private static int chercheOccurrencesManu(String texte, String mot) {
//         int compte = 0;
//         if (mot.length() > texte.length()) {
//             return 0;
//         }
//         for (int i = 0; i <= texte.length() - mot.length(); i++) {
//             boolean correspondanceTrouvee = true; 
//             for (int j = 0; j < mot.length(); j++) {
//                 if (texte.charAt(i + j) != mot.charAt(j)) {
//                     correspondanceTrouvee = false; // pas le bon mot
//                     break; // stop tout
//                 }
//             }

//             if (correspondanceTrouvee) {
//                 compte++; //on en a trouvé une
//             }
//         }
//         return compte;
//     }

//     /**
//      * @param entree la chaîne originale.
//      * @return 
//      */
//     private static String creerInverse(String entree) {
//         String resultatInverse = ""; 
//         for (int i = entree.length() - 1; i >= 0; i--) {
//             resultatInverse += entree.charAt(i); 
//         }
//         return resultatInverse;
//     }

//     /**
//      * @param botteDeFoin 
//      * @param aiguille 
//      * @return 
//      */
//     public static int compterApparitionsTotales(String botteDeFoin, String aiguille) {
//         if (botteDeFoin == null || aiguille == null || aiguille.isEmpty()) {
//             return 0;
//         }

//         int compteFinal = 0;
//         String texteMinuscule = botteDeFoin.toLowerCase();
//         String aiguilleMinuscule = aiguille.toLowerCase();
//         boolean estPalindrome = estPalindrome(aiguilleMinuscule);

//         compteFinal += trouverOccurrences(texteMinuscule, aiguilleMinuscule);
//         if (!estPalindrome) {
//             String aiguilleInversee = inverserMaChaine(aiguilleMinuscule);
//             compteFinal += trouverOccurrences(texteMinuscule, aiguilleInversee);
//         }
//         return compteFinal;
//     }

//     /**
//      * @param chaine 
//      * @return 
//      */
//     private static boolean estPalindrome(String chaine) {
//         if (chaine == null || chaine.length() <= 1) {
//             return true;
//         }
//         int debut = 0;
//         int fin = chaine.length() - 1;
//         while (debut < fin) {
//             if (chaine.charAt(debut) != chaine.charAt(fin)) {
//                 return false; // pas un palindrome
//             }
//             debut++;
//             fin--;
//         }
//         return true; //c'est un palindrome
//     }

//     /**
//      * @param grandTexte Le texte dans lequel ça cherche
//      * @param sousChaine 
//      * @return
//      */
//     private static int trouverOccurrences(String grandTexte, String sousChaine) {
//         int compteur = 0;
//         int indexDepartRecherche = 0;

//         while (true) { // Boucle infinie, on sortira avec 'break'
//             int positionTrouvee = grandTexte.indexOf(sousChaine, indexDepartRecherche);

//             if (positionTrouvee == -1) {
//                 break; // finit la boucle
//             } else {
//                 compteur++; // trouve une occurrence
//                 indexDepartRecherche = positionTrouvee + sousChaine.length();
//             }
//         }
//         return compteur;
//     }

//     /**
//      * @param chaineAInverser
//      * @return 
//      */
//     private static String inverserMaChaine(String chaineAInverser) {
//         return new StringBuilder(chaineAInverser).reverse().toString();
//     }
// }
