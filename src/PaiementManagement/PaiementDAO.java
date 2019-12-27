package PaiementManagement;

import java.util.List;

public interface PaiementDAO {

    public Paiement find(int id);

    public void create(Paiement p);

    public void delete(Paiement p);

    public void update(Paiement p);

    public List<Paiement> findAll(int id);

}
