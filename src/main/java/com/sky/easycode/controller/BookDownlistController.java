package com.sky.easycode.controller;


/**
 * 小说列表(BookDownlist)表控制层
 *
 * @author makejava
 * @since 2018-09-05 17:33:23
 */
@Controller
public class BookDownlistController {
    protected Logger logger = Logger.getLogger(BookDownlistController.class);
	@Resource
	private BookDownlistService bookDownlistService;

}