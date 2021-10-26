package sk.stuba.fei.uim.oop.assignment3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "product with specified id not found")
public class ProductNotFoundException extends RuntimeException{
}
