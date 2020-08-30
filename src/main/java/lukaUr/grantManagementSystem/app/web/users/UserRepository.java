package lukaUr.grantManagementSystem.app.web.users;

import lukaUr.grantManagementSystem.app.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
