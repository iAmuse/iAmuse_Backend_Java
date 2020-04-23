package com.iamuse.admin.VO;

public class BarChartResponseVO {

	private String name;
	private float y;
	private String drilldown;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public String getDrilldown() {
		return drilldown;
	}

	public void setDrilldown(String drilldown) {
		this.drilldown = drilldown;
	}

	@Override
	public String toString() {
		return "BarChartResponseVO [name=" + name + ", y=" + y + ", drilldown="
				+ drilldown + "]";
	}

}