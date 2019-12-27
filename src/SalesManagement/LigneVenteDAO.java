package SalesManagement;

import java.util.List;

public interface LigneVenteDAO {

    public LigneVente find(int id);

    public void create(LigneVente p);

    public void delete(LigneVente p);

    public void update(LigneVente p);

    public List<LigneVente> findAll();

    public List<LigneVente> findAll(int id);
    
}
