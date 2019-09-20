package com.suo.controller;

import com.suo.service.GaoDeAPI;
import com.suo.service.SuoServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <h3>SuoController  Class</h3>
 *
 * @author : YuXiang
 * @date : 2019-08-07 17:18
 **/
@RestController
@RequestMapping("/test")
public class SuoController {
    @Resource
    private SuoServiceImpl suoService;

    @Resource
    private GaoDeAPI gaoDeAPI;

    /**
     * 生成短链接---------
     * */
    @GetMapping("/suo")
    public String suo(String url){
        return suoService.suo(url);
    }

    @GetMapping("/gaoDe")
    public String suo(){
        return gaoDeAPI.gaoDeSou();
    }
}
