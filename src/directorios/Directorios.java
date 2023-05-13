/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package directorios;

import java.util.HashMap;
import java.io.File;
/**
 *
 * @author HP PORTATIL
 */
public class Directorios {

    public static class Arbol{
        
        private class Nodo{
            public String Ruta;
            public String nombre;
            public HashMap<String,Nodo> SubCarpetas = new HashMap<>();

            public Nodo(String Ruta, String nombre) {
                this.Ruta = Ruta;
                this.nombre = nombre;
            }

            public Nodo() {
            }
            
        }
        
        Nodo root;
        
        public Arbol(){
            this.root = new Nodo();
        }
        
        public void GenerarArbol(File carpeta){
            this.root = GenerarArbolRecursivo(root,carpeta);
        }
        
        private Nodo GenerarArbolRecursivo(Nodo nodo, File carpeta){
            if(nodo != null){
                nodo.Ruta = carpeta.getAbsolutePath();
                nodo.nombre = carpeta.getName();

                File[] SubDirectorios = carpeta.listFiles();

                if(SubDirectorios!=null){
                    for(File Directorio: SubDirectorios){
                        if(Directorio.isDirectory()){
                            Nodo nuevo = new Nodo();
                            nuevo = GenerarArbolRecursivo(nuevo,Directorio);
                            nodo.SubCarpetas.put(Directorio.getAbsolutePath(), nuevo);
                        }
                        //con un else aqui detecta archivos
                    }
                }
            }
            
            return nodo;
        }
        
        public void imprimirArbol() {
            imprimirArbol(root, "");
        }

        private void imprimirArbol(Nodo nodo, String prefijo) {
            if(nodo != null){
                System.out.println(prefijo + "+-- " + nodo.nombre);
                prefijo += "    ";
                for (Nodo subnodo : nodo.SubCarpetas.values()) {
                    imprimirArbol(subnodo, prefijo);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Arbol arbol = new Arbol();
        
        File CarpetaBase = new File("C:\\");
        
        arbol.GenerarArbol(CarpetaBase);
        
        arbol.imprimirArbol();
    }
    
}
