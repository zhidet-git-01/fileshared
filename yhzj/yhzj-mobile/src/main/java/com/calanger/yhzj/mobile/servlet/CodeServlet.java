package com.calanger.yhzj.mobile.servlet;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.calanger.yhzj.mobile.constant.Constants;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class CodeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -594310104427573408L;

	
	  private static final int BLACK = 0xFF000000;
	  private static final int WHITE = 0xFFFFFFFF;
	  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
              this.doPost(request, response);
       }

       // 生成数字和字母的验证码
       public void doPost(HttpServletRequest request, HttpServletResponse response)
                     throws ServletException, IOException {
              
    	String inviteId = request.getParameter("inviteId"); 
    	String text = "http://"+Constants.BASE_DOMAIN+"/register/to-register?inviteId="+inviteId;
   		int width = 100;
   		int height = 100;
   		String format = "JPG";
   		Hashtable hints = new Hashtable();
   		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
   		BitMatrix bitMatrix;
   		BufferedImage img = new BufferedImage(68, 22,BufferedImage.TYPE_INT_RGB);
   		try {
   			bitMatrix = new MultiFormatWriter().encode(text,
   					BarcodeFormat.QR_CODE, width, height, hints);
   			writeToStream(bitMatrix,format,response.getOutputStream());
   		} catch (Exception e) {
   			e.printStackTrace();
   		}

       }
       
       public static void writeToStream(BitMatrix matrix, String format, OutputStream stream)
    		      throws IOException {
    		    BufferedImage image = toBufferedImage(matrix);
    		    if (!ImageIO.write(image, format, stream)) {
    		      throw new IOException("Could not write an image of format " + format);
    		    }
    		  }
       
       public static BufferedImage toBufferedImage(BitMatrix matrix) {
    	    int width = matrix.getWidth();
    	    int height = matrix.getHeight();
    	    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    	    for (int x = 0; x < width; x++) {
    	      for (int y = 0; y < height; y++) {
    	        image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
    	      }
    	    }
    	    return image;
    	  }

}
