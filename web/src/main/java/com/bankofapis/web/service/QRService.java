package com.bankofapis.web.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.bankofapis.core.model.cheque.chequeRequest;
import com.bankofapis.core.model.cheque.chequeUtil;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import ch.qos.logback.core.net.SyslogOutputStream;

@Service
public class QRService {
	

    //private static final Logger logger = LoggerFactory.getLogger(QRService.class);
    
	@Autowired
	private ResourceLoader resourceLoader;
	
	private static FileWriter file;
	
	public String generateQR(chequeRequest chqrequest) throws WriterException, IOException {
		String chequename=chqrequest.getBeneName()+ chqrequest.hashCode();
		String genpath="src/main/resources/qrimages/generated/"+chqrequest.getPayeeName()+"/";

		Files.createDirectories(Paths.get(genpath));
		String qcodePath =  genpath + chequename +"-QRCode.png";
		
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		 Gson gson = new Gson();
		 String amtTxt = chequeUtil.convertToAmountText(chqrequest.getChequeAmount());
		 chqrequest.setChequeAmountText(amtTxt);
		String json = gson.toJson(chqrequest);
		Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = qrCodeWriter.encode(
				json, BarcodeFormat.QR_CODE, 300, 300 , hints );
		Path path = FileSystems.getDefault().getPath(qcodePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
		file = new FileWriter(genpath+chequename+".txt");
				file.write(json.toString());
				file.close();
		return "/qrimages/" + chqrequest.getBeneName()+chqrequest.hashCode() + "-QRCode.png";
	}
	
	
	public JSONObject readQR(String qrImage, String folder) throws Exception {
		final Resource fileResource = resourceLoader.getResource("classpath:/qrimages/"+folder+"/" + qrImage);
		File QRfile = fileResource.getFile();
		BufferedImage bufferedImg = ImageIO.read(QRfile);
		LuminanceSource source = new BufferedImageLuminanceSource(bufferedImg);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		Result result = new MultiFormatReader().decode(bitmap);
		System.out.println("Barcode Format: " + result.getBarcodeFormat());
		System.out.println("Content: " + result.getText());
		JSONObject json = new JSONObject(result.getText().toString());		
		return json;

	}


	public String shareQR(String qrImage, String bene, String user) throws IOException {
		// TODO Auto-generated method stub		
		String genfile="src/main/resources/qrimages/generated/"+user+"/"+qrImage;
		String sharefile="src/main/resources/qrimages/receieved/"+bene+"/"+qrImage;
		//Files.createDirectories(Paths.get(genfile));
		Files.createDirectories(Paths.get("src/main/resources/qrimages/receieved/"+bene+"/"));
		Path temp = Files.move 
		        (Paths.get(genfile),  
		        Paths.get(sharefile)); 		  
		        if(temp != null) 
		        { 
		        	System.out.println(temp);
		            System.out.println("File moved successfully"); 
		            return "Success";
		        } 
		        else
		        { 
		            System.out.println("Failed to move the file"); 
		        } 
		return "failure";
	}


	public JSONObject getAllGenerated(String user) {
		// TODO Auto-generated method stub
		return null;
	}


	public JSONObject getAllReceived(String user) {
		// TODO Auto-generated method stub
		return null;
	}


}
	
	


