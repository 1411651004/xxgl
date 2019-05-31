package com.guochen.utils;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

public class Jpush {
    
    public static void send(String alias,String alert,String masterSecret,String appKey) {
    	JPushClient jpushClient = new JPushClient(masterSecret, appKey, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_alias_alert(alias,alert);

        try {
            PushResult result = jpushClient.sendPush(payload);

        } catch (APIConnectionException e) {
            // Connection error, should retry later

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        }

	}
    public static PushPayload buildPushObject_all_alias_alert(String alias,String alert) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(alert))
                .build();
    }

}
