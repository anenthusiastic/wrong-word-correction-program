/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author fatih
 */
public class Text {
    private DataEntryOperator yazar;
    private String content;

    public Text() {
        this.yazar = null;
        this.content=null;
    }

    public Text(DataEntryOperator yazar, String content) {
        this.yazar = yazar;
        this.content = content;
    }

    public DataEntryOperator getYazar() {
        return yazar;
    }

    public void setYazar(DataEntryOperator yazar) {
        this.yazar = yazar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public static String[][] hatali_kelime_bul_duzelt(String dosya_adi){
        
        String hatali_kelimeler = "";
        String duzeltilen_kelimeler ="";
        String duzeltilmis_icerik = ""  ;
        String [][] arrayler = new String[3][1];
        
        try(BufferedReader brmetin = new BufferedReader(new FileReader(dosya_adi))){
            BufferedReader brsozluk = new BufferedReader(new FileReader("words.txt"));
            
            String satir = brmetin.readLine();
            String icerik = "";
            while((satir)!=null){
                satir = brmetin.readLine();
                icerik += satir;
            }
            
            
            
            String [] metinArray = icerik.split(" ");
            
            String sozluk = "";
            String kelime = brsozluk.readLine();
            while((kelime)!=null){
                sozluk +=(" "+kelime);
                kelime = brsozluk.readLine();
                
            }
            String [] sozlukArray = sozluk.split(" ");
            
            metinArray[metinArray.length-1]= metinArray[metinArray.length-1].substring(0, metinArray[metinArray.length-1].length()-5);//.null çıktısını yok etmek için
            for( int i =0; i<metinArray.length;i++){
                    
                if ((metinArray[i].endsWith(".")) || (metinArray[i].endsWith(",")) || (metinArray[i].endsWith("?"))||(metinArray[i].endsWith(";"))||(metinArray[i].endsWith(":"))||(metinArray[i].endsWith("!"))){
                    metinArray[i]= metinArray[i].substring(0, metinArray[i].length()-1);
                }
                    
                if(metinArray[i].contains("-")) { //eğer kelimenin içerisinde - bulunduruyorsa kelimeyi - işaretinden ikiye bölüp her iki kelime için 
                                                    // ayrı ayrı kontrolleri gerçekleştirdim  
                    
                    String [] metinArray2 = metinArray[i].split("-");
                        
                    for (String metinArray21 : metinArray2) {
                        if (!sozluk.contains(metinArray21.toLowerCase())) {
                               hatali_kelimeler+=" "+metinArray21;
                        for (String sozlukArray1 : sozlukArray){
                            if (metinArray21.length()==sozlukArray1.length()){
                            char[] harfler = metinArray21.toCharArray();
                            char[] harfler2 = sozlukArray1.toCharArray();
                            int sayac = 0;
                            for (int k = 0; k<harfler.length;k++){
                                if((harfler[k])!=(harfler2[k])){
                                sayac++;
                                }
                            }
                    
                            if (sayac==1) { //tek harf yanlış girilmişse (klavyedeki girilmek istenen harfe yakın harflerde dahil) düzelten algoritma
                            
                            metinArray21=sozlukArray1;
                            duzeltilen_kelimeler+=" "+metinArray21;
                            break;
                            }
                        
                        
                            //iki harfin yerleri değişik girilmişse düzelten algoritma
                            Arrays.sort(harfler);
                            Arrays.sort(harfler2);
                            if (Arrays.equals(harfler, harfler2)&&sayac==2) {
                            
                            metinArray21=sozlukArray1;
                            duzeltilen_kelimeler+=" "+metinArray21;
                            break;
                            }
                        }    
                        
                    }
                    for (int j=0;j<metinArray21.length()-1;j++){//aynı harf yanlışkla iki kere girildiyse düzelten algoritma
                        if (metinArray21.charAt(j)==metinArray21.charAt(j+1)){
                        String gecicihatali=metinArray21.substring(0,j)+metinArray21.substring(j+1,metinArray21.length());
                        
                            if(sozluk.contains(gecicihatali)){
                                
                                metinArray21=gecicihatali;
                                duzeltilen_kelimeler+=" "+metinArray21;
                                }
                            }
                        } 
                    }
                    }
                    
                    continue;//içinde -bulunan kelime kontrol edildikten sonra aşağıda tekrar kontrol edilmesin diye
                             //contiue kullandım.   
                    }    
                if ((metinArray[i].contains("0")) || (metinArray[i].contains("1")) || (metinArray[i].contains("2")) || (metinArray[i].contains("3")) || (metinArray[i].contains("4")) || (metinArray[i].contains("5")) || (metinArray[i].contains("6")) || (metinArray[i].contains("7")) || (metinArray[i].contains("8")) || (metinArray[i].contains("9"))){
                    continue;
                }  
                    
                if (!sozluk.contains(metinArray[i].toLowerCase())){
                    hatali_kelimeler+=" "+metinArray[i];
                    for (String sozlukArray1 : sozlukArray){
                        if (metinArray[i].length()==sozlukArray1.length()){
                        char[] harfler = metinArray[i].toCharArray();
                        char[] harfler2 = sozlukArray1.toCharArray();
                        int sayac = 0;
                        for (int k = 0; k<harfler.length;k++){
                            if((harfler[k])!=(harfler2[k])){
                                sayac++;
                            }
                        }
                    
                        if (sayac==1) { //tek harf yanlış girilmişse (klavyedeki girilmek istenen harfe yakın harflerde dahil) düzelten algoritma
                            
                            metinArray[i]=sozlukArray1;
                            duzeltilen_kelimeler+=" "+metinArray[i];
                            break;
                        }
                        
                        
                        //iki harfin yerleri değişik girilmişse düzelten algoritma
                        Arrays.sort(harfler);
                        Arrays.sort(harfler2);
                        if (Arrays.equals(harfler, harfler2)&&sayac==2) {
                            
                            metinArray[i]=sozlukArray1;
                            duzeltilen_kelimeler+=" "+metinArray[i];
                            break;
                        }
                    }    
                        
                    }
                    for (int j=0;j<metinArray[i].length()-1;j++){//aynı harf yanlışlıkla iki kere girilmişse düzelten algoritma
                        if (metinArray[i].charAt(j)==metinArray[i].charAt(j+1)){
                        String gecicihatali=metinArray[i].substring(0,j)+metinArray[i].substring(j+1,metinArray[i].length());
                        
                            if(sozluk.contains(gecicihatali)){
                                
                                metinArray[i]=gecicihatali;
                                duzeltilen_kelimeler+=" "+metinArray[i];
                            }
                        }
                    }    
                }
            }
            String [] hatali_kelimeler_array = hatali_kelimeler.split(" ");
            String [] duzeltilen_kelimeler_array = duzeltilen_kelimeler.split(" ");
            
              
            
            
            arrayler[0]=metinArray;
            arrayler[1]=hatali_kelimeler_array;
            arrayler[2]=duzeltilen_kelimeler_array;
            
            
            
            
            
            
                
        
        }
        catch (FileNotFoundException ex) {
        System.out.println("Dosya bulunamadı..");
        } 
        catch (IOException ex) {
        System.out.println("dosya okunurken hata oluştu!");
        }
    return arrayler;
    }
    
    
    
}
