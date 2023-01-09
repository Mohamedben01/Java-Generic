package presentation;

import metier.MetierProduitImpl;
import metier.Produit;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.*;

public class Application {



    private final static String  menu = "============>*Menu*<============\n"+
            "1. Afficher la liste des produits.\n" +
            "2. Rechercher un produit par son id.\n" +
            "3. Ajouter un nouveau produit dans la liste.\n" +
            "4. Supprimer un produit par id.\n" +
            "5. Sauvegarder une liste des produits.\n" +
            "6. Quitter ce programme.\n"+
            "================================";

    private final static String[] labels = {"Id", "Nom", "Marque", "Prix", "Description", "Nombre En Stock"};

    public static void main(String[] args) throws Exception {

        MetierProduitImpl metierProduit = new MetierProduitImpl();
        System.out.println(menu);
        int nbr = 0;
        while (nbr != 6){
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
                    else System.out.println("\tIl n'y a pas de produit avec cet id.");
                    System.out.println("================================");
                    break;
                case 3:
                    System.out.println("================================");
                    System.out.print("3. Ajouter un nouveau produit dans la liste:\n");
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
                    System.out.println("Produit ajouté avec succès.");
                    System.out.println("================================");
                    break;
                case 4:
                    System.out.println("================================");
                    System.out.print("4. Supprimer un produit par id:\n");
                    System.out.print("Veuillez saisir Id de produit:\t");
                    Scanner prd = new Scanner(System.in);
                    long prdId = prd.nextInt();
                    metierProduit.delete(prdId);
                    System.out.println("Produit supprimé avec succès.");
                    System.out.println("================================");
                    break;
                case 5:
                    System.out.println("================================");
                    System.out.print("3. Sauvegarder une liste des produits:\n");
                    String quitter = "";
                    int nber = 1;
                    while (!quitter.toLowerCase().equals("n")){
                        System.out.print("==>Produit N°"+nber+"\n");
                        List<String> allResults = new ArrayList<>();
                        Arrays.stream(labels).forEach(elm -> {
                            System.out.print(elm+" de produit:\t");
                            Scanner prdN = new Scanner(System.in);
                            String prdName = prdN.nextLine();
                            allResults.add(prdName.toString());
                        });
                        Produit produitN = new Produit(
                                Long.parseLong(allResults.get(0)),
                                allResults.get(1),
                                allResults.get(2),
                                Double.parseDouble(allResults.get(3)),
                                allResults.get(4),
                                Integer.parseInt(allResults.get(5))
                        );
                        metierProduit.add(produitN);
                        nber++;
                        System.out.print("Continue (y/n): ");
                        Scanner next = new Scanner(System.in);
                        quitter = next.nextLine();
                    }
                    metierProduit.saveAll();
                    System.out.println("Les produits ajoutés avec succès.");
                    System.out.println("================================");
                    break;
                case 6:
                    System.out.println("================================");
                    System.out.println("merci d'utiliser cette application. A plus tard!!!");
                    System.out.println("================================");
                    break;
            }
        }
    }
}
