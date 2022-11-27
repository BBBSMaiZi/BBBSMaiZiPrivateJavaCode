package com.demo2.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.merge.LoopMergeStrategy;
import com.demo2.bean.Outer;
import com.demo2.bean.Result;
import com.demo2.bean.request.DownLoadRequest;
import com.demo2.service.DownloadServer;

import com.demo2.util.AuditUnitLevelReportColumnHandler;
import com.demo2.util.CustomMergeStrategy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.compress.utils.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className: DownloadController
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-25
 **/
@RestController("/easyExcel")
public class DownloadController {

    @Resource
    private DownloadServer downloadServer;


    /*
     * 修改的地方
     *  1.调整列宽
     *  2.单元格合并
     *
     * */

    @GetMapping("/download")
    public void download(HttpServletResponse response) {

        String path = System.getProperty("user.dir");
        System.out.println("path = " + path);

        // 模拟请求数据开始
        DownLoadRequest request = new DownLoadRequest();
        request.setItemCode("02314DDK");
        ArrayList<String> yearMonth = new ArrayList<>();
        Collections.addAll(yearMonth, "202208", "202209", "202210");
        request.setYearMonthList(yearMonth);
        // 模拟请求数据开始

        List<Outer> outList = downloadServer.getOutList();


        try {
            // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("同编码历史制费" + new Date().getTime(), "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");


            // 设置动态表头
            List<List<String>> head = getExcelHead(request);


            List<List<String>> body = getExcelBody(outList);
            List<String> collect = body.stream()
                    .map(info -> info.get(0))
                    .collect(Collectors.toList());


            // 设置要被合并的列
            CustomMergeStrategy customMergeStrategy = new CustomMergeStrategy(collect,0);

            EasyExcel.write(response.getOutputStream())
                    .head(head)
                    .sheet("模板")
                    .registerWriteHandler(customMergeStrategy)
                    .registerWriteHandler(new CustomMergeStrategy(collect,1))
                    .registerWriteHandler(new AuditUnitLevelReportColumnHandler())
                    .doWrite(body);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    /**
     * 设置单元格身体
     *
     * @param outList 排序处理后的数据
     * @return 返回值
     */
    private List<List<String>> getExcelBody(List<Outer> outList) {

        ArrayList<String> propertyName = new ArrayList<>();
        Collections.addAll(propertyName, "operationName", "operationCode", "viewData");

        ArrayList<String> propertyName1 = new ArrayList<>();
        Collections.addAll(propertyName1, "resourceCode", "time");

        JSONArray jsonArray = JSONArray.fromObject(outList);

        ArrayList<List<String>> body0 = new ArrayList<>();

        jsonArray.stream()
                .forEach(info -> {

                    List<String> body1 = new ArrayList<>();
                    JSONObject item = (JSONObject) info;
                    propertyName.stream()
                            .forEach(product -> {
                                if (product.equals("viewData")) {
                                    JSONArray viewData = item.getJSONArray("viewData");
                                    viewData.stream()
                                            .forEach(todo -> {
                                                JSONObject todo1 = (JSONObject) todo;
                                                JSONObject info1 = todo1.getJSONObject("info");
                                                propertyName1.stream()
                                                        .forEach(pro -> {
                                                            String string = info1.getString(pro);
                                                            body1.add(string);
                                                        });
                                            });
                                } else {
                                    String string = item.getString(product);
                                    body1.add(string);
                                }

                            });
                    body0.add(body1);
                });


        return body0;
    }

    /**
     * 获取动态表头
     *
     * @param request 请求参数
     * @return 返回值
     */
    private List<List<String>> getExcelHead(DownLoadRequest request) {
        ArrayList<String> propertyName = new ArrayList<>();
        Collections.addAll(propertyName, "资源编码", "工时");


        List<String> yearMonthList = request.getYearMonthList();
        int size = yearMonthList.size();
        String itemCode = request.getItemCode();

        ArrayList<List<String>> head0 = new ArrayList<>();

        // 设置前两列
        ArrayList<String> head1 = new ArrayList<>();
        head1.add("工艺路线");
        head1.add("工序名称");
        head0.add(head1);
        ArrayList<String> head2 = new ArrayList<>();
        Collections.addAll(head2, "工艺路线", "Code");
        head0.add(head2);

        // 设置后面的动态列
        yearMonthList.stream()
                .forEach(info -> {
                    String title = itemCode + "-" + info;
                    propertyName.stream()
                            .forEach(item -> {
                                ArrayList<String> head3 = new ArrayList<>();
                                Collections.addAll(head3, title, item);
                                head0.add(head3);
                            });


                });
        return head0;
    }


}
