package net.wenz.service.fsnode.core;

import net.wenz.service.fsnode.model.vo.Notice;
import net.wenz.service.fsnode.utils.MachineUtil;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class ActiveTask {

    // 固定间隔时间执行, 方法执行完成后, 按照间隔时间点再次执行该方法
    // 比如方法执行5s, 定时间隔为3s, 则中间有一次执行不上, 从第6s开始下一次执行
    @Scheduled(cron = "0/600 * * * * *")
    public void task() {
        String mcode = MachineUtil.getMachineCode();

//        Map<String,String> map = new HashMap();
//        map.put("mcode", mcode);
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Notice> entity = restTemplate.getForEntity("http://127.0.0.1:9092/node/active?mcode={mcode}", Notice.class, map);

        String url = "http://127.0.0.1:9092/api/node/active";
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("mcode", mcode);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(url, request);

        // HttpStatus statusCode = entity.getStatusCode();
        // Notice body = entity.getBody();
        // System.out.println("active.getBody(): "+body.getList());

    }

}