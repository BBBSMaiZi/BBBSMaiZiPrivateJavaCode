package com.demo2.service;

import com.demo2.bean.Info;
import com.demo2.bean.Inner;
import com.demo2.bean.Outer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @className: DownloadServer
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-25
 **/
@Service
public class DownloadServer {

    public List<Outer> getOutList() {
        ArrayList<Outer> outers = new ArrayList<>();


        Info info1 = new Info("a1", "1");
        Inner inner1 = new Inner("8月", info1);
        Info info2 = new Info("a1", "1");
        Inner inner2 = new Inner("9月", info2);
        Info info3 = new Info("a1", "11");
        Inner inner3 = new Inner("10月", info3);
        ArrayList<Inner> inners = new ArrayList<>();
        Collections.addAll(inners, inner1, inner2, inner3);
        Outer outer1 = new Outer("分料", "A", inners);


        outers.add(outer1);
        getOutList2(outers);
        getOutList3(outers);
        getOutList4(outers);
        getOutList5(outers);
      //  getOutList6(outers);

        return outers;
    }

    public List<Outer> getOutList2(ArrayList<Outer> outers) {

        Info info1 = new Info("a2", "2");
        Inner inner1 = new Inner("8月", info1);
        Info info2 = new Info("a2", "2");
        Inner inner2 = new Inner("9月", info2);
        Info info3 = new Info("a4", "22");
        Inner inner3 = new Inner("10月", info3);
        ArrayList<Inner> inners = new ArrayList<>();
        Collections.addAll(inners, inner1, inner2, inner3);
        Outer outer1 = new Outer("分料", "A", inners);


        outers.add(outer1);


        return outers;
    }

    public List<Outer> getOutList3(ArrayList<Outer> outers) {

        Info info1 = new Info("a3", "3");
        Inner inner1 = new Inner("8月", info1);
        Info info2 = new Info("a4", "2");
        Inner inner2 = new Inner("9月", info2);
        Info info3 = new Info("a5", "13");
        Inner inner3 = new Inner("10月", info3);
        ArrayList<Inner> inners = new ArrayList<>();
        Collections.addAll(inners, inner1, inner2, inner3);
        Outer outer1 = new Outer("分料", "A", inners);


        outers.add(outer1);


        return outers;
    }

    public List<Outer> getOutList4(ArrayList<Outer> outers) {

        Info info1 = new Info("b1", "1");
        Inner inner1 = new Inner("8月", info1);
        Info info2 = new Info("b1", "1");
        Inner inner2 = new Inner("9月", info2);
        Info info3 = new Info("b1", "11");
        Inner inner3 = new Inner("10月", info3);
        ArrayList<Inner> inners = new ArrayList<>();
        Collections.addAll(inners, inner1, inner2, inner3);
        Outer outer1 = new Outer("装配", "B", inners);


        outers.add(outer1);


        return outers;
    }

    public List<Outer> getOutList5(ArrayList<Outer> outers) {

        Info info1 = new Info();
        Inner inner1 = new Inner("8月", info1);
        Info info2 = new Info("b2", "2");
        Inner inner2 = new Inner("9月", info2);
        Info info3 = new Info("b2", "22");
        Inner inner3 = new Inner("10月", info3);
        ArrayList<Inner> inners = new ArrayList<>();
        Collections.addAll(inners, inner1, inner2, inner3);
        Outer outer1 = new Outer("装配", "B", inners);


        outers.add(outer1);


        return outers;
    }

 /*   public List<Outer> getOutList6(ArrayList<Outer> outers) {

        Info info1 = new Info();
        Inner inner1 = new Inner("8月", info1);
        Info info2 = new Info();
        Inner inner2 = new Inner("9月", info2);
        Info info3 = new Info("b3", "13");
        Inner inner3 = new Inner("10月", info3);
        ArrayList<Inner> inners = new ArrayList<>();
        Collections.addAll(inners, inner1, inner2, inner3);
        Outer outer1 = new Outer("装配", "B", inners);


        outers.add(outer1);


        return outers;
    }*/


}
