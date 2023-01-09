package metier;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IMetier<T, U> {

    public void add(T o) throws Exception;

    public List<T> getAll() throws Exception;

    public Optional<T> findById(U id) throws Exception;

    public void saveAll() throws Exception;

    public void delete(U id) throws Exception;
}
