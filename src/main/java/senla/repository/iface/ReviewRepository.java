package senla.repository.iface;

import senla.entities.Client;
import senla.entities.Review;

import java.util.List;

public interface ReviewRepository {
    Review findById(Long uuid);
    List<Review> findAll();
    void save(Review review);
    void deleteById(Long uuid);
}
