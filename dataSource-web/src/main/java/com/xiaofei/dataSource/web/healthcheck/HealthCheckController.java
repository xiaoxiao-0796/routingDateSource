package com.xiaofei.dataSource.web.healthcheck;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Administrator on 2016/12/16 0016.
 */
@Controller
@Slf4j
public class HealthCheckController {

    private String opsInfo;

    public HealthCheckController() {
        BufferedInputStream inputStream = null;
        try {
            log.info("call HealthCheckController");
            inputStream = new BufferedInputStream(
                    HealthCheckController.class.getResourceAsStream("/healthcheck.html"));
            StringBuilder sb = new StringBuilder();
            byte[] line = new byte[2048];
            while (inputStream.read(line) != -1) {
                sb.append(new String(line));
            }
            opsInfo = sb.toString();
        } catch (FileNotFoundException e) {
            opsInfo = "ops info not exist";
        } catch (IOException e) {
            opsInfo = "ops info read error";
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    opsInfo = "ops info read error";
                }
            }

        }
    }

    /**
     * 应用打包相关信息
     *
     * @return project.version
     */
    @RequestMapping("/healthcheck.html")
    @ResponseBody
    public String healthCheck() {
        return opsInfo;
    }
}
