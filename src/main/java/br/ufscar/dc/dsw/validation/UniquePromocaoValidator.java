package br.ufscar.dc.dsw.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.dc.dsw.dao.IPromocaoDAO;
import br.ufscar.dc.dsw.domain.Promocao;

import java.util.List;

public class UniquePromocaoValidator implements ConstraintValidator<UniquePromocao, Promocao> {

    @Autowired
	private IPromocaoDAO dao;
	
    @Override
    public boolean isValid(Promocao promocao, ConstraintValidatorContext context) {
    	if (promocao == null || dao == null) {
            return true; 
        }
    	
    	
    	if(promocao.getInicio() == null || promocao.getFim() == null) {
    		return true;
    	}
    	
        List<Promocao> promocoes = dao.findAll();
        
        
        for (Promocao p : promocoes) {
            if(p.getCNPJ().equals(promocao.getCNPJ()) || p.getURL().equals(promocao.getURL())) {
            	if(p.getInicio().before(promocao.getInicio()) && p.getFim().after(promocao.getInicio()))
            		return false;
            	if(p.getInicio().before(promocao.getFim()) && p.getFim().after(promocao.getFim()))
            		return false;
            	if(p.getInicio().after(promocao.getInicio()) && p.getFim().before(promocao.getFim()))
            		return false;
            	if(p.getInicio().before(promocao.getInicio()) && p.getFim().after(promocao.getFim()))
            		return false;
            }
        }

        return true; // Sem conflitos de datas
    }
}
