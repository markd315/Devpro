import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
	int port = 2017;
	ServerSocket serverSocket = new ServerSocket(port);
	System.err.println("Server hosting on port: " + port);
	// repeatedly wait for connections, and process
	while (true) {
	    Socket clientSocket = serverSocket.accept();
	    System.err.println("New client connected");
	    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
	    String s;
	    while ((s = in.readLine()) != null) {
		System.out.println(s);
		if (s.isEmpty()) {
		    break;
		}
	    }
	    out.write("HTTP/1.0 200 OK\r\n");
	    out.write("\r\n");
	    out.write(
		    "<html><head><title>Test</title><link rel=\"shortcut icon\" href=\"https://zh.wiktionary.org/favicon.ico\"/> <head/>");
	    out.write("<body> <h1>Large Text </h1><p> Hello world </p>");
	    out.write("\r\n <a href=\"http://google.com\">google link</a>");
	    for (int i = 0; i < 15; i++)
		out.write("\r\n <p>More lines of text...</p>");
	    out.write("</body> </html>");
	    System.err.println("Connection to client terminated.");
	    out.close();
	    in.close();
	    clientSocket.close();
	}
    }
}
