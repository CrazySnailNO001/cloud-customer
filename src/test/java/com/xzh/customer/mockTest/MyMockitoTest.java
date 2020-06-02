package com.xzh.customer.mockTest;

import com.xzh.customer.technical.myMock.MockController;
import com.xzh.customer.technical.myMock.MockService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;

/**
 * @author ：xzh
 * @date ：Created in 2020-06-02 11:07
 * @description：
 * @modified By：
 * @version:
 */
@RunWith(MockitoJUnitRunner.class)
public class MyMockitoTest {
    private MockMvc mockMvc;

    @InjectMocks
    private MockController mockController;

    @Mock
    private MockService mockService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(mockController)
                .build();
    }

    @Test
    public void test_controller_get() throws Exception {
        //当调用mockService.mockGetTest("李四")方法的时候,返回"success"
        when(mockService.mockGetTest("李四")).thenReturn("success");

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/mock/get_mock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "李四"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        //希望方法被调用一次
        verify(mockService, times(1)).mockGetTest("李四");
        //希望方法至少被调用一次
        verify(mockService, atLeast(1)).mockGetTest("李四");
        verify(mockService, atLeastOnce()).mockGetTest("李四");

        //判断是否存在没有校验的调用
        verifyNoMoreInteractions(mockService);
    }

    @Test
    public void test_service_get() throws Exception {
        when(mockService.mockGetTest("李四")).thenReturn("success");

        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/mock/get_mock")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("name", "张三"))
                .andReturn();

        //如果没有满足条件的话返回空
        System.out.println(mvcResult.getResponse());
    }

    /**
     * verify的用法
     * verify(mock).someMethod();
     * verify(mock, times(10)).someMethod();
     * verify(mock, atLeastOnce()).someMethod();
     */
    @Test(expected = RuntimeException.class)//希望抛出RuntimeException异常
    public void test_list() {
        //创建mock对象,参数可以是类或者接口
        List<String> list = mock(List.class);

        when(list.get(0)).thenReturn("name=test01");
        when(list.get(1)).thenThrow(new RuntimeException("test Exception"));

        String result = list.get(0);

        //验证方法调用
        verify(list).get(0);

        //断言,list的第一个元素是否是"name=test01"
        Assert.assertEquals(result, "name=test01");

        //验证方法没有调用过
        verifyZeroInteractions(list.get(1));
        verifyNoMoreInteractions(list);
        verify(list, never()).get(1);

        String result02 = list.get(1);
    }

    @Test
    public void test_list_02() {
        List<String> list = mock(List.class);

        list.clear();
        int i = list.hashCode();
        i = list.size();

        verify(list).clear();

        //判断是否存在没有校验的调用,会报错,因为hashCode方法没有被校验
//        verifyNoMoreInteractions(list);
    }
}
