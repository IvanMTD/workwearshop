package ru.workwear.workwearshop.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.workwear.workwearshop.dto.SubjectDTO;

@Component
public class SubjectValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(SubjectDTO.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SubjectDTO subject = (SubjectDTO) target;
        if(!subject.getPassword().equals(subject.getPasswordCheck())){
            errors.rejectValue("passwordCheck","","The password doesn't match");
        }
    }
}
