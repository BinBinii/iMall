package com.binbini.imall.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: BinBin
 * @Date: 2022/10/18/20:31
 * @Description:
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class SearchResult {
    private Long recordCount;

    private int totalPages;

    private List<SearchItem> itemList;
}
