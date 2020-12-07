package net.wenz.service.fsnode.service.impl;

import net.wenz.service.fsnode.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;

@Service
public class FileServiceImpl implements FileService {

//    @Autowired
//    ApplicationCache applicationCache;

    @Value("${storageDirectory}")
    private String storagedir;

    @Autowired
    private ServletContext context;

    public String getProjectPath() {
        String url = context.getRealPath("/");
        // System.out.println(url);
        return url;
    }

    @Override
    public boolean saveFile(MultipartFile file, String blockid) {
        // TODO Auto-generated method stub
        String realFileName = file.getOriginalFilename();
        File dirPath = new File(this.getProjectPath() + storagedir);//创建所需要的文件夹
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        String absoluteAddr = this.getProjectPath() + storagedir + "/" + blockid;
        File dest = new File(absoluteAddr);//新生成文件的路径

        boolean bool = true;
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            bool = false;
            e.printStackTrace();
        }
        return bool;
    }

    @Override
    public File getBlock(String blockid) throws IOException {

        String filepath = this.getProjectPath() + storagedir + "/" + blockid;
        File file = new File(filepath);

        return file;
    }

    @Override
    public String getBlockContext(String blockid) throws IOException {

        String filepath = this.getProjectPath() + storagedir + "/" + blockid;
        File filename = new File(filepath);

        ArrayList<String> strArray = new ArrayList<String>();
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while(line != null) {
            strArray.add(line);
            line = br.readLine();
        }
        return String.join("\n", strArray);
    }
}
