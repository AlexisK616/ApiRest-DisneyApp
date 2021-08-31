
package com.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class ExceptionConfigHandler {
    
    
   @ExceptionHandler(BadRequestException.class)
   public ResponseEntity<?> badRequest(Exception e){
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
   }
   
   @ExceptionHandler(NotFoundException.class)
   public ResponseEntity<?> notFound(Exception e){
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
   }
}
