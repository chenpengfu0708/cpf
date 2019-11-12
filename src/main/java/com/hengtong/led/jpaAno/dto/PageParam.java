package com.hengtong.led.jpaAno.dto;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页参数
 * 
 * @author deng.huaiyu
 *
 */
@Data
public class PageParam {

	private int start = 0;

	@Min(value = 1, message = "分页中每页的总数不能小于0")
	private int count = 10;

	private int offSet = 0;

	public int getPageOffSet() {
        int offSet = (start - 1) * count;
		if (offSet < 0) {
			offSet = 0;
		}
		return offSet;
	}
}
