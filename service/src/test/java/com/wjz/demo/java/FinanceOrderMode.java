package com.wjz.demo.java;

public enum FinanceOrderMode {

	YFK {

		@Override
		public String modeName() {
			return "预付款融资";
		}

	},
	DDR {

		@Override
		public String modeName() {
			return "订单融资";
		}

	},
	CDR {

		@Override
		public String modeName() {
			return "仓单融资";
		}

	};

	public String modeCode() {
		return this.name();
	}

	public abstract String modeName();
}
