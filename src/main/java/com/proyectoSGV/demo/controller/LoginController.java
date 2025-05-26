package com.proyectoSGV.demo.controller;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.proyectoSGV.demo.main.ProductosExistencias;
import com.proyectoSGV.demo.main.ProductosVentas;
import com.proyectoSGV.demo.main.Usuarios;
import com.proyectoSGV.demo.main.Venta;
import com.proyectoSGV.demo.main.VentaDTO;
import com.proyectoSGV.demo.service.DetallesVentasService;
import com.proyectoSGV.demo.service.ProductoVentasService;
import com.proyectoSGV.demo.service.ProductosExistenciaService;
import com.proyectoSGV.demo.service.UsuariosService;
import com.proyectoSGV.demo.service.VentasService;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import com.proyectoSGV.demo.main.DetallesVentas;

@RequestMapping("/auth")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LoginController {

	@Autowired
	private UsuariosService userService;

	@Autowired
	private ProductosExistenciaService productosExistService;

	@Autowired
	private ProductoVentasService productosVentasService;

	@Autowired
	private VentasService ventasService;

	@Autowired
	private DetallesVentasService detallesVentasService;
	
	private String userName;

	/*
	 * @PostMapping("/login") public ResponseEntity<LoginRequest> login(@RequestBody
	 * LoginRequest loginRequest) {// @RequestBody lo que hace es // parsear la
	 * informacion que le // llega en JSON al objecto // correspondiente // y espera
	 * la informa en el // cuerpo de la consulta // no en la URL
	 * 
	 * try { boolean usuExiste = userService.usuExiste(loginRequest.getUsername(),
	 * loginRequest.getPassword());
	 * 
	 * if (usuExiste) {
	 * 
	 * return new ResponseEntity<>(HttpStatus.OK); } else { return new
	 * ResponseEntity<>(HttpStatus.UNAUTHORIZED); }
	 * 
	 * } catch (Exception e) { System.out.println(e); return new
	 * ResponseEntity<>(HttpStatus.BAD_REQUEST);
	 * 
	 * } }
	 */

	/*
	 * @PostMapping("/login") public ResponseEntity<String> loginUser(@RequestBody
	 * Usuarios user) { boolean valid = userService.authenticate(user.getUsername(),
	 * user.getPassword()); if (valid) { return ResponseEntity.ok("Login correcto");
	 * } return ResponseEntity.status(401).body("Credenciales inválidas"); }
	 */

	public ResponseEntity<String> autorizacion(String authorizationHeader) {

		// El encabezado de autorización debería tener el formato "Basic
		// base64UsernamePassword"
		System.out.println(authorizationHeader);
		try {
			if (authorizationHeader != null && authorizationHeader.startsWith("Basic")) {
				// Extraer credenciales codificadas de base64
				String base64Credentials = authorizationHeader.substring("Basic".length()).trim();
				String credentials = new String(Base64.getDecoder().decode(base64Credentials));

				// Divide las credenciales, se espera que el formato sea "usuario:contraseña"
				final String[] values = credentials.split(":", 2);

				if (values.length == 2) {
					String username = values[0];
					String password = values[1];

					// Imprimir para depuración
					System.out.println("Username: " + username);
					System.out.println("Password: " + password);

					// Autenticar al usuario
					boolean valid = userService.authenticate(username, password);
					if (valid) {
						this.userName = username;
						return new ResponseEntity<>(HttpStatus.OK);
					}
				}
			}
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestHeader("Authorization") String authorizationHeader) {

		ResponseEntity<String> respuesta = autorizacion(authorizationHeader);

		return respuesta;

	}

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Usuarios user) {

		System.out.println(user);
		userService.saveUser(user);
		return ResponseEntity.ok("User registered successfully");
	}

	@PostMapping("/registroVenta")
	public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {

		try {

			// System.out.println(venta);

			boolean ventaResgistrada = ventasService.registrarVenta(venta, this.userName);

			if (ventaResgistrada) {

				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}

	}

	@PostMapping("/actualizarProducto") // creo que debo utilizar put o path
	public ResponseEntity<ProductosExistencias> updateProExistencias(
			@RequestBody ProductosExistencias productoActualizado) {

		boolean productoActu = productosExistService.actualizarProducto(productoActualizado);

		try {
			if (productoActu) {

				// SI producto ES DISTINTO DE NULL, ENVIAMOS EL PRODUCTO
				return new ResponseEntity<>(HttpStatus.OK);

			} else {

				// ES NULL, NO LO ENCONTRO EN LA BASE DE DATOS
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}

	}

	@PostMapping("/agregarProducto") // creo que debo utilizar put o path
	public ResponseEntity<ProductosExistencias> agregarProducto(@RequestBody ProductosExistencias productoNuevo) {

		boolean productoN = productosExistService.aggNuevoProducto(productoNuevo);

		try {
			if (productoN) {

				// SI producto ES DISTINTO DE NULL, ENVIAMOS EL PRODUCTO
				return new ResponseEntity<>(HttpStatus.OK);

			} else {

				// ES NULL, NO LO ENCONTRO EN LA BASE DE DATOS
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}

	}

	@DeleteMapping("/productoEliminar/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {

		boolean productoEliminado = productosExistService.eliminarProductoExistencia(id);

		try {
			if (productoEliminado) {

				// SI ME RETORNA TRUE, ES PORQUE EL PRODCUTO EXISTE Y SE ELIMINO CORRECTAMENTE
				return new ResponseEntity<>(HttpStatus.OK);

			} else {

				// NO EXISTE EL PROCUTO O HUBO UN ERROR AL MOMENTO DE ELIMINAR
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/buscador/{codigo}") // CREO QUE NO ES NECESARIO CAPTURAR EL EL HEADER DE
	public ResponseEntity<ProductosVentas> buscaddorProductos(@PathVariable long codigo) {
		System.out.println(codigo);
		ProductosVentas producto = productosVentasService.buscarPorCodigo(codigo);
		System.out.println(producto);
		try {
			if (producto != null) {

				// SI producto ES DISTINTO DE NULL, ENVIAMOS EL PRODUCTO
				return ResponseEntity.ok(producto);

			} else {

				// ES NULL, NO LO ENCONTRO
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping("/ventas")
	public ResponseEntity<List<VentaDTO>> getVentas(
			@RequestParam(required = true) String fechaInicio,
			@RequestParam(required = true) String fechaFinPar) {

		LocalDate fechaIni = LocalDate.parse(fechaInicio);
		LocalDate fechaFin = LocalDate.parse(fechaFinPar);

		LocalDateTime fechaIniTime = fechaIni.atStartOfDay();
		LocalDateTime fechaFinTime = fechaFin.atStartOfDay();

		List<Venta> ventas = ventasService.findByFechaBetween(fechaIniTime, fechaFinTime);

		List<VentaDTO> ventasDTO = new ArrayList<>();
		for (Venta venta : ventas) {
			VentaDTO dto = new VentaDTO();
			dto.setIdVenta(venta.getId());
			dto.setMetodoPago(venta.getMetodoPago());
			dto.setTotalVenta(venta.getTotalVenta());
			dto.setIva(venta.getIva());
			dto.setIdUsuario(venta.getUsuario().getId());
			dto.setFecha(venta.getFecha());
			ventasDTO.add(dto);
		}
		
		
		
		
		try {
			if (ventasDTO != null) {

				// SI producto ES DISTINTO DE NULL, ENVIAMOS EL PRODUCTO
				for (VentaDTO venta : ventasDTO) {
					System.out.println(venta);
				}
				return ResponseEntity.ok(ventasDTO);

			} else {
				
				// ES NULL, NO LO ENCONTRO
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/buscadorStock/{codigo}")
	public ResponseEntity<ProductosExistencias> buscaddorProductosStock(@PathVariable long codigo) {

		ProductosExistencias producto = productosExistService.buscarPorCodigo(codigo);

		try {
			if (producto != null) {

				// SI producto ES DISTINTO DE NULL, ENVIAMOS EL PRODUCTO
				return ResponseEntity.ok(producto);

			} else {

				// ES NULL, NO LO ENCONTRO
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}

	}

	/*
	 * @GetMapping("/generarPDF/{codigo}") public ResponseEntity<?>
	 * CrearReporteProductoExistentes(@PathVariable long codigo) {
	 * 
	 * List<ProductosExistencias> productosExistentes =
	 * productosExistService.productosExistencias();
	 * 
	 * 
	 * 
	 * try { if (productosExistentes != null) {
	 * 
	 * productosExistService.generarInforme(productosExistentes); // SI producto ES
	 * DISTINTO DE NULL, ENVIAMOS EL PRODUCTO return ResponseEntity.ok("");
	 * 
	 * } else {
	 * 
	 * // ES NULL, NO LO ENCONTRO return ResponseEntity.notFound().build(); }
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println(e); return ResponseEntity.badRequest().build(); }
	 * 
	 * }
	 */

	@GetMapping("/generarPDFProductos/{codigo}")
	public ResponseEntity<byte[]> CrearReporteProductoExistentes(@PathVariable long codigo) {
		List<ProductosExistencias> productosExistentes = productosExistService.productosExistencias();

		
		try {
			if (productosExistentes != null) {
				// Genera el reporte y lo exporta a un archivo PDF en memoria (sin necesidad de
				// guardarlo en disco)
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				generarInforme(productosExistentes, outputStream,"repost\\Invoice.jasper");

				// Configura la respuesta para que el navegador lo descargue como un archivo
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_PDF);
				headers.setContentDisposition(ContentDisposition.attachment().filename("listadoProductos.pdf").build());
				return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
			} else {
				// Es NULL, no lo encontró
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	@GetMapping("/generarPDFVenta/{codigoVenta}")
	public ResponseEntity<byte[]> CrearReporteVentasExistentes(@PathVariable int codigoVenta) {
		//List<ProductosExistencias> productosExistentes = productosExistService.productosExistencias();

		List<DetallesVentas> detalleVenta = detallesVentasService.ventas(codigoVenta);
		
		for (DetallesVentas detallesVentas : detalleVenta) {
			
			System.out.println(detallesVentas);
		}
		
		try {
			if (detalleVenta != null) {
				// Genera el reporte y lo exporta a un archivo PDF en memoria (sin necesidad de
				// guardarlo en disco)
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				generarInforme(detalleVenta, outputStream,"repost\\DetalleVentas.jasper");

				// Configura la respuesta para que el navegador lo descargue como un archivo
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_PDF);
				headers.setContentDisposition(ContentDisposition.attachment().filename("listadoVentas.pdf").build());
				return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
			} else {
				// Es NULL, no lo encontró
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}
	
	//UTILIZO ESTE METODO PARA GENERAR EL INFORME, POR PARAMETRO RECIBE LA LISTA DE DATOS Y EL NOMBRE DE ARCHIVO JASPER
	public void generarInforme(List<?> datos, ByteArrayOutputStream outputStream,String nomFicheroJasper) throws JRException {
		String ficheroJasper = nomFicheroJasper;

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
