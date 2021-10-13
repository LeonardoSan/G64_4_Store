package co.edu.unbosque.TiendaVirtual.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
            Files.write(path, bytes);
 
            redirectAttributes.addFlashAttribute("message", "Has subido correctamente" + filename + "', el tamaño del archivo es aproximadamente" +bytes.length/1024+" KB.");
            
            readCSV read_csv = new readCSV(UPLOADED_FOLDER + filename);
            List<String> result = read_csv.read();
            Files.delete(path);
           
            
            ProductosModel producto = new ProductosModel();
            //ProveedorModel proveedor = new ProveedorModel();
            //proveedor.setId((long) 1);
            
            /*producto.setIva_compra(1000.00);
            producto.setCodigo((long) 4);
            producto.setNombre("banana");
            producto.setPrecio_compra(20000.00);
            producto.setPrecio_venta(30000.00);
            //producto.setProveedor(proveedor);
            producto.setNit_proveedor((long) 123123);*/
            
            
            //productoRepository.save(producto);
            
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
                	producto.setCodigo(codigo);
                	producto.setNombre(nombre);
                	producto.setNit_proveedor(nit_proveedor);
                	producto.setPrecio_compra(precio_compra);
                	producto.setIva_compra(iva);
                	producto.setPrecio_venta(precio_venta);
                	
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
}
