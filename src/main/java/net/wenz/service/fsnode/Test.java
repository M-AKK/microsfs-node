package net.wenz.service.fsnode;

import net.wenz.service.fsnode.model.vo.Notice;
import net.wenz.service.fsnode.utils.MachineUtil;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Test {
    static public void main(String[] args) throws IOException {
//        Map<String,String> map = new HashMap();
//        map.put("path", "/");
//        map.put("page","5");
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Notice> entity = restTemplate.getForEntity("http://127.0.0.1:9092/fs/ls?path={path}", Notice.class, map);
//
//        HttpStatus statusCode = entity.getStatusCode();
//        System.out.println("statusCode.is2xxSuccessful(): "+statusCode.is2xxSuccessful());
//
//        Notice body = entity.getBody();
//        System.out.println("entity.getBody(): "+body.getList());

            // TODO Auto-generated method stub
            String mcode = MachineUtil.getMachineCode();
            System.out.println(mcode);

            for(int i=0; i<100; i++) {
                Random random = new Random();
                int idx = random.nextInt(2);
                System.out.println(idx);
            }
    }
}
