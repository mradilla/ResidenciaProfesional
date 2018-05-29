/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo_II.Submodulos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Martha
 */
public class CopiarArchivo {

    public CopiarArchivo(String srFile, String dtFile) {

            String a= "/Modulo_II/Submodulos/" + srFile;
            System.out.println(a);
            OutputStream out=null;
            try {
                InputStream in =getClass().getResourceAsStream(a);
                System.out.println("in    "+in.toString());
                File f2 = new File(dtFile);
                out = new FileOutputStream(f2);
                byte[] buf = new byte[1024];
                int len=0;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                 out.close();
                   System.out.println("Archivo copiado.");
            }catch(Exception e){
                e.printStackTrace();
            }
          
        
    }
}
