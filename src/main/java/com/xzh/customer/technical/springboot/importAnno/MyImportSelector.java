package com.xzh.customer.technical.springboot.importAnno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author ：xzh
 * @date ：Created in 2021-01-12 17:00
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.xzh.customer.technical.springboot.importAnno.ImportBeanSelector"};
    }
}
