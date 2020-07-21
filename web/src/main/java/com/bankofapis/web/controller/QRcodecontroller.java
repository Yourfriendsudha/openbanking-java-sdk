package com.bankofapis.web.controller;

import static com.bankofapis.remote.common.Endpoints.OB_JOURNEY_INIT;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankofapis.core.model.cheque.QRimage;
import com.bankofapis.core.model.cheque.chequeRequest;
import com.bankofapis.core.model.cheque.chequeUtil;
import com.bankofapis.web.service.AispService;
import com.bankofapis.web.service.QRService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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

@RestController
@RequestMapping("/open-banking/v3/cheques")
public class QRcodecontroller {

	@Autowired
	private QRService qrService;

	@Autowired
	private AispService aispService;

	@GetMapping(value = OB_JOURNEY_INIT)
	public String initialize(HttpServletResponse response) {
		return aispService.initialize();
	}

//	@RequestMapping(value = "/QRCheque", method = RequestMethod.POST, produces = { MediaType.IMAGE_JPEG_VALUE,
//			MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
//
	@PostMapping(value = "/QRCheque")
	public @ResponseBody ResponseEntity<QRimage> createQRCheque(@RequestBody chequeRequest request) throws WriterException, IOException {

		QRimage image = qrService.generateQR(request);

		return new ResponseEntity<QRimage>(image, HttpStatus.OK);
	}

	@RequestMapping(value = "/shareQR", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {
			MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<String> shareQR(@RequestParam("bene") String bene,
			@RequestParam("qrImage") String qrImage, @RequestParam("user") String user) throws Exception {
		try {
			if (null != qrImage && null != bene) {
				String sucess = qrService.shareQR(qrImage, bene, user);
				return new ResponseEntity<String>(sucess, HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Couldn't share to the Beneficiary. Please try again", HttpStatus.NOT_FOUND);

	}
	



	@RequestMapping(value = "/getAllGenerated", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {
			MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<String> getAllGeneratedPending(@RequestParam("user") String user)
			throws Exception {
		try {
			if (null != user) {
				JSONObject json = new JSONObject();
				json = qrService.getAllGenerated(user);

				return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Couldn't retrive. Please try again", HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/getAllReceived", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = {
			MediaType.IMAGE_PNG_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE })
	public @ResponseBody ResponseEntity<String> getAllReceived(@RequestParam("user") String user) throws Exception {
		try {
			if (null != user) {
				JSONObject json = new JSONObject();
				json = qrService.getAllReceived(user);

				return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<String>("Couldn't retrive. Please try again", HttpStatus.NOT_FOUND);

	}

	@RequestMapping(value = "/submitQR", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.IMAGE_PNG_VALUE)
	public @ResponseBody ResponseEntity<String> submitQR(@RequestParam("qrImage") String qrImage,
			@RequestParam("folder") String folder, @RequestParam("user") String user) throws Exception {
		try {
			if (null != qrImage) {
				JSONObject json = new JSONObject();
				json = qrService.readQR(qrImage, folder, user);
				json.put("Status", "Sent for Processing");
				return new ResponseEntity<String>(json.toString(), HttpStatus.OK);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return new ResponseEntity<String>("not processed", HttpStatus.NOT_FOUND);

	}

}
