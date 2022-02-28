package ch.fhnw.webec.coffeecatalogue.repository;

import ch.fhnw.webec.coffeecatalogue.model.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BeansRepository extends JpaRepository<Bean, Long> {
    @Query("""
        SELECT DISTINCT bean FROM Bean bean
        INNER JOIN bean.roaster roaster
        INNER JOIN bean.roastings roasting
        WHERE lower(bean.name) LIKE lower(concat('%', :search, '%'))
            OR lower(bean.description) LIKE lower(concat('%', :search, '%'))
            OR lower(roasting.name) LIKE lower(concat('%', :search, '%'))
            OR lower(roaster.name) LIKE lower(concat('%', :search, '%'))
            OR lower(bean.isFavorite) LIKE lower(concat('%', :search, '%'))
    """)
    List<Bean> findBySearch(@Param("search") String search);

}
