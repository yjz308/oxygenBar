package cn.geekzone.oxygenBar.upload.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.geekzone.oxygenBar.common.entity.Constants;
import cn.geekzone.oxygenBar.common.entity.Result;
import cn.geekzone.oxygenBar.common.entity.Status;
import cn.geekzone.oxygenBar.utils.FileNameUtils;
import cn.geekzone.oxygenBar.utils.FileNameUtils.FileType;
import cn.geekzone.oxygenBar.utils.FilePathConfig;





@RestController
public class UploadController {
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Object userUpload(@RequestParam(name = "file", required = false) MultipartFile file) {
		Result result = new Result();
		FilePathConfig.Type pathType;
		pathType = FilePathConfig.Type.USER;
		if (file == null || file.isEmpty()) {
            return result.setErrorCode().setMessage("无效的文件大小").setStatus(Status.upload_no_file);
        }
        if (!FileNameUtils.isFileType(file.getOriginalFilename(), FileType.IMAGE)) {
            return result.setErrorCode().setMessage("无效的文件类型").setStatus(Status.upload_file_type_err);
        }
        String fileName = FileNameUtils.getUUID() + FileNameUtils.getFileType(file.getOriginalFilename());
        File saveFile = new File(FilePathConfig.getLocalPath(pathType, true), fileName);
        try {
            file.transferTo(saveFile);
        } catch (Exception e) {
            return result.setErrorCode().setMessage("保存文件失败").setStatus(Status.upload_save_fail);
        }
        
        String url = Constants.photoPrefix+FilePathConfig.getUrlPath(pathType) + fileName;
        /*UploadImage uploadImage = new UploadImage();
        uploadImage.setAdminId(null);
        uploadImage.setUserId(null);
        uploadImage.setBusinessId(null);
        if (type == 1) {
            setUserInfo(uploadImage);
        } else if (type == 2) {
            setAdminInfo(uploadImage);
        } else {
            setBusinessInfo(uploadImage);
        }
        uploadImage.setImgUrl(url);
        result = uploadImageService.save(uploadImage);*/
        return result.setSuccessCode().setData(url).add("fileName", file.getOriginalFilename());
    }
	
//	@RequestMapping(value = "/upload/uploadFile", method = RequestMethod.POST)
//    public Object userUpload(HttpServletRequest request) {
//		Result result = new Result();
//		FilePathConfig.Type pathType;
//		pathType = FilePathConfig.Type.USER;
//		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
//		if (files.isEmpty()) {
//            return result.setErrorCode().setMessage("请上传图片").setStatus(Status.upload_no_file);
//        }
//        if (!FileNameUtils.isFileType(files.get(0).getOriginalFilename(), FileType.IMAGE)) {
//            return result.setErrorCode().setMessage("文件类型错误").setStatus(Status.upload_file_type_err);
//        }
//        List<UploadFile> list = new ArrayList<UploadFile>();
//        for(int i=0; i<files.size(); i++) {
//        	String fileName = FileNameUtils.getUUID() + FileNameUtils.getFileType(files.get(i).getOriginalFilename());
//            File saveFile = new File(FilePathConfig.getLocalPath(pathType, true), fileName);
//            try {
//                files.get(i).transferTo(saveFile);
//            } catch (Exception e) {
//                return result.setErrorCode().setMessage("保存文件失败").setStatus(Status.upload_save_fail);
//            }
//            String url = Constants.photoPrefix+FilePathConfig.getUrlPath(pathType) + fileName;
//            UploadFile f = new UploadFile();
//            f.setUrl(url);
//            f.setFileName(files.get(i).getOriginalFilename());
//            list.add(f);
//        }
//        /*UploadImage uploadImage = new UploadImage();
//        uploadImage.setAdminId(null);
//        uploadImage.setUserId(null);
//        uploadImage.setBusinessId(null);
//        if (type == 1) {
//            setUserInfo(uploadImage);
//        } else if (type == 2) {
//            setAdminInfo(uploadImage);
//        } else {
//            setBusinessInfo(uploadImage);
//        }
//        uploadImage.setImgUrl(url);
//        result = uploadImageService.save(uploadImage);*/
//        return result.setSuccessCode().setData(list);
//    }

}
