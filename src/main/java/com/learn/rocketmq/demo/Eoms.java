/**
  * Copyright 2020 bejson.com 
  */
package com.learn.rocketmq.demo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * Auto-generated: 2020-11-03 14:51:35
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Data
public class Eoms {

    private String serCaller;
    private String opTimeStamp;
    private String serSupplier;
    private OutopDetail opDetail;
    private OutsheetDetail sheetDetail;
    private String callerPwd;
    private String callTime;


    public static void main(String[] args) {
        String a = "{\"serCaller\":\"OSS\",\"opTimeStamp\":\"123\",\"serSupplier\":\"BBS\",\"opDetail\":{\"opDetail\":[[{\"fieldChName\":\"产品订单号\",\"fieldEnName\":\"requestID\",\"fieldContent\":\"000A201102057146001\"},{\"fieldChName\":\"产品实例标识\",\"fieldEnName\":\"productCode\",\"fieldContent\":\"99000403075\"},{\"fieldChName\":\"操作描述\",\"fieldEnName\":\"descOutline\",\"fieldContent\":\"薛文杰 派发给: 薛文杰\"},{\"fieldChName\":\"当前进度联系人\",\"fieldEnName\":\"name\",\"fieldContent\":\"薛文杰\"},{\"fieldChName\":\"当前进度联系人所在部门\",\"fieldEnName\":\"department\",\"fieldContent\":\"省公司\"},{\"fieldChName\":\"当前进度联系方式\",\"fieldEnName\":\"contactPhone\",\"fieldContent\":\"13132325656\"},{\"fieldChName\":\"当前进度联系人所在省\",\"fieldEnName\":\"province\",\"fieldContent\":\"陕西\"},{\"fieldChName\":\"当前进度联系人所在地市\",\"fieldEnName\":\"city\",\"fieldContent\":\"宝鸡\"},{\"fieldChName\":\"流程进度\",\"fieldEnName\":\"progress\",\"fieldContent\":\"Z端业务受理\"},{\"fieldChName\":\"是否具备端到端联调\",\"fieldEnName\":\"IsTest\",\"fieldContent\":\"0\"},{\"fieldChName\":\"联调联系人\",\"fieldEnName\":\"TPerson\",\"fieldContent\":\"111\"},{\"fieldChName\":\"联调联系人联系方式\",\"fieldEnName\":\"TPersonPhone\",\"fieldContent\":\"18888888888\"},{\"fieldChName\":\"备注\",\"fieldEnName\":\"remark\",\"fieldContent\":\"0\"}],[{\"fieldChName\":\"产品订单号\",\"fieldEnName\":\"requestID\",\"fieldContent\":\"000A201102057146002\"},{\"fieldChName\":\"产品实例标识\",\"fieldEnName\":\"productCode\",\"fieldContent\":\"99000403075\"},{\"fieldChName\":\"操作描述\",\"fieldEnName\":\"descOutline\",\"fieldContent\":\"薛文杰 派发给: 薛文杰\"},{\"fieldChName\":\"当前进度联系人\",\"fieldEnName\":\"name\",\"fieldContent\":\"薛文杰\"},{\"fieldChName\":\"当前进度联系人所在部门\",\"fieldEnName\":\"department\",\"fieldContent\":\"省公司\"},{\"fieldChName\":\"当前进度联系方式\",\"fieldEnName\":\"contactPhone\",\"fieldContent\":\"13132325656\"},{\"fieldChName\":\"当前进度联系人所在省\",\"fieldEnName\":\"province\",\"fieldContent\":\"陕西\"},{\"fieldChName\":\"当前进度联系人所在地市\",\"fieldEnName\":\"city\",\"fieldContent\":\"宝鸡\"},{\"fieldChName\":\"流程进度\",\"fieldEnName\":\"progress\",\"fieldContent\":\"Z端业务受理\"},{\"fieldChName\":\"是否具备端到端联调\",\"fieldEnName\":\"IsTest\",\"fieldContent\":\"0\"},{\"fieldChName\":\"联调联系人\",\"fieldEnName\":\"TPerson\",\"fieldContent\":\"111\"},{\"fieldChName\":\"联调联系人联系方式\",\"fieldEnName\":\"TPersonPhone\",\"fieldContent\":\"18888888888\"},{\"fieldChName\":\"备注\",\"fieldEnName\":\"remark\",\"fieldContent\":\"0\"}]]},\"sheetDetail\":{\"sheetDetail\":[[{\"fieldChName\":\"BSS工单编号\",\"fieldEnName\":\"serialNo\",\"fieldContent\":\"000A201102057146\"},{\"fieldChName\":\"工单类型\",\"fieldEnName\":\"sheetType\",\"fieldContent\":\"032\"},{\"fieldChName\":\"集团客户业务类型\",\"fieldEnName\":\"serviceType\",\"fieldContent\":\"004\"},{\"fieldChName\":\"子任务工单编号\",\"fieldEnName\":\"provinceNo\",\"fieldContent\":\"JT-032-20150618-000001-陕西-0002\"},{\"fieldChName\":\"操作人\",\"fieldEnName\":\"opPerson\",\"fieldContent\":\"xuewenjie\"},{\"fieldChName\":\"操作人单位\",\"fieldEnName\":\"opCorp\",\"fieldContent\":\"省公司\"},{\"fieldChName\":\"操作人所属部门\",\"fieldEnName\":\"opDepart\",\"fieldContent\":\"省公司\"},{\"fieldChName\":\"操作人联系方式\",\"fieldEnName\":\"opContact\",\"fieldContent\":\"13132325656\"},{\"fieldChName\":\"操作时间\",\"fieldEnName\":\"opTime\",\"fieldContent\":\"20150618 15:36:37\"}]]},\"callerPwd\":\"123\",\"callTime\":\"123\"}";

        Eoms eoms = JSON.parseObject(a, Eoms.class);
        OutopDetail opDetail = eoms.getOpDetail();
        opDetail.getOpDetail().forEach(opDetails -> opDetails.forEach(opDetail1 -> {
            String fieldChName = opDetail1.getFieldChName();
            String fieldContent = opDetail1.getFieldContent();
        }));

        System.out.println(JSON.toJSONString(eoms));
    }
}