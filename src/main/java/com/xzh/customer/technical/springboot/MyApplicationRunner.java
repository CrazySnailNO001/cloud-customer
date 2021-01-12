package com.xzh.customer.technical.springboot;

import com.sun.management.HotSpotDiagnosticMXBean;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-09-10 09:34
 * @description： ApplicationRunner demo
 * @modified By：xzh
 * @version: V1.0.0
 */
@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws IOException {
//        String pid = ManagementFactory.getRuntimeMXBean().getName();
//        pid = pid.substring(0, pid.indexOf('@'));
//        String date = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
//        String fileName = "/tmp/heap_" + pid + "_" + date + ".dump";
//
//        HotSpotDiagnosticMXBean bean = ManagementFactory.newPlatformMXBeanProxy(
//                ManagementFactory.getPlatformMBeanServer(),
//                "com.sun.management:type=HotSpotDiagnostic",
//                HotSpotDiagnosticMXBean.class);
//        bean.setVMOption("HeapDumpOnOutOfMemoryError", "true");
//        bean.setVMOption("HeapDumpPath", fileName);

        log.info("ApplicationRunner测试类 MyRunner running...");

//        List list = new ArrayList();
//        for (; ; ) {
//            log.info("参数: {}", args);
//            list.add(new Data(1l, 1l, 1l, 1l, 1l, 1l));
//        }
    }

//    @AllArgsConstructor
//    class Data {
//        private Long a = 1l;
//        private Long b = 4l;
//        private Long c = 5l;
//        private Long d = 5l;
//        private Long i = 9l;
//        private Long t = 9l;
//    }
}
