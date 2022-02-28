package ch.fhnw.webec.coffeecatalogue.repository;

import ch.fhnw.webec.coffeecatalogue.model.Roaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoasterRepository extends JpaRepository<Roaster, Long> {
}
