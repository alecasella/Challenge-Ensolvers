package api.challenge.ensolvers.repositories;

import api.challenge.ensolvers.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
