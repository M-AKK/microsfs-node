package net.wenz.service.fsnode.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface FileService {
    boolean saveFile(MultipartFile file, String blockid);
    public File getBlock(String blockid) throws IOException;
    String getBlockContext(String blockid) throws IOException;
}
