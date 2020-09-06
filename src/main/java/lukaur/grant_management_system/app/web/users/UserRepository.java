package lukaur.grant_management_system.app.web.users;

import lukaur.grant_management_system.app.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getOneByName(String name);
}
