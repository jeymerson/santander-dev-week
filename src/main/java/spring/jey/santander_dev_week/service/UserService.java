package spring.jey.santander_dev_week.service;

import spring.jey.santander_dev_week.domain.model.User;

public interface UserService {

    //tipo_de_saída + nome + (tipo de entrada + alias de entrada)
    User findById(Long id);

    User create(User userToCreate);

}
