package afp.restapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import afp.restapi.models.Roles;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Long> {
    @Query("FROM Roles WHERE roleName = ?1")
    Optional<List<Roles>> findRolesByName(String roleName);

}
