package ServerSide;

import java.util.List;

public interface TransactionDAO {

    public TransactionDAO find(int id);

    public void create(TransactionDAO p);

    public void delete(TransactionDAO p);

    public void update(TransactionDAO p);

    public List<TransactionDAO> findAll();
}
