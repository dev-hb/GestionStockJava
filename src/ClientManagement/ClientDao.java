package ClientManagement;

import java.util.List;

public interface ClientDao {
    public Client find(int id);
    public void create(Client c);
    public void delete(Client c);
    public void update(Client c,String nom, String prenom, String adresse , String ville);
    public List<Client> findAll();
  //  public Client findClient(String key);    
}
