package co.edu.unbosque.TiendaVirtual;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import com.csvreader.CsvReader;
 
/*
 ** clase readCSV: realiza la lectura del contenido del archivo CSV
 */
public class readCSV {
 
    // Parámetro: ruta del archivo
    private String file_path;
 
    // constructor
    readCSV(){}
 
    public readCSV(String file_path){
        this.file_path = file_path;
    }
 
    // getter and setter
    public String getFile_path() {
        return file_path;
    }
 
    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }
 
    // La función read () implementa un método específico para leer el contenido del archivo CSV
    public List<String> read() {
 
        List<String> result = new ArrayList<>();
 
        try {
            // Crear objeto de lectura CSV
            CsvReader csvReader = new CsvReader(file_path);
            while (csvReader.readRecord()){
                // Leer cada fila de datos, separados por comas
                result.add(csvReader.getRawRecord());
            }
            csvReader.close();
            return result;
 
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
    }
 
}
