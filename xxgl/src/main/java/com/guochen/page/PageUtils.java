package com.guochen.page;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtils {

	/**
	 * 生成spring data JPA 对象 描述
	 * 
	 * @param page
	 * @return
	 */
	public static Pageable createPageable(Page page) {
		int startIndex = (page.getPlainPageNum() - 1) * page.numPerPage;
		if (StringUtils.isNotBlank(page.getOrderField())) {
			// 忽略大小写
			if (page.getOrderDirection().equalsIgnoreCase(
					Page.ORDER_DIRECTION_ASC)) {
				return new PageRequest(startIndex, page.getNumPerPage(),
						Sort.Direction.ASC, page.getOrderField());
			} else {
				return new PageRequest(startIndex, page.getNumPerPage(),
						Sort.Direction.DESC, page.getOrderField());
			}
		}

		return new PageRequest(page.getPlainPageNum() - 1, page.getNumPerPage());
	}

	public static RowBounds createRowBounds(Page page) {
		int startIndex = (page.getPlainPageNum() - 1) * page.numPerPage;
		if (StringUtils.isNotBlank(page.getOrderField())) {

			if (page.getOrderDirection().equalsIgnoreCase(
					Page.ORDER_DIRECTION_ASC)) {

				return new RowBounds(startIndex, page.getNumPerPage(),
						Sort.Direction.ASC, page.getOrderField());
			} else {
				return new RowBounds(startIndex, page.getNumPerPage(),
						Sort.Direction.DESC, page.getOrderField());
			}
		}

		return new RowBounds(startIndex, page.getNumPerPage());
	}

	/**
	 * 将springDataPage的属性注入page描述
	 * 
	 * @param page
	 * @param springDataPage
	 */
	@Deprecated
	public static void injectPageProperties(Page page,
			org.springframework.data.domain.Page<?> springDataPage) {
		// 暂时只注入总记录数量
		page.setTotalCount(springDataPage.getTotalElements());
	}
}
