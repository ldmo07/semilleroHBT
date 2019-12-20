package com.hbt.semillero.ejb;

import java.util.List;
import javax.ejb.Local;
import com.hbt.semillero.dto.VentaDTO;
import com.hbt.semillero.exceptions.ComicException;

@Local
public interface IGestionarVentaLocal {
	
	public void crearVenta(VentaDTO ventaDTO) throws ComicException;

	public VentaDTO consultarVentaCliente(String idCliente) throws ComicException;

	
	public List<VentaDTO> consultarVentas() throws ComicException;
	
	
	public void CalcularTotal(VentaDTO ventaDTO)throws ComicException;
	

}
