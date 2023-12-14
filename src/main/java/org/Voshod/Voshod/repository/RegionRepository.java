package org.Voshod.Voshod.repository;

import org.Voshod.Voshod.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByLogin(String login);
    List<Region> findByHouseNumber(String houseNumber);
    Optional<Region> findBySeriesPassportAndNumberPassport(String seriesPassport, String numberPassport);
    Optional<Region> findByName(String name);

    List<Region> findByNameAndSecondName(String name, String secondName);
    List<Region> findByTelephone(String telephone);
    List<Region> findByMail(String mail);
}
