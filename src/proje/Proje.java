
package proje;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Proje {

    
    public static void main(String[] args){
        int toplam_kelimesayisi =0;
        int top_dogru_kelimesayisi=0;
        int top_duzeltilen_kelimesayisi=0;
        double muh_doğru_yazimorani=0;
        double tıp_hatali_yazimorani=0;
        int tıpsayac=0;
        int muhsayac=0;
        int sayac=0;
        double top_algobasarisi=0;
        
        
       
        
        for(int i =1;i<=5;i++){
        
        int kelimesayisi = Text.hatali_kelime_bul_duzelt("metadosya\\girdi0"+i+".txt")[0].length;
        int dogru_kelimesayisi=Text.hatali_kelime_bul_duzelt("metadosya\\girdi0"+i+".txt")[0].length-Text.hatali_kelime_bul_duzelt("metadosya\\girdi0"+i+".txt")[1].length;//toplam kelime sayisindan hatalıkelimelerin sayısı çıkartıldı.
        
        //bilgiler nesnesinin contentini güncelleme işi.
        int duzeltilen_kelimesayisi=Text.hatali_kelime_bul_duzelt("metadosya\\girdi0"+i+".txt")[2].length;
        int duzeltilemeyen_kelimesayisi=Text.hatali_kelime_bul_duzelt("metadosya\\girdi0"+i+".txt")[1].length-duzeltilen_kelimesayisi;//hatali kelime sayısından düzeltilen kelime sayısı çıkartıldı.
        double doğru_yazimorani=(double)dogru_kelimesayisi/(double)kelimesayisi;
        double hatali_yazimorani=(double)(Text.hatali_kelime_bul_duzelt("metadosya\\girdi0"+i+".txt")[1].length)/(double)kelimesayisi;
        double algo_basarisi =(double) duzeltilen_kelimesayisi/(double)(duzeltilen_kelimesayisi+duzeltilemeyen_kelimesayisi);
        
        
        toplam_kelimesayisi+=kelimesayisi;
        top_dogru_kelimesayisi+=dogru_kelimesayisi;
        top_duzeltilen_kelimesayisi+=duzeltilen_kelimesayisi;
            
            
            if(DEObilgileri("metadosya\\girdi0"+i+".txt").getDepartman().equals("MuhendislikFakultesi ")) {
                
                    muh_doğru_yazimorani+=doğru_yazimorani;
                    muhsayac+=1;
            }        
            else if (DEObilgileri("metadosya\\girdi0"+i+".txt").getDepartman().equals("TipFakultesi ")){
                        tıp_hatali_yazimorani+=hatali_yazimorani;
                        tıpsayac+=1;
            }
                    
            else{        
               
                    sayac++;
                    
            }
        top_algobasarisi+=algo_basarisi;
            
        }
        System.out.println("tüm metinlerdeki toplam kelime sayisi : "+toplam_kelimesayisi);  
        System.out.println("tüm metinlerdeki doğru yazılmış kelime sayisi : "+top_dogru_kelimesayisi);
        System.out.println("tüm metinlerdeki düzeltilen toplam kelime sayisi :"+top_duzeltilen_kelimesayisi);
        
        System.out.println("***************************************************");
        System.out.println("Mühendislik fakültesinde çalışan veri giriş operatörlerinin doğru yazım orani ortalaması : "+(muh_doğru_yazimorani/muhsayac)*100+"%");
        
        System.out.println("Tıp fakültesinde çalışan veri giriş operatörlerinin hatalı yazım oranı ortalaması : "+(tıp_hatali_yazimorani/tıpsayac)*100+"%");
        System.out.println("Algoritmanın hatalı yazım düzeltme başarısı oranı ortalamsı : "+(top_algobasarisi/(tıpsayac+muhsayac+sayac))*100+" %");
        
    
        
    }            
            
        
    public static DataEntryOperator DEObilgileri(String dosya_adi){
        DataEntryOperator yazar = new DataEntryOperator();
        try(BufferedReader br = new BufferedReader(new FileReader(dosya_adi))){
            
            String yazar_bilgileri = br.readLine();
            
            String [] bilgilerArray = yazar_bilgileri.split(",");
            
            yazar.setID(Integer.valueOf(bilgilerArray[0]));
            yazar.setAdSoyad(bilgilerArray[1]);
            yazar.setDepartman(bilgilerArray[2]);
         
            
            
        }
        catch (FileNotFoundException ex) {
        System.out.println("Dosya bulunamadı..");
        } 
        catch (IOException ex) {
        System.out.println("dosya okunurken hata oluştu!");
        }
    return yazar;
    }
    
    public static Text Textbilgileri(String dosya_adi){
        Text  bilgiler = new Text();
        
        
        try(BufferedReader brmetin = new BufferedReader(new FileReader(dosya_adi))){
            
            String satir = brmetin.readLine();
            String metin = "";
            while(satir!=null){
                
                satir = brmetin.readLine();
                metin += satir;
                
            }
        metin = metin.substring(0, metin.length()-4);
            
        bilgiler.setContent(metin);
        bilgiler.setYazar(DEObilgileri(dosya_adi));
        
        Text [] textnesneleri = {bilgiler };
        
        
        
        } catch (FileNotFoundException ex) {
            System.out.println("Dosya bulunamadı");
        } catch (IOException ex) {
            System.out.println("Dosya okunurken hata!");
        }
    return bilgiler ;
    }
        
        
}           