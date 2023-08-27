package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.ISiteDAO;
import br.ufscar.dc.dsw.domain.Site;

@Component
public class UniqueURLValidator implements ConstraintValidator<UniqueURL, String> {

	@Autowired
	private ISiteDAO dao;

	@Override
	public boolean isValid(String URL, ConstraintValidatorContext context) {
		if (dao != null) {
			Site site = dao.findByURL(URL);
			return site == null;
		} else {
			return true;
		}

	}
}