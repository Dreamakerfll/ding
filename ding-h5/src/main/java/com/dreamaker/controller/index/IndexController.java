package com.dreamaker.controller.index;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.dreamaker.domain.person.Person;
import com.dreamaker.service.person.PersonService;

@Controller
@RequestMapping("index")
public class IndexController {
	
	Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PersonService personService1;
	
	@RequestMapping("home")
	public String goToPage(){
		System.out.println("goToHome");
		return "index/home";
	}
	
	@RequestMapping("fileUpload")
	public String fileUpload(){
		System.out.println("fileUpload");
		return "index/fileUpload";
	}
	
	@RequestMapping("receiveFile")
	@ResponseBody
	public Map<String,Object> receiveFile(HttpServletRequest request){
		
		String url = request.getScheme() +"://" + request.getServerName()  
		
		                        + ":" +request.getServerPort() 
		
		                        +
		                        request.getServletPath();
		
		        if (request.getQueryString() != null){
		
		            url += "?" + request.getQueryString();
		
		        }

		
		System.out.println("receiveFile");
		// 转存在项目服务器temp文件夹下
				// 文件保存路径
				String filePath = request.getSession().getServletContext().getRealPath("/") + "temp/";

				File uploadFile = new File(filePath);
				if (!uploadFile.exists()) {
					uploadFile.mkdirs();
				}
				CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext()); 


		       //判断 request 是否有文件上传,即多部分请求  
		       if(multipartResolver.isMultipart(request)){  
		           //转换成多部分request    
		           MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
		           //取得request中的所有文件名  
		           Iterator<String> iter = multiRequest.getFileNames(); 
		           String openid = "";
		           Map<String, String[]> map = multiRequest.getParameterMap();
		           for(String str:map.get("filed1")){
		        	   openid = str; 
		            }
		           System.out.println(openid);
		           System.out.println(request.getParameter("filed1"));
		           while(iter.hasNext()){  
		               //记录上传过程起始时的时间，用来计算上传时间  
		               int pre = (int) System.currentTimeMillis();  
		               //取得上传文件  
		               MultipartFile file = multiRequest.getFile(iter.next()); 
		               
		               if(file != null){  
		                   //取得当前上传文件的文件名称  
		                   String myFileName = file.getOriginalFilename();  
		                   //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
		                   if(myFileName.trim() !=""){  
		                       //重命名上传后的文件名  
		                       String fileName = "demoUpload" + file.getOriginalFilename();  
		                        
		                       File localFile = new File ( filePath ,  
		                       fileName ) ;  
		                       
		                       try {
								file.transferTo(localFile);
							} catch (IllegalStateException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}  
		                       
		                   
		               		
		                   }  
		               }  
		               //记录上传该文件后的时间  
		               int finaltime = (int) System.currentTimeMillis();  
		           }  
		             
		       }else{
		    	   
		       }
				
		       Map<String,Object> returnMap = new HashMap<String,Object>();
		       returnMap.put("code", 200);
		       returnMap.put("msg", "成功！");
				return returnMap;
	}
	
	@RequestMapping("receivePerson")
	@ResponseBody
	public Map<String,Object> receivePerson(HttpServletRequest request,Person person){
		
		System.out.println(request.getParameter("name"));
		log.debug("HAHAHAH");
		System.out.println(person.getAge());
		System.out.println(person.getName());
		System.out.println(person.getBirthday());
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("code", 200);
		map.put("name", person.getName());
		
		Person per = personService1.getPerson();
		System.out.println(per.getMessage_text());
		return map;
	}

}
