package base.io.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
/**
 * Person需要满足如下的要求，方可序列化
 * 1.需要实现接口：Serializable
 * 2.当前类提供一个全局常量：serialVersionUID
 * 3.除了当前Person类需要实现Serializable接口之外，还必须保证其内部所有属性
 *   也必须是可序列化的。（默认情况下，基本数据类型可序列化）
 *
 *
 * 补充：ObjectOutputStream和ObjectInputStream不能序列化static和transient修饰的成员变量
 *
 *
 * @author zues
 * @create 2021-01-16 12:07:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {

    public static final long serialVersionUID = 123123123123L;

    private String name;
    private int age;
    private int id;
    private Account acct;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
