package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatoURLValidator implements ConstraintValidator<FormatoURL, String> {

    @Override
    public boolean isValid(String URL, ConstraintValidatorContext context) {
        if (URL == null || URL.isEmpty()) {
            return true;
        }
        String URLPattern= "^(https?://)?[\\w.-]+\\.[a-zA-Z]{2,}(\\S*)?$";
        Pattern pattern = Pattern.compile(URLPattern);
        Matcher matcher = pattern.matcher(URL);
        return matcher.matches();
    }
}
