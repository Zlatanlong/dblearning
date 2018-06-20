package action;

import dao.StudentDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Student;

public class StudentAction {

    public void add(Student s) throws Exception {
        StudentDao dao = new StudentDao();
        dao.addStudent(s);
    }

    public void edit(Student s) throws Exception {
        StudentDao dao = new StudentDao();
        dao.updateStudent(s);
    }
    /**
     * 判断是否有某个学号为num的学生
     * @param num 该学生的学号
     * @return 有返回true，没有返回false
     * @throws Exception 
     */
    public boolean isNum(String num) throws Exception{
        List<Map<String, Object>> params = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("name", "num");
        map.put("rela", "=");
        map.put("value", "'"+num+"'");

        params.add(map);
        StudentDao dao = new StudentDao();
        return !dao.query(params).isEmpty();
    }
    
    public void editAgeByNum(String num,Integer age) throws Exception {   
        Student student=searchByNum(num).get(0);
        student.setAge(age);
        edit(student);        
    }

    /**
     * 按学号删除
     *
     * @param num 想删除的学生学号
     * @throws SQLException
     */
    public void del(String num) throws SQLException {
        StudentDao dao = new StudentDao();
        dao.delStudent(num);
    }

    /**
     * 查询全部学生信息
     *
     * @return
     * @throws Exception
     */
    public List<Student> query() throws Exception {
        StudentDao dao = new StudentDao();
        return dao.query();
    }
    /**
     * 按照学号查找学生
     * @param num
     * @return
     * @throws Exception 
     */
    public List<Student> searchByNum(String num) throws Exception {
        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", "num");
        map.put("rela", "=");
        map.put("value", "'"+num+"'");

        params.add(map);
        StudentDao dao = new StudentDao();
        return dao.query(params);
    }
    
    /**
     * 按照姓名查找学生
     * @param name
     * @return
     * @throws Exception
     */
    public List<Student> searchByName(String name) throws Exception {
        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", "name");
        map.put("rela", "=");
        map.put("value", "'"+name+"'");

        params.add(map);
        StudentDao dao = new StudentDao();
        return dao.query(params);
    }

    /**
     * 按照年龄查找学生
     * @param age
     * @return
     * @throws Exception
     */
    public List<Student> searchByAge(Integer age) throws Exception {
        List<Map<String, Object>> params = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

        map.put("name", "age");
        map.put("rela", "=");
        map.put("value", age);

        params.add(map);
        StudentDao dao = new StudentDao();
        return dao.query(params);
    }
}
