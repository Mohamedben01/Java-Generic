package presentation;

import metier.MetierProduitImpl;
import metier.Produit;

import java.util.*;

public class Application {

    private final static String  menu = "============>*Menu*<============\n"+
            "1. Afficher la liste des produits.\n" +
            "2. Rechercher un produit par son id.\n" +
            "3. Ajouter un nouveau produit dans la liste.\n" +
            "4. Supprimer un produit par id.\n" +
            "5. Quitter ce programme.\n"+
            "================================";

    public static void main(String[] args) {
        MetierProduitImpl metierProduit = new MetierProduitImpl();
        System.out.println(menu);
        int nbr = 0;
        while (nbr != 5){
            System.out.print("Veuillez saisir votre choix:\t");
            Scanner in = new Scanner(System.in);
            nbr = in.nextInt();
            switch (nbr){
                case 1:
                    System.out.println("================================");
                    System.out.print("1. Afficher la liste des produits:\n");
                    if (metierProduit.getAll().isEmpty())System.out.println("\tLa liste est vide.");
                    else metierProduit.getAll().stream().forEach(elm -> System.out.print(elm.toString()));
                    System.out.println("================================");
                    break;
                case 2:
                    System.out.println("================================");
                    System.out.print("2. Rechercher un produit par son id:\n");
                    System.out.print("Veuillez saisir Id de produit:\t");
                    Scanner id = new Scanner(System.in);
                    long idProduit = id.nextInt();
                    Optional<Produit> produit = metierProduit.findById(idProduit);
                    if(produit.isPresent()) System.out.print(produit.get().toString());
                    else System.out.print("\tIl n'y a pas de produit avec cet id.");
                    System.out.println("================================");
                    break;
                case 3:
                    System.out.println("================================");
                    System.out.print("3. Ajouter un nouveau produit dans la liste:\n");
                    String[] labels = {"Id", "Nom", "Marque", "Prix", "Description", "Nombre En Stock"};
                    List<String> results = new ArrayList<>();
                    Arrays.stream(labels).forEach(elm -> {
                        System.out.print(elm+" de produit:\t");
                        Scanner prd = new Scanner(System.in);
                        String prdName = prd.nextLine();
                        results.add(prdName.toString());
                    });
                    Produit newProduit = new Produit(
                            Long.parseLong(results.get(0)),
                            results.get(1),
                            results.get(2),
                            Double.parseDouble(results.get(3)),
                            results.get(4),
                            Integer.parseInt(results.get(5))
                            );
                    metierProduit.add(newProduit);
                    System.out.println("Produit ajout?? avec succ??s.");
                    System.out.println("================================");
                    break;
                case 4:
                    System.out.println("================================");
                    System.out.print("4. Supprimer un produit par id:\n");
                    System.out.print("Veuillez saisir Id de produit:\t");
                    Scanner prd = new Scanner(System.in);
                    long prdId = prd.nextInt();
                    metierProduit.delete(prdId);
                    System.out.println("Produit supprim?? avec succ??s.");
                    System.out.println("================================");
                    break;
                case 5:
                    System.out.println("================================");
                    System.out.println("merci d'utiliser cette application. A plus tard!!!");
                    System.out.println("================================");
                    break;
            }
        }
    }
}
