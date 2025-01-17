package afp.restapi.services;

import java.util.List;

import org.springframework.stereotype.Service;

import afp.restapi.models.Roles;
import afp.restapi.repositories.RoleRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class RoleService {
    private final RoleRepo ROLEREPO;

    public RoleService(RoleRepo ROLEREPO){
        this.ROLEREPO = ROLEREPO;
    }

    public List<Roles> findAllRoles(){
        return ROLEREPO.findAll();
    }

    public Roles saveRole(Roles role){
        return ROLEREPO.save(role);
    }

    public Roles updateRole(Roles role){
        return ROLEREPO.save(role);
    }

    public void deleteRole(Roles role){
        ROLEREPO.delete(role);
    }

    // public Optional<List<Roles>> findRolesByName(String roleName){
    //     return ROLEREPO.findRolesByName(roleName);
    // }

    // public Optional<List<Roles>> findUsersByRole(String roleName){
    //     return ROLEREPO.findUsersByRole(roleName);
    // }
}
