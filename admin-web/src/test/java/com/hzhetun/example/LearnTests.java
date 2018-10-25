package com.hzhetun.example;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LearnTests{
    private Logger logger=Logger.getLogger(LearnTests.class);

    @Test
    public void test1(){
        logger.info("hello world");
    }

    @Test
    public void test2(){
        logger.info("abc");
    }

    @Test
    public void test3(){
    }
}
