package com.iamuse.server.responseVO;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;


@Lazy(true)
@Component
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class CrashLogsResponseVO extends BaseResponseVO{
	
	

}
