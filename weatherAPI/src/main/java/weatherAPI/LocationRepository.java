package weatherAPI;

import org.springframework.data.jpa.repository.JpaRepository;

interface LocationRepository extends JpaRepository<Location, Long>{
    Location findByName(String name);
}
