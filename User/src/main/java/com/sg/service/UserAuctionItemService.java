package com.sg.service;

import com.sg.result.Result;

public interface UserAuctionItemService {

    Result getAll(Integer currentPage, Integer pageSize);
}
