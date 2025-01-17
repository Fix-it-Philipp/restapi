package afp.restapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import afp.restapi.models.Thread;

@Repository
public interface ThreadRepo extends JpaRepository<Thread, Long>{
    
}
