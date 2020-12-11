package com.xzh.customer.technical.jvm.classLoader;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author ：xzh
 * @date ：Created in 2020-11-27 15:24
 * @description：
 * @modified By：xzh
 * @version: V1.0.0
 */
public class MyClassLoader extends ClassLoader {


    /*
    如果不想打破双亲委派模型，那么只需要重写findClass方法即可
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        File file = getClassFile(name);
        try
        {
            byte[] bytes = getClassBytes(file);
            return this.defineClass(name, bytes, 0, bytes.length);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return super.findClass(name);
    }

    private File getClassFile(String name)
    {
        return new File("/Users/ZHONGHUI/Desktop/ClassLoaderPeople.class");
    }

    private byte[] getClassBytes(File file) throws Exception
    {
        // 这里要读入.class的字节，因此要使用字节流
        FileInputStream fis = new FileInputStream(file);
        FileChannel fc = fis.getChannel();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        WritableByteChannel wbc = Channels.newChannel(stream);
        ByteBuffer by = ByteBuffer.allocate(1024);

        while (true)
        {
            int i = fc.read(by);
            if (i == 0 || i == -1)
                break;
            by.flip();
            wbc.write(by);
            by.clear();
        }

        fis.close();

        return stream.toByteArray();
    }


    /*
    如果想打破双亲委派模型，那么就重写整个loadClass方法
     */
    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return super.loadClass(name, resolve);
        //getClassLoadingLock(name):parallelLockMap中有就返回该类,否则插入map,并返回Object newLock = new Object();
//        synchronized (getClassLoadingLock(name)) {
//            Class<?> c = findLoadedClass(name);
        //如果.class文件没有被加载过，那么会去找加载器的父加载器。如果父加载器不是null（不是Bootstrap ClassLoader），
        // 那么就执行父加载器的loadClass方法，把类加载请求一直向上抛，直到父加载器为null（是Bootstrap ClassLoader）为止
//            if (c == null) {没有加载过该类
//                long t0 = System.nanoTime();
//                try {
//                    if (parent != null) {
//                        c = parent.loadClass(name, false);
//                    } else {
        //返回有引导类加载器加载的类,找不到返回null
//                        c = findBootstrapClassOrNull(name);
//                    }


//                } catch (ClassNotFoundException e) {
//                    // ClassNotFoundException thrown if class not found
//                    // from the non-null parent class loader
//                }
//
//                if (c == null) {
//                    // If still not found, then invoke findClass in order
//                    // to find the class.
//                    long t1 = System.nanoTime();
//                    c = findClass(name);
//
//                    // this is the defining class loader; record the stats
//                    PerfCounter.getParentDelegationTime().addTime(t1 - t0);
//                    PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
//                    PerfCounter.getFindClasses().increment();
//                }
//            }
        //如果要解析这个.class文件的话，就解析一下，解析的作用类加载的文章里面也写了，主要就是将符号引用替换为直接引用的过程
//            if (resolve) {
//                resolveClass(c);
//            }
//            return c;
//        }
    }
}
