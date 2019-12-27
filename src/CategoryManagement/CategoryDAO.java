
package CategoryManagement;

import java.util.List;

public interface CategoryDAO {
    public Category find(int id);
    public void create(Category c);
    public void delete(Category c);
    public void update(Category c,String name, String desc);
    public List<Category> findAll();
    public Category findCate(String key);
}
