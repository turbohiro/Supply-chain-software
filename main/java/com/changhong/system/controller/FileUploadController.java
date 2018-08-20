package com.changhong.system.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.changhong.appraise.model.AppraiseFileList;
import com.changhong.appraise.service.AppraiseFileListService;
import com.changhong.common.ExtReturn;
import com.changhong.util.WebConstants;

@Controller
public class FileUploadController {
	@Autowired
	private AppraiseFileListService  appraisefileListService;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @RequestMapping("/fileupload")
    public void processUpload2(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response,
                               PrintWriter writer) {
        try {
            logger.info("start");
            String queryString = request.getQueryString();//得到请求的URL地址中附带的参数
            //截取normcode
    		String str = "fileNormCode=_chmesnorm_";
    		String normId = queryString.substring(queryString.indexOf("fileNormCode=")+str.length());
            // 保存的地址
            String savePath = request.getSession().getServletContext().getRealPath("/upload");
 
            // 以年月/天的格式来存放
            String dataPath = DateFormatUtils.format(new Date(), "yyyy-MM" + File.separator + "dd");
            // 上传的文件名 //需要保存
            String uploadFileName = file.getOriginalFilename();
            String finalPath = File.separator + dataPath + File.separator + uploadFileName;
            logger.debug("savePath:{},finalPath:{}", new Object[]{savePath, finalPath});
            File saveFile = new File(savePath + finalPath);
            // 判断文件夹是否存在，不存在则创建
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            // 写入文件
            FileUtils.writeByteArrayToFile(saveFile, file.getBytes());
            
            // 保存文件的基本信息到数据库
            AppraiseFileList af = new AppraiseFileList();
            String id =  java.util.UUID.randomUUID().toString();
            af.setId(id);
            af.setFilename(uploadFileName);
            af.setFilepath("/upload"+changePath(finalPath));
            //af.setFilecontext(file.getBytes().toString());
            af.setNormcode(normId);
            appraisefileListService.insert(af);
            
            String returnMsg = JSON.toJSONString(new ExtReturn(true, uploadFileName));
            logger.debug("{}", returnMsg);
            writer.print(returnMsg);
        } catch (Exception e) {
            logger.error(WebConstants.EXCEPTION, e);
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * 功能描述：转换反斜杠
     * @param path
     * @return
     * @author sw.j
     */
    public String changePath(String path){
    	return path = path.replace("\\", "/");
    }
}
