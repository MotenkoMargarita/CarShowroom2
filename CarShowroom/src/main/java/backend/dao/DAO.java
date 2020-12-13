package backend.dao;

import java.util.List;

public interface DAO<Entity, Key> {

    Long insert(Entity model);

    Entity get(Key key);

    boolean update(Entity model);

    boolean delete(Key key);

    List<Entity> getAll();
}
