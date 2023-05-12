//package iss;
//javac Main.java
//java Main.java texts

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File(args[0]);
        File[] list = file.listFiles();
        int count = 0;
        List<String> pathOfTexts = new ArrayList<>();
        HashMap<String, ArrayList<String>> wordCount = new HashMap<String, ArrayList<String>>();
        for(File dir : list) {
            File[] textFiles = dir.listFiles();
            for(File text: textFiles) {
                pathOfTexts.add(text.toString());
                //break;//remove later
                //System.out.println(text.toString());
            }
            //break;//remove later
        }
        String outputName = "";
        for(String text : pathOfTexts) {
            FileReader fr = new FileReader(new File(text));
            String[] textOutput = text.split("/");
            outputName = textOutput[textOutput.length - 1];
            BufferedReader br = new BufferedReader(fr);
            String p = "result" + File.separator + outputName;
            File newFile = new File(p);
            String line = br.readLine();
            FileWriter fw = new FileWriter(new File(p), false);
            BufferedWriter bw = new BufferedWriter(fw);
            String lastWord = "";
            boolean isLast = false;
            
            for(    ; line != null; line = br.readLine()) {
               line = line.toLowerCase().replaceAll("[^a-zA-Z0-9'\\s]+", " ");
               //System.out.println(line);
               String[] str= line.split("\\s+");
               //System.out.println(str.length);
               String str1 = "";
               String str2 = "";
               lastWord = str[str.length - 1];
               for(int i = 0 ; i < str.length ; i ++) {
                    if(str.length > 1 && i < str.length - 1 ) {
                        str1 = str[i];
                        str2 = str[i + 1];
                    } else { //at last word in a line
                        isLast = true;
                        lastWord = str[str.length - 1];
                        break;
                    }    

                    if (!wordCount.containsKey(str[i])) {
                        ArrayList<String> arr = new ArrayList<>();
                        arr.add(str2);
                        wordCount.put(str1, arr); 
                        //System.out.println("added");   
                    } else {
                        ArrayList<String> arr = wordCount.get(str[i]);
                        arr.add(str2);
                        wordCount.put(str[i], arr);
                    }
               }


               bw.write(line);   
               bw.newLine();    
            }
            System.out.println("Written " +  text);
            
            bw.flush();
            bw.close();
        
        }

        for(String key : wordCount.keySet()) {
            //System.out.println("hello im in");
            System.out.println(key);
            ArrayList<String> used = new ArrayList<>();
            double sum = 0.0;
            for(String str : wordCount.get(key)) {
                ArrayList<String> res = wordCount.get(key);
                if(used.contains(str)) {
                    continue;
                }
                used.add(str);
                sum += Collections.frequency(res, str);
                //System.out.println("    " + str + " " + Collections.frequency(res, str));
                //res.removeAll(Collections.singletonList(str));

            }
            // String pp = "probability" + File.separator + outputName;
            // File newFilee = new File(pp);
            // FileWriter fw1 = new FileWriter(new File(pp), true);
            // BufferedWriter bw1 = new BufferedWriter(fw1);

            ArrayList<String> prob = new ArrayList<>();

            for(String str : wordCount.get(key)) {
                ArrayList<String> res = wordCount.get(key);
                if(prob.contains(str)) {
                    continue;
                }
                prob.add(str);
                System.out.println(("    " + str + " " + Collections.frequency(res, str)/ sum));
                //count++;
            //     bw1.write(key);
            //     bw1.newLine();
            //     bw1.write(probRes);
            //     bw1.newLine();

            }
            // bw1.flush();
            // bw1.close();


        }
        //System.out.println(count);
    }
}

