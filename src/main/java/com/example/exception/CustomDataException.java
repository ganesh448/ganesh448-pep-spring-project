
package com.example.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.entity.Message;

import org.springframework.http.HttpStatus;

import java.util.List;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomDataException extends RuntimeException {

    public CustomDataException(String message) {
        super(message);
    }

    public CustomDataException(String message, Throwable cause) {
        super(message, cause);
    }
     @SuppressWarnings(value = "unchecked")
    public List<Message> handleUncheckedCast(Object obj) {
        try {
            // Attempting to cast the object to List<Message>
            if (obj instanceof List<?>) {
                List<?> list = (List<?>) obj;

                // Checking if all elements in the list are of type Message
                for (Object item : list) {
                    if (!(item instanceof Message)) {
                        throw new ClassCastException("List contains non-Message objects");
                    }
                }
               
                return (List<Message>) obj;
            } else {
                throw new ClassCastException("Object is not a List");
            }
        } catch (ClassCastException e) {
            // Handling the unchecked cast exception
            throw new CustomDataException("Unchecked cast from Object to List<Message>", e);
        }
    }
}