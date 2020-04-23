package com.iamuse.server.requestVO;

import org.springframework.stereotype.Component;

import com.iamuse.server.responseVO.BaseResponseVO;

@Component
public class RestartVO extends BaseResponseVO {

		private String cameraIP;
		private String touchIP;
		public String getCameraIP() {
			return cameraIP;
		}
		public void setCameraIP(String cameraIP) {
			this.cameraIP = cameraIP;
		}
		public String getTouchIP() {
			return touchIP;
		}
		public void setTouchIP(String touchIP) {
			this.touchIP = touchIP;
		}
		

		

		
}
