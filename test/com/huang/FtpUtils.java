package com.huang;

import java.io.File;
import java.io.IOException;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

/**
 * FTP Utils
 * @author huangchentao
 */
public class FtpUtils {
	private String FTP_HOST = "192.168.100.1";
	private int FTP_PORT = 21;
	private String FTP_USERNAME = "";
	private String FTP_PASSWORD = "";
	
	private static String PATH_SEPARATOR_CHAR = "/";
	private static String ROOT_PATH = "/";
	
	private FTPClient client;
	
	public FtpUtils(){
	}
	
	public FTPClient initClient(String host,int port,String username,String password) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		client = new FTPClient();
		client.connect(host,port);
		client.login(username,password);
		return client;
	}

	private FTPClient initClient() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
		return initClient(FTP_HOST,FTP_PORT,FTP_USERNAME,FTP_PASSWORD);
	}
	
	public void disConnect() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		if(client == null)
			return;
		client.disconnect(true);
	}
	
	private File _download(File file,String root_path){
		try{
			if(client == null)
				client = initClient();
		} catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
