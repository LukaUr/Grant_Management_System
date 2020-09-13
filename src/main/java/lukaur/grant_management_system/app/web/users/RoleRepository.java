package lukaur.grant_management_system.app.web.users;

import lukaur.grant_management_system.app.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getOneByName(String name);
}
