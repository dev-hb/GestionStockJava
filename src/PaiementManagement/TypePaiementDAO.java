package PaiementManagement;

import java.util.List;

public interface TypePaiementDAO {

    public TypePaiement find(int id);

    public void create(TypePaiement p);

    public void delete(TypePaiement p);

    public void update(TypePaiement p);

    public List<TypePaiement> findAll();
    
}
