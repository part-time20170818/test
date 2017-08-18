package com.wg.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
@Controller
public class UploadController {
	
	@RequestMapping("/upload/data")
	public String upLoadPic(HttpServletRequest req) throws IOException{
		//强制转换req
		MultipartHttpServletRequest mr = (MultipartHttpServletRequest) req;
		//接收文件
		MultipartFile mf = mr.getFile("pic");
		//获取文件的字节数组
		byte[] bytes = mf.getBytes();
		//给文件命名
		String fileNmae = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		Random random = new Random();
		for(int i = 0 ;i<5;i++){
			fileNmae =fileNmae+random.nextInt(10);
		}
		//获取原始文件名字
		String oriFileName = mf.getOriginalFilename();
		int lastName    =  oriFileName.lastIndexOf(".");
		String suffix      = oriFileName.substring(lastName);
		
		//获取项目部署的绝对路径
		String path=req.getSession().getServletContext().getRealPath("/");
		
		/*OutputStream out = new FileOutputStream(path+"/upLoad/"+fileNmae+suffix);*/
		
		OutputStream out = new FileOutputStream("C:/Users/SLL/Desktop/img/"+fileNmae+suffix);
		out.write(bytes);
		out.close();
		return "index";
	}
	
	
	  
    @RequestMapping(value="/upload",method=RequestMethod.POST)    
    public String upload(String content,HttpServletRequest request,MultipartFile file) throws IOException{
    	String path=request.getSession().getServletContext().getContextPath();
//    	 //转换成多部分request    
//        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
        System.out.println(content);
        System.out.println(file+"file");
    	byte[] bytes = file.getBytes();
    	System.out.println("11");
        return "index";    
    } 
}
