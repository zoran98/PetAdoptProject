package petadopt.service;

import org.springframework.data.domain.Page;

import petadopt.model.User;
import petadopt.web.dto.UserPasswordChangeDto;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> one(Long id);

    List<User> all();

    Page<User> all(int pageNo);

    User save(User user);

    void delete(Long id);

    Optional<User> findbyKorisnickoIme(String username);

	boolean changePassword(Long id, UserPasswordChangeDto userPasswordChangeDto);
}
