package co.edu.unbosque.TiendaVirtual.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.TiendaVirtual.readCSV;
import co.edu.unbosque.TiendaVirtual.model.ProductosModel;
import co.edu.unbosque.TiendaVirtual.model.ProveedorModel;
import co.edu.unbosque.TiendaVirtual.repositories.ProductoRepository;

@RestController
@RequestMapping("productos")
public class ProductoService {
	
    private String filename;
    //Save the uploaded file to this folder
    private static String UPLOADED_FOLDER = "C://Temp/";
    
    @Autowired
	private ProductoRepository productoRepository;

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }
    
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
 
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "¡El archivo está vacío! ¡Seleccione un archivo no vacío para cargar!");
            return "redirect:/uploadStatus";
        }
 
        try {
            byte[] bytes = file.getBytes();
            filename = file.getOriginalFilename();
            System.out.println("archivo: " + filename);
            Path path = Paths.get(UPLOADED_FOLDER + filename);
            System.out.println("Path: " + path);
            System.out.println(path.toAbsolutePath());
            
            File fRuta = new File(UPLOADED_FOLDER);
            if (!fRuta.exists()) {
            	fRuta.mkdir();
            }
            
            Files.write(path, bytes);
 
            redirectAttributes.addFlashAttribute("message", "Has subido correctamente" + filename + "', el tamaño del archivo es aproximadamente" +bytes.length/1024+" KB.");
            
            readCSV read_csv = new readCSV(UPLOADED_FOLDER + filename);
            List<String> result = read_csv.read();
            Files.delete(path);
           
            
            ProductosModel producto = new ProductosModel();
            ProveedorModel proveedor = new ProveedorModel();
            
            productoRepository.deleteAll();
            
            int i = 0;
            for(String str : result)
            {
            	if(i > 0) {
            		System.out.println("producto: " + str);
            		String[] split = str.split(";");
                	Long codigo = Long.parseLong(split[0]);
                	String nombre = split[1];
                	Long nit_proveedor = Long.parseLong(split[2]);
                	Double precio_compra = Double.parseDouble(split[3]);
                	Double iva = Double.parseDouble(split[4]);
                	Double precio_venta = Double.parseDouble(split[5]);
                	
                	producto = new ProductosModel();
                	proveedor = new ProveedorModel();
                	proveedor.setId((long) 1);
                	proveedor.setNit(nit_proveedor);
                	producto.setCodigo(codigo);
                	producto.setNombre(nombre);
                	producto.setPrecio_compra(precio_compra);
                	producto.setIva_compra(iva);
                	producto.setPrecio_venta(precio_venta);
                	producto.setProveedor(proveedor);
                	
                	productoRepository.save(producto);
                	
            	}
            	
            	i++;
            }
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
 
        return "productos";
    }
    
    @GetMapping("/listar")
	public List<ProductosModel> listar() {
		return productoRepository.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<ProductosModel> consultar(@PathVariable("id") Long id) {
		return productoRepository.findById(id);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public void eliminar(@PathVariable("id") Long id) {
		productoRepository.deleteById(id);
	}
	
	@PutMapping("/actualizar")
	public void actualizar(@RequestBody ProductosModel producto) {
		productoRepository.save(producto);
	}
}
