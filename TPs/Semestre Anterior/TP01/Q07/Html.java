import java.net.*;
import java.io.*;

public class Html {

   //verifica se o nome do site é a flag
   public static boolean stop(String str)
   {
      boolean stop = true;

      if(!(str.length() == 3 && str.charAt(0) == 'F' && str.charAt(1) == 'I' && str.charAt(2) == 'M'))
      {
         stop = false;
      }

      return stop;
   }
   //função para ler o html
   public static String getHtml(String endereco)
   {
      URL url;
      InputStream is = null;
      BufferedReader br;
      String resp = "", line;

      try 
      {
         url = new URL(endereco);
         is = url.openStream();  // throws an IOException
         br = new BufferedReader(new InputStreamReader(is));

         while ((line = br.readLine()) != null) {
            resp += line + "\n";
         }
      } catch (MalformedURLException mue) {
         mue.printStackTrace();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } 

      try {
         is.close();
      } catch (IOException ioe) {
         // nothing to see here

      }

      return resp;
   }


   public static void contador(String str, int tamanho, String nome)
   {

      int out01 = 0;//a
      int out02 = 0;//e
      int out03 = 0;//i
      int out04 = 0;//o
      int out05 = 0;//u
      int out06 = 0;//á
      int out07 = 0;//é
      int out08 = 0;//í
      int out09 = 0;//ó
      int out10 = 0;//ú
      int out11 = 0;//à
      int out12 = 0;//è
      int out13 = 0;//ì
      int out14 = 0;//ò
      int out15 = 0;//ù
      int out16 = 0;//ã
      int out17 = 0;//õ
      int out18 = 0;//â
      int out19 = 0;//ê
      int out20 = 0;//ô
      int out21 = 0;//î
      int out22 = 0;//û
      int out23 = 0;//Consoante-
      int out24 = 0;//<br>-
      int out25 = 0;//<table>-

      for(int i = 0; i < tamanho; i++)
      {
         if(str.charAt(i) == '<')//verifica se será table ou br
         {
            if(str.charAt(i + 1) == 'b' && str.charAt(i + 2) == 'r' && str.charAt(i + 3) == '>')//verifica br>
            {
               out24++;//caso seja br adiciona a quantidade de chars da palavra no i para nao adicionar aos outros contadores
               i += 3;
            }
            else if(str.charAt(i + 1) == 't' && str.charAt(i + 2) == 'a' && str.charAt(i + 3) == 'b' && str.charAt(i + 4) == 'l' && str.charAt(i + 5) == 'e' && str.charAt(i + 6) == '>')//verifica table>
            {
               out25++;//caso seja table adiciona a quantidade de chars da palavra no i para nao adicionar aos outros contadores
               i += 6;
            }
         }
         else if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z')//verifica se está entre 'a' e 'z'
         {
            if(str.charAt(i) == 'a') out01++;
            else if(str.charAt(i) == 'e') out02++;
            else if(str.charAt(i) == 'i') out03++;//verifica as vogais e adiciana a seus respectivos contadores
            else if(str.charAt(i) == 'o') out04++;
            else if(str.charAt(i) == 'u') out05++;
            else//se nao for vogal e consoante(eles nao tem um contador especifico pra cada)
            {
               out23++;
            }
         }
         //usa o numero relativo de cada char para verificar os char especiais
         else if(str.charAt(i) == 225) out06++;//á
         else if(str.charAt(i) == 233) out07++;//é
         else if(str.charAt(i) == 237) out08++;//í
         else if(str.charAt(i) == 243) out09++;//ó
         else if(str.charAt(i) == 250) out10++;//ú
         else if(str.charAt(i) == 224) out11++;//à
         else if(str.charAt(i) == 232) out12++;//è
         else if(str.charAt(i) == 236) out13++;//ì
         else if(str.charAt(i) == 242) out14++;//ò
         else if(str.charAt(i) == 249) out15++;//ù
         else if(str.charAt(i) == 227) out16++;//ã
         else if(str.charAt(i) == 245) out17++;//õ
         else if(str.charAt(i) == 226) out18++;//â
         else if(str.charAt(i) == 234) out19++;//ê
         else if(str.charAt(i) == 238) out20++;//ô
         else if(str.charAt(i) == 244) out21++;//î
         else if(str.charAt(i) == 251) out22++;//û
      }//fim do for
      
      //printa do jeito que o verde quer ler
      MyIO.println("a(" + out01 + ") e(" + out02 + ") i(" + out03 + ") o(" + out04 + ") u(" + out05 + ") á(" + out06 + ") é(" + out07 + ") í(" + out08 + ") ó(" + out09 + ") ú(" + out10 + ") à(" + out11 + ") è(" + out12 + ") ì(" + out13 + ") ò(" + out14 + ") ù(" + out15 + ") ã(" + out16+ ") õ("+out17+") â("+out18+") ê("+out19+") î("+out20+") ô("+out21+") û("+out22+") consoante("+out23+") <br>("+out24+") <table>("+out25+") "+ nome );
   }
    
    
   public static void main(String[] args)
   {
      String nome;
      boolean stop = false;

      while(!stop)
      {
         nome = MyIO.readLine();//recebe o nome, e se for fim sera barrado pela funcao stop
         stop = stop(nome);

         if(!stop)
         {
            String url = MyIO.readLine(); //puxa o link html
            String contentHtml = getHtml(url);//transforma o content do html em string
            
            int tamanho = contentHtml.length();//calcula o tamanho da string

            contador(contentHtml, tamanho, nome);// envia a str, o tamanho e o nome(esse ultimo so servira para ser printado no final)

         }

      }

   }
}
