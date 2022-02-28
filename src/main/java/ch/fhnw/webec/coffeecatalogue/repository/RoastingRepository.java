package ch.fhnw.webec.coffeecatalogue.repository;

import ch.fhnw.webec.coffeecatalogue.model.Roasting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoastingRepository extends JpaRepository<Roasting, Long> {
}
