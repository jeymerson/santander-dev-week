package spring.jey.santander_dev_week.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import spring.jey.santander_dev_week.domain.model.User;
import spring.jey.santander_dev_week.service.UserService;

import java.net.URI;

@RestController // implementa o controller
@RequestMapping("/users") // define um path para nosso recurso, que será user
public class UserController {

    //implementando as funcionalidades da camada de serviço
    // o controller vai chamar a camada de serviço para sua implementação
    // estamos usando a interface de userService

    private final UserService userService;

    // injeção de dependências via construtor
    public UserController(UserService userService){
        this.userService = userService;
    }

    //implementando os endpoints
    // uma boa prática elabora DTO para implementação do nosso projeto, especificos
    // para nosso endpoint, pois podem haver informações que você não queira demonstrar
    // para quem acessa sua API, neste caso poderiamos implementar um UserDTO que seria a implementação
    // mencionada
    @GetMapping("/{id}")//ao cria um GetMapping o Spring já entende que queremos expor esse endpoint, do tipo get
    public ResponseEntity<User> findById(@PathVariable Long id){ // neste caso, vai devolver um objeto do tipo "user"
        //Lembrando que PathVariable "pega" o valor que vem pela URI e passa como long com alias de "id"
        var user = userService.findById(id); // faz com que o service acima criado procure um usuário pelo "id"
        return ResponseEntity.ok(user); // devolve a respota de "ok /200" e também o objeto user
        //vai retornar um usuário caso ele exista e como foi implementando anteriormente, caso não
        // exista, será lançado uma exceção
    }

    @PostMapping // assina o método post
    public ResponseEntity<User> create(@RequestBody User userToCreated){
        //@RequestBody é o "corpo" da requisção
        var userCreated = userService.create(userToCreated);
        // agora vamos localizar o nosso usuário criado e devolver por uri
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}") // essa parte será a que for substituida pelo usuário encontrado!
                .buildAndExpand(userCreated.getId())
                .toUri();

        // temos nesse return a localização do usuário criado juntamente o objeto user no Body
        return ResponseEntity.created(location).body(userCreated);

    }


    }
