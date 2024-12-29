package be.ugent.reactive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, value = HttpStatus.CONFLICT)
public class BlogPostConflictException extends Exception {

}
