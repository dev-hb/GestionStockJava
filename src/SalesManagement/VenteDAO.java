package SalesManagement;

import java.util.*;

public interface VenteDAO {

    public Vente find(int id);

    public void create(Vente p);

    public void delete(Vente p);

    public void update(Vente p);

    public List<Vente> findAll();
}
