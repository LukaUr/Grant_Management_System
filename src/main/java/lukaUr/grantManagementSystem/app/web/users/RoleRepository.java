package lukaUr.grantManagementSystem.app.web.users;

import lukaUr.grantManagementSystem.app.web.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getOneByName(String name);
}
