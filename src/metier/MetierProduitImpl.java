package metier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MetierProduitImpl implements  IMetier<Produit, Long> {

    private List<Produit> produits = new ArrayList<Produit>();
    File file = new File("files/produits.dat");

    @Override
    public void add(Produit o) throws Exception {
        this.produits.add(o);
        saveAll();
    }

    @Override
    public List<Produit> getAll() throws Exception {
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            produits = (List<Produit>)objectInputStream.readObject();
            objectInputStream.close();
            return produits;
        }catch (Exception e){
            return List.of();
        }
    }

    @Override
    public Optional<Produit> findById(Long id) throws Exception {
        return produits.stream().filter(elm -> elm.getId()==id).findFirst();
    }

    @Override
    public void saveAll() throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(produits);
        objectOutputStream.close();
    }

    @Override
    public void delete(Long id) throws Exception {
        produits.remove(this.findById(id).get());
        saveAll();
    }

}
