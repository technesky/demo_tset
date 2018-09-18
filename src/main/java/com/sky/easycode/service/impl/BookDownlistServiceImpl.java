package com.sky.easycode.service.impl;

import com.sky.easycode.dao.BookDownlistDao;
import com.sky.easycode.service.BookDownlistService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.logging.Logger;

/**
 * 小说列表(BookDownlist)表服务实现类
 *
 * @author makejava
 * @since 2018-09-05 17:33:22
 */
@Service
public class BookDownlistServiceImpl implements BookDownlistService {
    protected Logger logger = Logger.getLogger(BookDownlistServiceImpl.class);
	@Resource
	private BookDownlistDao bookDownlistDao;

}