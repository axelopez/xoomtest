/*Java SEBSERVER
 *
 * Autor: Axelopez
 * axelopez@gmail.com
 *
 **/
package com.xoom.webserver;

import java.io.*;
import java.net.*;
import java.util.*;

public class webRequest extends Thread {

    static int counter = 0;
    int myid = 0;
    final int ERROR = 0;
    final int WARNING = 1;
    final int DEBUG = 2;

    void postDebug(String mensaje) {

        debug("====" + mensaje, ERROR);
    }

    void debug(String mensaje) {

        debug(mensaje, DEBUG);
    }

    void debug(String mensaje, int gravedad) {
        System.out.println(currentThread().toString() + " - task(" + myid + ") " + mensaje);
    }

    private Socket socketClient = null;		// Server Socket
    private DataOutputStream out = null;		// Out File

    webRequest(Socket socket) {
        debug("Proccess Counter " + counter);

        counter++;
        myid = counter;
        socketClient = socket;
        setPriority(NORM_PRIORITY - 1); //set priority low

    }

    private String construct_http_header(int return_code, int file_type) {
        String result = "HTTP/1.0 ";
        //you probably have seen these if you have been surfing the web a while
        switch (return_code) {
            case 200:
                result = result + "200 OK";
                break;
            case 400:
                result = result + "400 Bad Request";
                break;
            case 403:
                result = result + "403 Forbidden";
                break;
            case 404:
                result = result + "404 Not Found";
                break;
            case 500:
                result = result + "500 Internal Server Error";
                break;
            case 501:
                result = result + "501 Not Implemented";
                break;
        }

        result = result + "\r\n"; //other header fields,
        result = result + "Connection: close\r\n";
        result = result + "Server: XOOM SERVER v0\r\n"; //server name

        //Construct the right Content-Type for the header.
        switch (file_type) {
            //plenty of types for you to fill in
            case 0:
                break;
            case 1:
                result = result + "Content-Type: image/jpeg\r\n";
                break;
            case 2:
                result = result + "Content-Type: image/gif\r\n";
            case 3:
                result = result + "Content-Type: application/x-zip-compressed\r\n";
            case 4:
                result = result + "Content-type: text/css\r\n";
            case 5:
                result = result + "Content-type: application/javascript\r\n";
            default:
                result = result + "Content-Type: text/html\r\n";
                break;
        }

        ////so on and so on......
        result = result + "\r\n"; //this marks the end of the httpheader
        //and the start of the body
        //ok return our newly created header!
        return result;
    }

    public void run() // Start the Thread
    {
        debug("Accept Connection");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));

            //out = new PrintWriter(new OutputStreamWriter(socketClient.getOutputStream(), "8859_1"), true);
            out = new DataOutputStream(socketClient.getOutputStream());

            String data = "";		// buffer for socket data
            String method = "";          //Method of request
            int i = 0;                  // is an index
            int contentLength = 0;     //Size of content for post
            String serverFile = "";     //File to serve
            StringBuilder raw = new StringBuilder();

            String file = "";
            do {
                data = in.readLine();

                if (data != null) {
                    // sleep(500);
                    debug("--" + data + "-");
                    if (method.equals("POST")) {
                        final String contentHeader = "Content-Length: ";
                        if (data.startsWith(contentHeader)) {
                            contentLength = Integer.parseInt(data.substring(contentHeader.length()));
                           
                        }
                    }

                }

                if (i == 0) // la primera linea nos dice que fichero hay que descargar
                {
                    i++;

                    StringTokenizer st = new StringTokenizer(data);
                    String token = st.nextToken();
                    if ((st.countTokens() >= 2) && (token.equals("GET") || token.equals("POST"))) {
                        method = token;
                        serverFile = st.nextToken();
                       
                    } else {
                        out.writeBytes("400 Bad Request");
                    }
                }

            } while (data != null && data.length() != 0);
            StringBuilder body = new StringBuilder();
            if (method.equals("POST"))  {
                int c = 0;
                String param="";
                for (int j = 0; j < contentLength; j++) {
                    c = in.read();
                    body.append((char) c);
                    
                    
                }
                 retornaFichero(serverFile+"?"+body); //Add post parameters for show in readed file
            } else
            {
              retornaFichero(serverFile);
            }

        } catch (Exception e) {
            debug("Serve Error\n" + e.toString());
        } finally {

            System.gc();
        }

        debug("End Proccess ");
    }

    void retornaFichero(String file) {

        debug("Read file" + file);

        debug("Split url for file and parameters: " + file);
        debug("**************************************** ");
        String url[] = file.split("\\?");
        String params[] = new String[0] ;
        debug("File " + url[0]);
        if (url.length > 1) {
            debug("Parameters ");

            params = url[1].split("\\&");

            for (int i = 0; i < params.length; i++) {
                debug(params[i]);
            }
        } else {
            debug("No Parameters");
        }
        debug("****************************************");
        file = url[0];

        // comprobamos si tiene una barra al principio
        if (file.startsWith("/")) {
            file = file.substring(1);
        }

        // si acaba en /, le retornamos el index.htm de ese directorio
        // si la cadena esta vacia, no retorna el index.htm principal
        if (file.endsWith("/") || file.equals("")) {
            file = file + "index.htm";
        }

        try {

            // Read de FILE
            File serverFile = new File("www/" + file);

            if (serverFile.exists()) {

                int type_is = 5;
                //find out what the filename ends with,
                //so you can construct a the right content type
                if (file.endsWith(".zip")) {
                    type_is = 3;
                }
                if (file.endsWith(".jpg") || file.endsWith(".jpeg") || file.endsWith(".png")) {
                    type_is = 1;
                }
                if (file.endsWith(".gif") || file.endsWith(".ico") || file.endsWith(".svg") || file.endsWith(".ttf") || file.endsWith(".woff")) {
                    type_is = 1;

                }
                if (file.endsWith(".css")) {
                    type_is = 4;

                }
                if (file.endsWith(".js")) {
                    type_is = 5;

                }

                out.writeBytes(construct_http_header(200, type_is));

                //BufferedReader localFile = new BufferedReader(new FileReader(serverFile));
                FileInputStream localFile = new FileInputStream(serverFile);

                while (true) {
                    //read the file from filestream, and print out through the
                    //client-outputstream on a byte per byte base.
                    int b = localFile.read();
                    if (b == -1) {
                        break; //end of file
                    }
                    out.write(b);
                }
                
                /*Force to show parameters*/
                if (params.length > 0)
                {
                 out.writeBytes("<p> This is not part of html file is only for show post and get parameters</p>");
                 for (int i = 0; i < params.length; i++) 
                         out.writeBytes("<b>"+params[i]+"</b><br>");
                    }
                 

                String line = "";

                debug("File sended");

                localFile.close();
                out.close();

            } // fin de si el fiechero existe 
            else {
                debug("File not Found: " + serverFile.toString());

                construct_http_header(404, 0);

                out.close();
            }

        } catch (Exception e) {
            debug("Seding file exception: " + e.getMessage());
        }

    }
}
