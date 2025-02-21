package spring.jey.santander_dev_week.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.jey.santander_dev_week.domain.model.User;

// no generics, usamos o primeiro parametro <T, ID>:
// T = entidade que estamos usando nele
// ID = o tipo do ID
@Repository // deixa explicito que é um repository
public interface UserRepository extends JpaRepository<User, Long> {
    // escrevemos e o Spring já entende que tem que fazer um join
    // uma pesquisa em outra tabela, que no caso é de Account
    boolean existsByAccountNumber(String accountNumber);
}
