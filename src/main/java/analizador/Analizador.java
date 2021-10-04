
package analizador;

import java.util.ArrayList;
import java.util.Collections;


/**
 *
 * @author dell
 */
public class Analizador {
 private VentanaInicio ventana;
 ArrayList <String> listErrores = new ArrayList <String>();
 ArrayList <String> listLexema = new ArrayList <String>();
 ArrayList <String> list= new ArrayList <String>();
  int inidice=0;
  int estado=0;
  String lexema="";
  
 public Analizador( VentanaInicio ventana){
     this.ventana=ventana;
 }
  
  public void analizar(){
      Buscar bs=new Buscar();
   String todoTexto= this.ventana.getjTextArea1().getText()+" ";
   String textolimpio="";
   
   for(int i=0;i<todoTexto.length(); i++){
   
       char letra=todoTexto.charAt(i);
       switch (letra) {
           case '\r' :
           case '\t' :
          // case '\n' :
           case '\b' :
           case '\f' :
               
               break;
           default:
                textolimpio=textolimpio+letra;
       }
       
   }
   
   for(int indice=0; indice<textolimpio.length();indice++){
       char letra=textolimpio.charAt(indice);
       char Error;
       switch (estado) {
           case 0:
               if(Character.isLetter(letra)){
                estado=1;
                lexema=""+letra;
               }
               else if(Character.isDigit(letra)){
                   estado=2;
                   lexema=""+letra;
               }
               else if(letra==' ' | letra=='\n'){
                   estado=0;
               }
               else if (letra=='.' | letra==',' | letra=='.' | letra==';' | letra==':' ){
                   estado=4;
                   lexema=""+letra;
               }
               else if(letra=='+' | letra=='-' |letra=='*' | letra=='/' |letra=='%' ){
                   estado=5;
                   lexema=""+letra;
               }
               else if (letra=='(' | letra==')' | letra=='[' |  letra==']' | letra=='{' | letra=='}' ){
                   estado=6;
                   lexema=""+letra;
               }
               else {
               bs.buscarError(ventana.getjTextArea1(),String.valueOf(letra));
               System.out.println("Error Lexico   "+letra);
               listErrores.add("Error el carcater no existe en el alfabeto   "+letra);
               }
               
               break;
               
           case 1:
               if(Character.isLetter(letra) | Character.isDigit(letra) ){
                   estado=1;
                   lexema=lexema+letra;
                  
               }
               else if(letra==' ' | letra=='\n'){
                    listLexema.add("Token:  identificador    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    indice--;
               }
               else {
                  String error=lexema+letra;
                 bs.buscarError(ventana.getjTextArea1(), error);
                  listLexema.add("Token: identificador    Lexema:  "+lexema);
                  listErrores.add("Se esperaba un numero o una letra  Error------>"+lexema+letra);
                  System.out.println("Error  ---"+letra);
                  lexema="";
                  estado=0;
                
               }
               break;
               
           case 2:
               if(Character.isDigit(letra)){
                        estado=2;
                        lexema=lexema+letra;
                        
                  }
               else if(letra=='.'){
                    estado=3;
                    lexema=lexema+letra;
               }
               else if(letra==' ' | letra=='\n'){
                    listLexema.add("Token:  Entero    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    indice--;
               }
               else {
                  bs.buscarError(ventana.getjTextArea1(),String.valueOf(lexema+letra));
                  listLexema.add(lexema);
                  System.out.println("Error Lexico   "+letra);
                  listErrores.add("Se esperava un numero    " +lexema+letra);
                  lexema="";
                  estado=0;
                 
               }
                
               break;
               
           case 3:
               if(Character.isDigit(letra)){
                        estado=7;
                        lexema=lexema+letra;
                        
                  }
           
              else {
                   bs.buscarError(ventana.getjTextArea1(),String.valueOf(lexema+letra));
                  listLexema.add(lexema);
                  System.out.println("Error Lexico   "+letra);
                  listErrores.add("Error se esperaba un numero "+lexema+letra);
                  lexema="";
                  estado=0;
                 
                  
               }
               
               
               break;
               
           case 4:
               if(letra==' ' | letra=='\n' ){
                    listLexema.add("Token: Puntuacion    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                    
                  }
               else {
                   bs.buscarError(ventana.getjTextArea1(),String.valueOf(lexema+letra));
                    listLexema.add(lexema);
                    System.out.println("Error Lexico   "+letra);
                    listErrores.add("Se esperaba un espacio "+lexema+letra);
                    lexema="";
                    estado=0;
                    
               }
               break;
           case 5:
             if(letra==' ' | letra=='\n'){
                    listLexema.add("Token: Operecion    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                  }
               else {
                  //  bs.buscarError(ventana.getjTextArea1(),String.valueOf(lexema+letra));
                    listLexema.add(lexema);
                    System.out.println("Error Lexico   "+letra);
                    listErrores.add("se esperaba un espacio "+lexema+letra);
                    lexema="";
                    estado=0;
               
               }
               break;
           case 6:
                if(letra==' ' | letra=='\n'){
                    listLexema.add("Token: Agrupacion    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                  }
               else {
                  //  bs.buscarError(ventana.getjTextArea1(),String.valueOf(lexema+letra));
                    listLexema.add(lexema);
                    System.out.println("Error Lexico   "+letra);
                    listErrores.add("Error se esperaba  un espacio  "+lexema+letra);
                    lexema="";
                    estado=0;
                   
               }
                
               break;
           case 7:
               if(Character.isDigit(letra)){
                        estado=7;
                        lexema=lexema+letra;
                        
                  }
               else if(letra==' '| letra=='\n'  ){
                    listLexema.add("Token:  Decimal    Lexema:  "+lexema);
                    lexema="";
                    estado=0;
                   
               }
               
              else {
                  bs.buscarError(ventana.getjTextArea1(),String.valueOf(lexema+letra));
                  listLexema.add(lexema);
                  System.out.println("Error Lexico   "+letra);
                  listErrores.add("Error se esperaba una digito o un espacio "+lexema+letra);
                  lexema="";
                  estado=0;
                 
                  
               }
               break;
               
           default:
               throw new AssertionError();
       }
               
   }
  }
  
    public void imprimirError(){
        
        for(int i=0; i<this.listErrores.size();i++){
            String lis= listErrores.get(i);
            this.ventana.getjTextArea2().append(lis+"\n");
        }
        this.ventana.getVerErrores().setVisible(false);
        this.ventana.getjTextArea2().setEditable(false);
    }
    
    public void imprimirLexemas(){
        ventana.getjTextArea2().setText("");
        for(int i=0; i<this.listLexema.size(); i++){
             String lis= listLexema.get(i);
            this.ventana.getjTextArea2().append(lis+"\n");
        
        }
        contarLexemas();
        
       // this.ventana.getVerToken().setVisible(false);
        this.ventana.getjTextArea2().setEditable(false);
        
    }
    
    public void contarLexemas(){
        
        for(int i=0; i<listLexema.size() ;i++){
            int cont=0;
            String texto="";
            for(int j=0;j<listLexema.size();j++){
                if(listLexema.get(i).equals(listLexema.get(j))){
                    cont++;
                    texto=listLexema.get(i);
                } 
            }
            agregarlista(texto+" cantidad de vez: "+cont);
        }    
    }
    
    public  void agregarlista(String campo){
       if(list.contains(campo)){
       
       }
       else {
           list.add(campo);
       }
        
         
    }
  
    
    public void repetidos(){
        ventana.getjTextArea2().setText("");
        for(int i=0; i<list.size(); i++){
             System.out.println("------"+list.get(i));
              this.ventana.getjTextArea2().append(list.get(i)+"\n");
        }
    }
    
    
   
}
