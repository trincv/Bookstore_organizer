package br.edu.ifba.inf008.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.validation.ConstraintViolation;

public class ValidationUtils {
    
    /**
     * Processa um conjunto de violações de validação e exibe-as em um alerta.
     * @param <T> O tipo de objeto que foi validado.
     * @param violations O conjunto de violações.
     * @return true se não houver violações, false se houver.
     */
    public static <T> boolean handleViolations(Set<ConstraintViolation<T>> violations) {
        
        if (!violations.isEmpty()) {

            List<String> errorMessages = new ArrayList<>();
            
            for (ConstraintViolation<T> violation : violations) {
                String fieldName = violation.getPropertyPath().toString();
                errorMessages.add(fieldName + ": " + violation.getMessage());
            }

            String aggregatedMessage = String.join("\n", errorMessages);
            UIUtils.showAlert(aggregatedMessage);
            return false;
        }
        return true;
    }
}
