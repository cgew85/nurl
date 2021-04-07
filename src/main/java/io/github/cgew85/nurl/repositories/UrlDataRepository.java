package io.github.cgew85.nurl.repositories;

import io.github.cgew85.nurl.model.entities.UrlData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UrlDataRepository extends JpaRepository<UrlData, Long> {

    UrlData findByKey(String key);

    UrlData findByHashedUrl(String hashedUrl);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM UrlData u WHERE u.hashedUrl = :hashedUrl")
    boolean existsByEmail(@Param("hashedUrl") String hashedUrl);
}
