package com.wjz.demo.java;

import org.junit.Assert;
import org.junit.Test;

public class ModeTest {

	@Test
	public void mode() {
		FinanceOrderMode mode = FinanceOrderMode.YFK;
		Assert.assertEquals("YFK", mode.modeCode());
		Assert.assertEquals("预付款融资", mode.modeName());
	}

}
