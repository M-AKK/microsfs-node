package net.wenz.service.fsnode.core;

import net.wenz.service.fsnode.model.vo.Notice;
import net.wenz.service.fsnode.utils.MachineUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class RegisterRunner implements CommandLineRunner {

    @Value("${master.ip}")
    private String masterIp;

    @Value("${master.port}")
    private long masterPort;

    @Value("${master.url.register}")
    private String masterRegisterURL;

    @Override
    public void run(String... strings) throws Exception {
        String mcode = MachineUtil.getMachineCode();

//        Map<String,String> map = new HashMap();
//        map.put("mcode", mcode);
//        map.put("port", "9096");
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Notice> entity = restTemplate.postForEntity("http://127.0.0.1:9092/api/node/register?mcode={mcode}&port={port}", Notice.class, map);

        String url = String.format("http://%s:%d/%s", masterIp, masterPort, masterRegisterURL);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("mcode", mcode);
        map.add("port", "9096");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Notice> entity = restTemplate.postForEntity(url, request, Notice.class);

//        HttpStatus statusCode = entity.getStatusCode();
        Notice body = entity.getBody();
        System.out.println("register.getBody(): " + body.getList());
    }
}
