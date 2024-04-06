package senla.repository;

import org.springframework.stereotype.Repository;
import senla.entities.Entity;

import java.util.ArrayList;
import java.util.List;

@Repository
public abstract class AbstractRepository<T extends Entity> {
    private final List<T> objects = new ArrayList<>();
    private long idCounter = 1;

    public T findById(Long uuid) {
        return objects.stream()
                .filter(obj -> uuid.equals(obj.getUuid()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Object with id " + uuid + " not found"));
    }

    public List<T> findAll() {
        return new ArrayList<>(objects);
    }

    public void save(T object) {
        if (object.getUuid() == null || !objects.contains(object)) {
            if (object.getUuid() == null) {
                object.setUuid(idCounter++);
            }
            objects.add(object);
        } else {
            int index = objects.indexOf(object);
            objects.set(index, object);
        }
    }

    public void deleteById(Long uuid) {
        objects.removeIf(obj -> uuid.equals(obj.getUuid()));
    }
}
