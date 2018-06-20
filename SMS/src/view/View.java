package view;

import action.StudentAction;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Student;

public class View {

    private static final String CONTEXT = "欢迎来到学生信息管理系统！：\n"
            + "下面是学生信息管理系统的功能列表：\n"
            + "[MAIN/M]:\t主菜单\n"
            + "[QUERY/Q]:\t学生信息浏览\n"
            + "[ADD/A]:\t录入学生信息\n"
            + "[UPDATE/U]:\t更新学生信息\n"
            + "[DELETE/D]:\t删除学生信息\n"
            + "[SEARCH/S]:\t查询学生信息(根据年龄或姓名来查询)\n"
            + "[EXIT/E]:\t退出学生信息管理系统\n"
            + "[BREAK/B]:\t退出当前功能，返回主菜单，可以随时退出";

    private static final String OPERATION_MAIN = "MAIN";
    private static final String OPERATION_QUERY = "QUERY";
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_UPDATE = "UPDATE";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_SEARCH = "SEARCH";
    private static final String OPERATION_EXIT = "EXIT";
    private static final String OPERATION_BREAK = "BREAK";

//    public static void main(String[] args) {
//
//        System.out.println(CONTEXT);
//        //怎么保持程序一直运行
//
//        Scanner scan = new Scanner(System.in);
//        Student student = new Student();
//        StudentAction action = new StudentAction();
//        String prenious = null;//当前功能标致
//        Integer step = 1;//当前步骤标致
//
//        String num = null;
//        Integer age = 0;
//
//        while (scan.hasNext()) {
//            String in = scan.next();
//            if (OPERATION_EXIT.equals(in.toUpperCase())
//                    || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
//                System.out.println("您已成功退出学生信息管理系统。");
//                break;
//            } else if (OPERATION_MAIN.equals(in.toUpperCase())
//                    || OPERATION_MAIN.substring(0, 1).equals(in.toUpperCase())
//                    || OPERATION_BREAK.equals(in.toUpperCase())
//                    || OPERATION_BREAK.substring(0, 1).equals(in.toUpperCase())) {
//                System.out.println("\n" + CONTEXT);
//                step = 1;
//                prenious = null;
//            } else if (OPERATION_QUERY.equals(in.toUpperCase())
//                    || OPERATION_QUERY.substring(0, 1).equals(in.toUpperCase())) {
//                try {
//                    List<Student> list = action.query();
//                    for (Student go : list) {
//                        System.out.println(go.toString());
//                    }
//                    System.out.println("浏览全部学生成功！");
//                    System.out.println("\n" + CONTEXT);
//                } catch (Exception e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//
//            } else if (OPERATION_ADD.equals(in.toUpperCase())
//                    || OPERATION_ADD.substring(0, 1).equals(in.toUpperCase())
//                    || OPERATION_ADD.equals(prenious)) {
//                prenious = OPERATION_ADD;
//                //新增学生
//
//                if (1 == step) {
//                    System.out.println("请输入学生的［学号］");
//                } else if (2 == step) {
//                    student.setNum(in);
//                    System.out.println("请输入学生的［姓名］");
//                } else if (3 == step) {
//                    student.setName(in);
//                    System.out.println("请输入学生的［性别］,只能输入'男'或'女'");
//                } else if (4 == step) {
//                    if (in.equals("男") || in.equals("女")) {
//                        student.setSex(in);
//                        System.out.println("请输入学生的［年龄］");
//                    } else {
//                        System.out.println("您输入的性别有误，请重新输入");
//                        step = 3;
//                    }
//                } else if (5 == step) {
//                    if (Integer.valueOf(in) >= 15 && Integer.valueOf(in) <= 45) {
//                        student.setAge(Integer.valueOf(in));
//                        System.out.println("请输入学生的［专业］");
//                    } else {
//                        System.out.println("您输入的年龄有误，请重新输入");
//                        step = 4;
//                    }
//                } else if (6 == step) {
//                    student.setMajor(in);
//                    System.out.println("请输入学生的［奖励］,若没有奖励请填'无'");
//                } else if (7 == step) {
//                    if (!in.equals("无")) {
//                        student.setReward(in);
//                    } else {
//                        student.setReward(null);
//                    }
//                    try {
//                        action.add(student);//添加结束标致归位
//                        step = 1;
//                        prenious = null;
//                        System.out.println("新增学生成功");
//                        System.out.println("\n" + CONTEXT);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        System.out.println("新增学生失败");
//                    }
//                }
//                if (OPERATION_ADD.equals(prenious)) {
//                    step++;
//                }
//            } else if (OPERATION_UPDATE.equals(in.toUpperCase())
//                    || OPERATION_UPDATE.substring(0, 1).equals(in.toUpperCase())
//                    || OPERATION_UPDATE.equals(prenious)) {
//                prenious = OPERATION_UPDATE;
//                //修改学生信息（按照学号修改年龄）
//                if (1 == step) {
//                    System.out.println("请输入要修改学生的［学号］");
//                } else if (2 == step) {
//                    try {
//                        if (action.isNum(in)) {
//                            num = in;
//                            System.out.println("请输入要修改学生的［年龄］");
//                        } else {
//                            System.out.println("您输入的学号是" + in + "，没有找到学号为该学号的学生，请重新输入");
//                            step = 1;
//                        }
//                    } catch (Exception ex) {
//                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                } else if (3 == step) {
//                    if (Integer.valueOf(in) >= 15 && Integer.valueOf(in) <= 45) {
//                        age = Integer.valueOf(in);
//                        try {
//                            action.editAgeByNum(num, age);
//                            prenious = null;
//                            step = 1;
//                            System.out.println("修改成功");
//                            System.out.println("\n" + CONTEXT);
//                        } catch (Exception ex) {
//                            System.out.println("修改失败");
//                            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    } else {
//                        System.out.println("您输入的年龄有误，请重新输入");
//                        step = 2;
//                    }
//                }
//                if (OPERATION_UPDATE.equals(prenious)) {
//                    step++;
//                }
//            } else if (OPERATION_DELETE.equals(in.toUpperCase())
//                    || OPERATION_DELETE.substring(0, 1).equals(in.toUpperCase())
//                    || OPERATION_DELETE.equals(prenious)) {
//                //删除学生信息      
//                prenious = OPERATION_DELETE;
//                if (1 == step) {
//                    System.out.println("请输入要删除学生的［学号］");
//                } else if (2 == step) {
//                    try {
//                        if (action.isNum(in)) {
//                            action.del(in);
//                            step = 1;
//                            prenious = null;
//                            System.out.println("删除学生成功");
//
//                        } else {
//                            step = 1;
//                            System.out.println("您输入的学号是" + in + "，没有找到学号为该学号的学生，请重新输入");
//                        }
//                    } catch (Exception ex) {
//                        System.out.println("删除学生失败");
//                        Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//
//                }
//                if (OPERATION_DELETE.equals(prenious)) {
//                    step++;
//                }
//            } else if (OPERATION_SEARCH.equals(in.toUpperCase())
//                    || OPERATION_SEARCH.substring(0, 1).equals(in.toUpperCase())) {
//                //查询学生信息（按照姓名和年龄）  
//                //2层菜单
//                while (true) {
//                    System.out.println("请输入要查询的依据：" + "\n" + "      [NAME/N]姓名  [AGE/A]年龄");
//                    String choose = scan.next();
//                    if ("NAME".equals(choose.toUpperCase())
//                            || "NAME".substring(0, 1).equals(choose.toUpperCase())) {
//                        System.out.println("请输入学生姓名： ");
//                        String s = scan.next();
//                        try {
//                            List<Student> list = action.searchByName(s);
//                            if (!list.isEmpty()) {
//                                System.out.println(list);
//                                for (Student go : list) {
//                                    System.out.println(go.toString());
//                                }
//                                System.out.println("查询学生成功");
//                                System.out.println("\n" + CONTEXT);
//                            } else {
//                                System.out.println("没找到");
//                                System.out.println("\n" + CONTEXT);
//                            }
//                            break;
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else if ("AGE".equals(choose.toUpperCase())
//                            || "AGE".substring(0, 1).equals(choose.toUpperCase())) {
//                        System.out.println("请输入学生年龄： ");
//                        Integer s = Integer.valueOf(scan.next());
//                        try {
//                            List<Student> list = action.searchByAge(s);
//                            if (!list.isEmpty()) {
//                                for (Student go : list) {
//                                    System.out.println(go.toString());
//                                }
//                                System.out.println("查询学生成功");
//                                System.out.println("\n" + CONTEXT);
//                            } else {
//                                System.out.println("没找到");
//                                System.out.println("\n" + CONTEXT);
//                            }
//                            break;
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    } else {
//                        System.out.println("您输入的值为:" + choose + "  请重新输入");
//                    }
//                }
//            } else {
//                System.out.println("您输入的值为:" + in + "  未找到该功能，请重新输入");
//            }
//
//        }
//    }
}
