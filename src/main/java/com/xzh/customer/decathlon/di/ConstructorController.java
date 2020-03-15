package com.xzh.customer.decathlon.di;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：xzh
 * @date ：Created in 2020-01-17 15:46
 * @description：
 * @modified By：
 * @version:
 */
@RestController
@RequestMapping("/constructor")
@Slf4j
public class ConstructorController {
    private  DiService diService;
    private  String result;


//    @Autowired
//    public ConstructorController(DiService diService, String result) {
//        System.out.println("第一个构造方法");
//        this.diService = diService;
//        this.result = "第一个构造方法";
//    }

    @Autowired
    public ConstructorController(DiService diService) {
//        System.out.println("第二个构造方法");
        this.diService = diService;
        this.result = diService.test001("constructor");
    }


    @GetMapping("/test001")
    public String test001() {
        return diService.test001(this.result);
    }

    /**
     * 001
    @Autowired
    private A a;

    private final String prefix = a.getExcelPrefix();

    private final String prefix;

     002
    @Autowired
    public Test(A a) {
        this.prefix= a.getExcelPrefix();
    }

    其实这两种方式都可以使用，但报错的原因是加载顺序的问题，@autowired写在变量上的注入要等到类完全加载完，才会将相应的bean注入，而变量是在加载类的时候按照相应顺序加载的，
    所以变量的加载要早于@autowired变量的加载，那么给变量prefix 赋值的时候所使用的a，其实还没有被注入，所以报空指针，而使用构造器就在加载类的时候将a加载了，
    这样在内部使用a给prefix 赋值就完全没有问题。

    如果不适用构造器，那么也可以不给prefix 赋值，而是在接下来的代码使用的地方，通过a.getExcelPrefix()进行赋值，这时的对a的使用是在类完全加载之后，即a被注入了，所以也是可以的。

    总之，@Autowired一定要等本类构造完成后，才能从外部引用设置进来。所以@Autowired的注入时间一定会晚于构造函数的执行时间。但在初始化变量的时候就使用了还没注入的bean，
    所以导致了NPE。若果在初始化其它变量时不使用这个要注入的bean，而是在以后的方法调用的时候去赋值，是可以使用这个bean的，因为那时类已初始化好，即已注入好了。

*/

}
