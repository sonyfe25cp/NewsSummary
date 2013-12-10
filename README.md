NewsSummary
===========

an auto summary tool for social news.

服务过程：
第一步： 从/summaryService的POST方法接收json,格式:{'id':12,"texts":["我爱北京天安门xxx","天啊门上太阳升","xxxx"],"source":"http://10.1.0.127:8080/summaryReceiver?summary="}
第二步： 计算完summary之后，根据json中的source返回其结果json格式：{"id":12, "summary":"天安门上太阳升","flag":true}

ps:
	source字段为请求服务者的返回地址，将直接根据其地址返回POST请求,如：http://10.1.0.127:8080/summaryReceiver, post data: summary={json}
	flag字段为是否计算出summary的标志，若为true，则有内容；若为false，则为无返回
