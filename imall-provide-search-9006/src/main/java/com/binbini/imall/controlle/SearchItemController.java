package com.binbini.imall.controlle;

import com.binbini.imall.dto.SearchResult;
import com.binbini.imall.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author: BinBin
 * @Date: 2022/10/19/15:51
 * @Description:
 */
@RestController
@RequestMapping(value = "/search/item/")
public class SearchItemController {

    @Autowired
    private SearchItemService searchItemService;

    @PostMapping("import/all")
    public boolean importAllItems() throws IOException {
        return searchItemService.importAllItems();
    }

    @PostMapping("del/all")
    public boolean deleteAllItems() throws IOException {
        return searchItemService.deleteAllItems();
    }

}
