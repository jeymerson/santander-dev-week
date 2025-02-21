package spring.jey.santander_dev_week.service.impl;

import org.springframework.stereotype.Service;
import spring.jey.santander_dev_week.domain.model.User;
import spring.jey.santander_dev_week.domain.repository.UserRepository;
import spring.jey.santander_dev_week.service.UserService;

import java.util.NoSuchElementException;

// classe que implementa a interface
@Service  // Anotação para a classe virar um service
public class UserServiceImpl implements UserService {

    /**
     *  O Spring tráz algumas facilidades de se você tiver algus
     *  atributos que você precisa injetar via spring, você pode definir
     *  eles aqui como nosso userRepository, que será nossa interface
     *  de acesso a dados, e criamos um construtor de UserServiceImpl
     *  para poder injetar o userRepository via construtor, o que é
     *  uma boa prática, o nosso construtor recebe o
     *  como parâmetro e com isso a gente simplimente atribui a nosso
     *  userRepository que definimos como atributo o que recebemos como parâmetro aqui
     *  isso faz com o que o spring entenda que para criar esse compoente de serviço
     *  ele precisa injetar o meu userRepository, porque iremos precisar dele
     *  pronto para ser usado!
     */

    private final UserRepository userRepository;
    public UserServiceImpl (UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User findById(Long id) {
        //orElseThrow() => caso não encontro o usuário dentro do find ele lançara uma exceção!
        //NoSuchElementException::new => é uma solução que tem uma semantica adequada para essa situação
        // caso não encontre o ID o tratamento é feito logo! com o tratamento do Java.util
        return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User create(User userToCreate) {
//        // se o ID for diferente de nulo e identificamos que o usuário já existe
//        if(userToCreate.getId() != null && userRepository.existsById(userToCreate.getId())){
//            // lança uma exceção simples basicamente dizendo que o ID já Existe!
//            throw new IllegalArgumentException("This User ID already exists.");
//        }
        //outras implementações fica a livre dispor
        if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            // lança uma exceção simples basicamente dizendo que a conta já Existe! // acessando uma tabela secundaria
            throw new IllegalArgumentException("This Account mumber already exists.");
        }
        return userRepository.save(userToCreate);
    }
}
