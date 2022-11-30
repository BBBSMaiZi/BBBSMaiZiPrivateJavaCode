package com.demo2.service;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: QueryItemCodeService
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-30
 **/
@Service
public class QueryItemCodeService {

    public static void main(String[] args) {
        QueryItemCodeService queryItemCodeService = new QueryItemCodeService();
        List<String> list1 = queryItemCodeService.data1();
        List<String> list2 = queryItemCodeService.data2();
        List<String> list3 = queryItemCodeService.data3();

        ArrayList<List<String>> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, list1, list2, list3);
        System.out.println("数据准备结束----------------------------------");

        ArrayList<List<String>> lists = new ArrayList<>();
        List<String> list11 = queryItemCodeService.getSortedItemCodeList(list1, arrayList);
        List<String> list22 = queryItemCodeService.getSortedItemCodeList(list2, arrayList);
        List<String> list33 = queryItemCodeService.getSortedItemCodeList(list3, arrayList);
    //    Collections.addAll(lists, list11, list22, list33);
    //    Collections.addAll(lists, list11, list22, list33);
        Collections.addAll(lists, list22, list33, list11);
        lists.stream()
                .forEach(System.out::println);



        // 返回所有工序排序的集合
        System.out.println("返回所有工序排序的集合-----------");
        List<String> collect = lists.stream()
                .flatMap(item -> item.stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect);


        /**
         * [a_1, c, d, e, g, a_2]
         * [b_1, c, d, e]
         * [a_1, b_1, c, a_2, b_2, d, e, a_3]
         *
         *  [b_1, c, d, e, g]
         *  [a_1, c, d, e]
         *  [b_1, b_2, c, b_3, b_4, d, e]
         *
         *
         *
         *
         *
         *
         */



    }


    /**
     * 单个编码的排序的方法
     *
     * @param list 传入的编码
     * @return 返回值
     */
    private List<String> getSortedItemCodeList(List<String> list, ArrayList<List<String>> arrayList) {
        List<List<String>> lists = new ArrayList<>();
        arrayList.stream()
                .forEach(item -> {
                    List<String> repeatCode = getRepeatCode(item);
                    lists.add(repeatCode);
                });
        List<String> allRepeatCode = lists.stream()
                .flatMap(item -> item.stream())
                .distinct()
                .collect(Collectors.toList());
        List<String> finalList = getFinalList(list, allRepeatCode);
        return finalList;
    }


    /**
     * 获取集合中重复的编码
     *
     * @param list 未排序的编码的集合
     * @return 返回重复的集合元素
     */
    private List<String> getRepeatCode(List<String> list) {
        List<String> repeatCode = list.stream()
                .filter(item -> list.indexOf(item) != list.lastIndexOf(item))
                .distinct()
                .collect(Collectors.toList());
        return repeatCode;
    }

    /**
     * 获取单个集合排序后的结果
     *
     * @param list       排序之前的集合
     * @param repeatCode 重复的编码
     * @return 返回值
     */
    private List<String> getFinalList(List<String> list, List<String> repeatCode) {
        if (CollectionUtils.isEmpty(repeatCode)) {
            return list;
        }
        ArrayList<String> finalList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            boolean flag = false;
            for (int j = 0; j < repeatCode.size(); j++) {
                if (!repeatCode.contains(list.get(i))) {
                    finalList.add(list.get(i));
                    break;
                } else {
                    if (finalList.indexOf(list.get(i) + "_1") == -1) {
                        finalList.add(list.get(i) + "_1");
                        break;
                    } else {
                        for (int m = 1; m < list.size(); m++) {
                            if (finalList.contains(list.get(i) + "_" + m) && !finalList.contains(list.get(i) + "_" + (m + 1))) {
                                finalList.add(list.get(i) + "_" + (m + 1));
                                flag = true;
                                break;
                            }
                        }
                    }

                }
                if (flag) {
                    break;
                }

            }
        }
        return finalList;
    }

    public List<String> data1() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "b", "c", "a", "b", "d", "e", "a");
        return list;
    }

    public List<String> data2() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "a", "c", "d", "e", "g", "a");
        return list;
    }

    public List<String> data3() {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, "b", "c", "d", "e");
        return list;
    }


}
