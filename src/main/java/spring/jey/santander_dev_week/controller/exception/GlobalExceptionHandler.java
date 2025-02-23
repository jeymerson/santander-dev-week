package spring.jey.santander_dev_week.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

/**
 * Todo: Para controlar as exceções da nossa aplicação!
 * Nota futuramente criar uma estrutura mais complexa para tratamento de erro!
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    // Feito ssa parte, passa a receber os registros de Logger da nossa aplicação!
    // Uma boa prátia deixar essa implementação feita!
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //Vamos fazer um ExceptionHandler para um tipo de exceção!
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException){
        //Retorna a mensagem dizendo que a conta ja existe definida anteriormente com o Erro 422
        return new ResponseEntity<>(businessException.getLocalizedMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
    //Vamos fazer um NoSuchElementException para caso não tenha encontrado um ID!
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException){
        //Retorna a mensagem dizendo que não foi encontrado, definida abaixo e com o Erro 404
        return new ResponseEntity<>("Resource ID not found.", HttpStatus.NOT_FOUND);
    }

    // Erro padrão ou default caso não seja de nem um dos casos acima! O seja uma exceção inesperada!
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleUnexpectedException(Throwable unexpectedException){
        var message = "Unexpected server error, see the logs.";
        logger.error(message,unexpectedException);
        //Mensagem de erro inesperado junto ao código de erro no servidor!
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
