package com.mq.cloud.controller;

import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @author: yaorp
 */
public class MqConsumerCon {

//    @StreamListener("input")
//    public void receiveInput1(String receiveMsg) {
//        System.out.println(port+"port,input receive: " + receiveMsg);
//    }

    public static void main(String[] args) {
//        int[] a = new int[]{0,2,1,0};
        int[] a = new int[]{1,2,1,0};
        moveZeroes(a);
    }

    public static void moveZeroes( int[] nums){
        if (nums ==null) {
            return;
        }

        // 存储不为0的数
        int j=0;
        for (int i=0; i<nums.length; i++){
            if (nums[i] !=0) {
                nums[j++] = nums[i];
            }
        }

        // 把剩下的设置为0
        for (int i=j; i<nums.length; i++){
            nums[i] =0;
        }
    }


}
