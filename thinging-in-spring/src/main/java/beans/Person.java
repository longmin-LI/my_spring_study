package beans;

/*
描述人的POJO类
Setter/getter方法
可写方法/可读方法
 */
public class Person {

    String name;

    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
