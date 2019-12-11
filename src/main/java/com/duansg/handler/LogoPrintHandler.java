package com.duansg.handler;

import java.io.*;

/**
 * @author Duansg
 *
 */
public class LogoPrintHandler {

    public static void print() {
        File file = new File(Class.class.getClass().getResource("/logo.txt").getPath());
        try (
                InputStream is = new FileInputStream(file);
                Reader reader = new InputStreamReader(is);
                BufferedReader bufferedReader = new BufferedReader(reader);
        ){
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
