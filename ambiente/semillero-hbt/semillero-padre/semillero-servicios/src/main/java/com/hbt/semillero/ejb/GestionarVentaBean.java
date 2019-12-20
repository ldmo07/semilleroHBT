package com.hbt.semillero.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import com.hbt.semillero.dto.VentaDTO;
import com.hbt.semillero.exceptions.ComicException;

/**
 * 
 * @author Luis David Mercado
 * @version
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GestionarVentaBean implements IGestionarVentaLocal {
	
	/**
	 * Atributo em que se usa para interacturar con los Log de la libreria log4j.
	 */
	final static Logger logger = Logger.getLogger(GestionarComicBean.class);

	@Override
	public void crearVenta(VentaDTO ventaDTO) throws ComicException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public VentaDTO consultarVentaCliente(String idCliente) throws ComicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VentaDTO> consultarVentas() throws ComicException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void CalcularTotal(VentaDTO ventaDTO) throws ComicException {
		// TODO Auto-generated method stub
		
	}

}
