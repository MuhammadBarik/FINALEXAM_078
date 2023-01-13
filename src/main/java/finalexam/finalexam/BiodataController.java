/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package finalexam.finalexam;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author M.Barik Addarukutni - 078
 */

@RestController
@ResponseBody
public class BiodataController {
    
    BiodataJpaController jpabiodata = new BiodataJpaController();
    @GetMapping("/GET")
    public List<Biodata> getdata(){
        List<Biodata> listbiodata = new ArrayList<>();
        
        listbiodata = jpabiodata.findBiodataEntities();
        return listbiodata;
    }
    
        // Membuat Method  Post mapping data untuk menambahkan data
    @PostMapping("/POST")
    public String sendData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        
        // Membuat objek baru untuk finaluas
        Biodata data = new Biodata();
        
        
        data= mapper.readValue(json_receive, Biodata.class);
        jpabiodata.create(data); // memanggil method create pada JPA controller
        
        
        return json_receive;
    }
    
    // Membuat Method PUT mapping untuk edit data
    @PutMapping("/PUT")
    public String editData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        
        ObjectMapper mapper = new ObjectMapper();
        Biodata data = new Biodata();
        
        data= mapper.readValue(json_receive, Biodata.class);
        jpabiodata.edit(data); // memanggil method edit pada jpa Controller
        
        
        return json_receive;
    }
    
    // Membuat method Delete mapping data untuk menghapus data pada database
    @DeleteMapping("/DELETE")
    public String deleteData(HttpEntity<String> kiriman) throws Exception{
        String json_receive = kiriman.getBody();
        String message = "no action";
        
        ObjectMapper mapper = new ObjectMapper();
        Biodata data = new Biodata();
        
        data= mapper.readValue(json_receive, Biodata.class);
        jpabiodata.destroy(data.getId()); // memanggil method destroy atau hapus pada jpa Controller
        
        message = "id = " + data.getId().toString() + " Deleted"; // membuat pesan notifikasi untuk pengguna
        
        return message;
    } 
}
