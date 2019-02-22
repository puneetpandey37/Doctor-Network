package com.docnet.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.docconnect.cloud.s3.access.PresignedS3URLService;
import com.docconnect.cloud.s3.model.GenerationParams;
import com.docconnect.cloud.s3.model.S3Options;

@Controller
@RequestMapping("/test")
public class TestController {
	
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public String presignedDownloadURL() {
		
		S3Options s3Options = new S3Options("elyx-bucket", "AKIAJD3OQVNTI56LBDZA", "4M+j9gDkwoIHBCUY9Pferw4cRIJZasaVvI1oEqNw");
		PresignedS3URLService s3urlService = new PresignedS3URLService(s3Options);
		return s3urlService.getPresignedDownloadS3URL("https://s3-ap-southeast-1.amazonaws.com/elyx-bucket/test-put/puneet2.jpg", "puneet2.jpg");
	}
	
	
	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	@ResponseBody
	public String presignedUploadURL() {
		
		S3Options s3Options = new S3Options("elyx-bucket", "AKIAJD3OQVNTI56LBDZA", "4M+j9gDkwoIHBCUY9Pferw4cRIJZasaVvI1oEqNw");
		PresignedS3URLService s3urlService = new PresignedS3URLService(s3Options);
		GenerationParams params = new GenerationParams("test-put/sachin.jpg", true);
		return s3urlService.getPresignedUploadS3URL(params);
	}

}
