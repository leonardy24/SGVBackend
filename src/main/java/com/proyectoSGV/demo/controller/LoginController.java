package com.proyectoSGV.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoSGV.demo.main.ProductosExistencias;
import com.proyectoSGV.demo.main.ProductosVentas;
import com.proyectoSGV.demo.main.Venta;
import com.proyectoSGV.demo.service.ProductoVentasService;
import com.proyectoSGV.demo.service.ProductosExistenciaService;
import com.proyectoSGV.demo.service.UsuariosService;
import com.proyectoSGV.demo.service.VentasService;

@RequestMapping("/auth")
@CrossOrigin("*")
@RestController
public class LoginController {

	@Autowired
	private UsuariosService loginSeervice;

	@Autowired
	private ProductosExistenciaService productosExistService;

	@Autowired
	private ProductoVentasService productosVentasService;

	@Autowired
	private VentasService ventasService;

	@PostMapping("/login")
	public ResponseEntity<LoginRequest> login(@RequestBody LoginRequest loginRequest) {// @RequestBody lo que hace es
																						// parsear la informacion que le
																						// llega en JSON al objecto
																						// correspondiente
																						// y espera la informa en el
																						// cuerpo de la consulta
																						// no en la URL

		try {
			boolean usuExiste = loginSeervice.usuExiste(loginRequest.getUsername(), loginRequest.getPassword());

			if (usuExiste) {

				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping("/registroVenta")
	public ResponseEntity<Venta> registrarVenta(@RequestBody Venta venta) {

		try {

			System.out.println(venta);

			boolean ventaResgistrada = ventasService.registrarVenta(venta);

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
	
	@PostMapping("/actualizarProducto")// creo que debo utilizar put o path
	public ResponseEntity<ProductosExistencias> updateProExistencias(@RequestBody ProductosExistencias productoActualizado){
		
		
		
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
	
	@DeleteMapping("/productoEliminar/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Integer id){
		
		boolean productoEliminado = productosExistService.eliminarProductoExistencia(id);
	
		try {
			if (productoEliminado) {

				// SI ME RETORNA TRUE, ES PORQUE EL PRODCUTO EXISTE Y SE ELIMINO CORRECTAMENTE
				return new ResponseEntity<>(HttpStatus.OK);

			} else {

				//NO EXISTE EL PROCUTO O HUBO UN ERROR AL MOMENTO DE ELIMINAR
				return ResponseEntity.notFound().build();
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
		
	}
	

	@GetMapping("/buscador/{codigo}")
	public ResponseEntity<ProductosVentas> buscaddorProductos(@PathVariable int codigo) {

		ProductosVentas producto = productosVentasService.buscarPorCodigo(codigo);

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

	@GetMapping("/buscadorStock/{codigo}")
	public ResponseEntity<ProductosExistencias> buscaddorProductosStock(@PathVariable int codigo) {

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
}
