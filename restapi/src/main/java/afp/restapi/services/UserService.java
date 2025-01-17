package afp.restapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import afp.restapi.models.IUsers;
import afp.restapi.models.Roles;
import afp.restapi.models.Users;
import afp.restapi.repositories.UserRepo;
import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCrypt.Version;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserRepo USERREPO;
    private final BCrypt.Hasher HASHALG = BCrypt.with(Version.VERSION_2Y);

    private List<String> sessionList = new ArrayList<>();

    public UserService(UserRepo USERREPO){
        this.USERREPO = USERREPO;
    }

    public List<Users> findAllUsers(){
        return USERREPO.findAll();
    }

    public Users saveUser(Users user){
        user.setPassword(HASHALG.hashToString(6, user.getPassword().toCharArray()));
        user.setEmail(user.getEmail().toLowerCase());
        return USERREPO.save(user);
    }

    public Optional<Users> findUserById(Long id){
        return USERREPO.findById(id);
    }

    public Users updateUser(Users user){
        Optional<Users> userDb = USERREPO.findUserByEMail(user.getEmail());
        user.setPassword(userDb.get().getPassword());
        return USERREPO.save(user);
    }

    public String updatePassword(Long userId, String oldPassword, String newPassword){
        Optional<Users> userDb = USERREPO.findById(userId);
        if (!userDb.isEmpty()) {
            if (BCrypt.verifyer().verify(oldPassword.toCharArray(), userDb.get().getPassword()).verified){
                USERREPO.updatePassword(
                    HASHALG.hashToString(6, newPassword.toCharArray()), 
                    userDb.get().getUserId());
                return "Your new Password is set";
            } else {
                return "Your password is incorrect";
            }
        } else return "User does not exist";
    }

    public void deleteUser(Users user){
        USERREPO.delete(user);
    }

    public Optional<List<Users>> findUserByDate(LocalDate date){
        return USERREPO.findUsersByDate(date);
    }

    public Optional<Users> findUserByIdAndEMail(Long id, String email){
        return USERREPO.findUserByIdAndEMail(id, email);
    }

    public List<Users> getUserByRole(String roleName){
        List<Users> list = USERREPO.findAll();
        List<Users> filteredList = new ArrayList<Users>();

        for (Users user : list) {
            for (Roles role : user.getRoles()) {
                if (role.getRoleName().equals(roleName)) {
                    filteredList.add(user);
                }
            }
        }

        return filteredList;
    }

    public Boolean logIn(String password, String email, HttpServletRequest request) {
        sessionList = (List<String>) request.getSession().getAttribute("USER_SESSION");
        Optional<Users> userDb = USERREPO.findUserByEMail(email);
        if (userDb.isEmpty() == true) return null;

        if(BCrypt.verifyer().verify(password.toCharArray(), userDb.get().getPassword()).verified) {
            if (sessionList == null) sessionList = new ArrayList<>();

            if (!sessionList.contains(userDb.get().getEmail())) sessionList.add(userDb.get().getEmail());

            request.getSession().setAttribute("USER_SESSION", sessionList);
            System.out.println(request.getSession().getAttribute("USER_SESSION"));
            System.out.println(sessionList);
            return true;
        }
        return false;
    }

    public Optional<IUsers> getIUserByEmail(String email){
        return USERREPO.getIUser((email));
    }
}
