package com.proyectoSGV.demo.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectoSGV.demo.main.ProductosExistencias;
import com.proyectoSGV.demo.repository.ProductoExistenciaRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ProductosExistenciaService {

	@Autowired
	public ProductoExistenciaRepository productosEmp;

	public ProductosExistencias buscarPorCodigo(long codigo) {

		ProductosExistencias producto = productosEmp.findByCodigo(codigo);

		return producto;

	}
	
	
	public List<ProductosExistencias> productosExistencias(){
		
		return productosEmp.findAll();
		
	}

	public boolean actualizarProducto(ProductosExistencias productoActualizado) {

		ProductosExistencias producActualizado = productosEmp.save(productoActualizado);

		if (producActualizado != null) {
			return true;
		}

		return false;
	}

	public boolean aggNuevoProducto(ProductosExistencias productoNuevo) {

		ProductosExistencias producActualizado = productosEmp.save(productoNuevo);

		if (producActualizado != null) {
			return true;
		}

		return false;

	}

	public boolean eliminarProductoExistencia(int id) {

		Optional<ProductosExistencias> producto = productosEmp.findById(id);

		if (producto.isEmpty()) {
			return false;
		}

		productosEmp.deleteById(id);

		return true;

	}
	/*
	public  void generarInforme(List<ProductosExistencias> datos) throws JRException {

		String ficheroJasper = "repost\\Invoice.jasper";

		String nombreInforme = "repost\\listadoProductos.pdf";

		JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);

		// COMPILAMOS PLANTILLA
		InputStream jasperStream = getClass().getResourceAsStream(ficheroJasper);
		
		if(jasperStream == null) {
			System.out.println("es nul");
		}
		
		JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
		//JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		// RELLENAMOS INFORME
		// LO DE NULL ES PARA PASAR LOS PARAMETORS
		JasperPrint informe = JasperFillManager.fillReport(jasperReport, null, camposInforme);

		// EXPORTAMOS A PDF

		JasperExportManager.exportReportToPdfFile(informe, nombreInforme);

	}
	*/
	
	
	public void generarInforme(List<ProductosExistencias> datos, ByteArrayOutputStream outputStream) throws JRException {
		String ficheroJasper = "repost\\Invoice.jasper";

	    JRBeanCollectionDataSource camposInforme = new JRBeanCollectionDataSource(datos);

	    // Compilamos plantilla
	   // InputStream jasperStream = getClass().getResourceAsStream(ficheroJasper);
	    
	    
	    //JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(ficheroJasper);
	    
	    
	    
	    // Rellenamos informe
	    JasperPrint informe = JasperFillManager.fillReport(jasperReport, null, camposInforme);

	    // Exportamos a PDF y escribimos el contenido en el OutputStream
	    JasperExportManager.exportReportToPdfStream(informe, outputStream);
	}
	

}
