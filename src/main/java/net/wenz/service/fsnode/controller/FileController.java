package net.wenz.service.fsnode.controller;

import net.wenz.service.fsnode.service.FileService;
import net.wenz.service.fsnode.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping(value = "fs")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private ServletContext context;

    @RequestMapping(value = "/put", method = {RequestMethod.POST})
    @ResponseBody
//    public String put(HttpServletRequest request) throws IOException {
    public String put(@RequestParam("blockid") String blockid, @RequestParam("file") MultipartFile script) throws IOException {
//        String blockid = HttpServletRequestUtil.getString(request, "blockid");

//        CommonsMultipartFile script = null;
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
//        if (commonsMultipartResolver.isMultipart(request)) {
//            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
//            script = (CommonsMultipartFile) multipartHttpServletRequest.getFile("file");
//        } else {
//            System.out.println("Error");
//        }
//        CommonsMultipartFile a = (CommonsMultipartFile) script;
        fileService.saveFile(script, blockid);

        Map<String, Object> ret = new HashMap<>();
        if (script == null)
            ret.put("ret", "failed");
        else
            ret.put("ret", "success");
        return JsonUtil.toJson(ret);
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseEntity<FileSystemResource> get(@RequestParam("blockid") String blockid) throws IOException {

        File file = fileService.getBlock(blockid);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.ok().headers(headers).contentLength(file.length()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
    }

}
