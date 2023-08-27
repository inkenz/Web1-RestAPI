package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.ufscar.dc.dsw.domain.Promocao;


public class DataInicioAntesDoFimValidator implements ConstraintValidator<DataInicioAntesDoFim, Promocao> {

    @Override
    public boolean isValid(Promocao promocao, ConstraintValidatorContext context) {
    	if (promocao == null) {
            return true; 
        }        
    	
    	if(promocao.getInicio() == null || promocao.getFim() == null) {
    		return true;
    	}
    	
        return promocao.getInicio().before(promocao.getFim()); 
    }
}
