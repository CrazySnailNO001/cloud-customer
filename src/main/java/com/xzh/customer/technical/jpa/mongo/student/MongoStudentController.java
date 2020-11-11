package com.xzh.customer.technical.jpa.mongo.student;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-06 17:30
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
@RestController
@RequestMapping("/mongo")
public class MongoStudentController {
    @Resource
    private MongoStudentService studentService;

    @PostMapping(value = "student", produces = MediaType.APPLICATION_JSON_VALUE)
    public MongoStudent save(@RequestBody MongoStudent mongoStudent) {
        return studentService.saveOrUpdate(mongoStudent);
    }

    @GetMapping(value = "student", produces = MediaType.APPLICATION_JSON_VALUE)
    public MongoStudent getMongoStudent(Long id) {
        return studentService.findById(id);
    }

    @GetMapping(value = "all_student", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MongoStudent> getMongoStudent(
            @RequestParam(name = "page_num", required = false) Integer pageNum,
            @RequestParam(name = "page_size", required = false) Integer pageSize) {
        return studentService.getAll(pageNum, pageSize);
    }

    @GetMapping(value = "count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countByCondition() {
        return studentService.countByCondition();
    }

//    @GetMapping(value = "condition", produces = MediaType.APPLICATION_JSON_VALUE)
//    public List<MongoStudent> findByCondition(
//            @RequestParam(name = "id", required = false) Long id,
//            @RequestParam(name = "name", required = false) String name,
//            @RequestParam(name = "age", required = false) Integer age,
//            @RequestParam(name = "class_name", required = false) String className,
//            @RequestParam(name = "update_time", required = false) Instant updatedTime) {
//        return studentService.findByCondition(id, name, age, className, updatedTime);
//    }
}
