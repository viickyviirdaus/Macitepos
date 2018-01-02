package com.macitepos.macitepos.api;

import com.macitepos.macitepos.dto.PenggunaDTO;
import com.macitepos.macitepos.services.PenggunaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PenggunaAPI {

    @Autowired
    PenggunaService penggunaService;



    @RequestMapping(path="/api/penggunaTrue", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PenggunaDTO> userTrue(){
        return penggunaService.showAllTrue();
    }

    @RequestMapping(path="/api/penggunaFalse", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PenggunaDTO> userFalse(){
        return penggunaService.showAllFalse();
    }

}
