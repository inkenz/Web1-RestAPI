package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.sql.Date;
import br.ufscar.dc.dsw.dao.IHotelDAO;
import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.domain.Hotel;

@Component
public class DataPromocaoValidator implements ConstraintValidator<DataPromocao, Date> {

	@Autowired
	private IPromocaoDAO dao;

	@Override
	public boolean isValid(Date data, ConstraintValidatorContext context) {
		if (dao != null && data != null) {
			java.sql.Date hoje = new java.sql.Date(new java.util.Date().getTime());
			
			return data.after(hoje) || data.equals(hoje);
		} else {
			return true;
		}

	}
}