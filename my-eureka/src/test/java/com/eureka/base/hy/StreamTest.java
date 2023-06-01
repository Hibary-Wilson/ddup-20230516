package com.eureka.base.hy;

import com.alibaba.fastjson.JSONObject;
import com.eureka.base.BaseTest;
import com.eureka.entity.PersonInfo;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * jdk1.8新特性stream流处理集合、数组
 *
 * @program: itheima20210917_StudyTest
 * @description: 2023-05-12
 * @author: Mr.Huang
 * @create: 2023-05-12 14:45
 **/
public class StreamTest extends BaseTest {

    /**
     * 创建stream流的几种方式:
     *  1、通过 java.util.Collection.stream() 方法用集合创建流;
     *  2、使用java.util.Arrays.stream(T[] array)方法用数组创建流;
     *  3、使用Stream的静态方法：of()、iterate()、generate();
     */
    @Test
    public void createStreamTest() {
        // 1、通过 java.util.Collection.stream() 方法用集合创建流
        Stream<PersonInfo> personStream = getPersonList().stream();                        // 创建一个顺序流
        Stream<PersonInfo> personStream1 = getPersonList().stream().parallel();            // 顺序流转并行流
        Stream<PersonInfo> personStream2 = getPersonList().parallelStream();               // 创建一个并行流


        // 2、使用java.util.Arrays.stream(T[] array)方法用数组创建流
        Stream<String> stream = Arrays.stream(getStrArray());                              // 通过数组创建流


        // 3、使用Stream的静态方法：of()、iterate()、generate()
        Stream<Integer> of1 = Stream.of(1);                                                // 基本数据类型，单个元素创建流
        Stream<Integer> of2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);                    // 基本数据类型，多个元素创建流
        Stream<String> of3 = Stream.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"); // 基本数据类型，多个元素创建流
        Stream<PersonInfo> of4 = Stream.of(new PersonInfo());                              // 引用数据类型，单个元素创建流
        Stream<PersonInfo> of5 = Stream.of(new PersonInfo(), new PersonInfo());            // 引用数据类型，多个元素创建流
        Stream<Integer> iterateStream = Stream.iterate(0, (x) -> x + 3).limit(5);    // 从零开始，生成五个等差数列，为：0, 3, 6, 9, 12
        Stream<Double> generateStream = Stream.generate(Math::random).limit(5);            // 随机生成五个小于1的小数，例如：0.7792229693078766、0.07420111959639553
    }

    /**
     * 遍历/匹配（
     * 1、foreach
     * 2、find
     * 3、match）
     * 4、Stream流中的元素是以Optional类型存在的
     */
    @Test
    public void outputStreamTest() {
        List<PersonInfo> personList = getPersonList();
        List<Integer> intList = getIntList();

        // 1、foreach
        personList.stream().forEach(personInfo -> {                    // 遍历输出所有元素【顺序流】
            System.out.println(JSONObject.toJSONString(personInfo));
        });
        System.out.println("***********分***割***线***********");

        personList.stream().filter(p -> p.getSalary() > 4500).         // 遍历输出薪资大于5000的元素【顺序流】
                forEach(System.out::println);
        System.out.println("***********分***割***线************");

        personList.parallelStream().forEach(personInfo -> {            // 遍历输出所有元素【并行流】
            System.out.println(JSONObject.toJSONString(personInfo));
        });
        System.out.println("***********分***割***线***********");

        personList.parallelStream().filter(p -> p.getSalary() > 4500). // 遍历输出薪资大于5000的元素【并行流】
                forEach(System.out::println);
        System.out.println("***********分***割***线***********");


        // 2、find
        // 匹配第一个大于60的数【取出的数为99】
        Optional<Integer> findFirstStream = intList.stream().filter(a -> a > 60).findFirst();
        System.out.println("输出第一个大于60的数：" + findFirstStream.get());

        // 匹配任意一个大于60的数【由于使用的是并行流，所以结果可能是99、88、98中的任何一个】
        Optional<Integer> findAnyStream = intList.parallelStream().filter(b -> b > 60).findAny();
        System.out.println("输出一个大于60的数：" + findAnyStream.get());


        // 3、match
        boolean match1 = intList.stream().anyMatch(a -> a > 98);
        System.out.println("是否存在大于6的数：" + match1);

        boolean match2 = intList.stream().allMatch(a -> a > 1);
        System.out.println("是否所有元素都大于1：" + match2);

        boolean match3 = intList.parallelStream().anyMatch(a -> a > 98);
        System.out.println("是否存在大于6的数：" + match3);

        boolean match4 = intList.parallelStream().allMatch(a -> a > 0);
        System.out.println("是否所有元素都大于0：" + match4);
    }

    /**
     * 筛选（filter）
     * 1、逻辑运算符直接比较，复杂的逻辑运算需在{}中进行
     * 2、按照一定的规则校验流中的元素，将符合条件的元素提取到新的流中的操作
     */
    @Test
    public void filterStreamTest() {
        List<PersonInfo> personList = getPersonList();
        List<Integer> intList = getIntList();

        // 打印岁数大于30岁的人
        System.out.println("年龄大于30的人：");
        personList.stream().filter(p -> p.getAge() > 30).forEach(System.out::println);

        // 打印大于50的数
        System.out.println("大于50的数：");
        intList.parallelStream().filter(p -> p > 50).forEach(System.out::println);

        // 筛选出薪资高于4500并且地址是关中的人
        System.out.println("薪资高于4500并且地址是关中的人：");
        List<PersonInfo> newPersonList = personList.parallelStream().
                filter(p -> p.getSalary() > 4500 && p.getArea().contains("关中")).collect(Collectors.toList());
        newPersonList.forEach(System.out::println);

        // 筛选出薪资高于4500的人姓名
        System.out.println("薪资高于4500的人姓名：");
        List<String> collect = personList.parallelStream().
                filter(p -> p.getSalary() > 4500).map(PersonInfo::getName).collect(Collectors.toList());
        System.out.println(collect);

        // 如果地址中含有巴蜀，则添加到新的集合，排除null对象
        System.out.println("地址中含有巴蜀的人：");
        List<PersonInfo> list = personList.parallelStream().map(p -> {
            if (p.getArea().contains("巴蜀")) {
                return p;
            } else {
                return null;
            }
        }).filter(p -> p != null).collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 聚合（max、min、count）
     */
    @Test
    public void togetherStreamTest() {
        List<String> strLsit = getStrLsit();
        List<Integer> intList = getIntList();
        List<Float> doubleList = getDoubleList();
        List<PersonInfo> personList = getPersonList();

        // max
        // Comparator.naturalOrder()
        // 集合元素只能是基本数据类型【如果是数字则正常判断大小；如果是中文或字母，则按照字母顺序判断大小（字母越靠前越大）】
        System.out.println("********************max：Comparator.naturalOrder()***********************");
        Optional<String> naturalOrder1 = strLsit.parallelStream().max(Comparator.naturalOrder());
        System.out.println("字符串集合的最大值为：" + naturalOrder1.get());
        Optional<Integer> naturalOrder2 = intList.parallelStream().max(Comparator.naturalOrder());
        System.out.println("正整数集合的最大值为：" + naturalOrder2.get());
        Optional<Float> naturalOrder3 = doubleList.parallelStream().max(Comparator.naturalOrder());
        System.out.println("浮点数集合的最大值为：" + naturalOrder3.get());

        // Comparator.comparing
        // 集合元素可以是基本数据类型，也可以是引用数据类型
        System.out.println("********************max：Comparator.comparing***********************");
        Optional<Integer> compare1 = intList.parallelStream().max(Comparator.comparing(Integer::intValue));
        System.out.println("正整数集合的最大值为：" + compare1.get());
        Optional<Float> compare2 = doubleList.parallelStream().max(Comparator.comparing(Float::floatValue));
        System.out.println("浮点数集合的最大值为：" + compare2.get());
        Optional<String> compare3 = strLsit.parallelStream().max(Comparator.comparing(String::toString));
        System.out.println("字符串集合的最大值为：" + compare3.get());
        Optional<PersonInfo> compare4 = personList.parallelStream().max(Comparator.comparing(PersonInfo::getSalary));
        System.out.println("薪资最高的人是：" + compare4.get());
        Optional<PersonInfo> compare5 = personList.parallelStream().max(Comparator.comparing(PersonInfo::getArea));
        System.out.println("地区排序最高的人是：" + compare5.get());
        Optional<PersonInfo> compare6 = personList.parallelStream().max(Comparator.comparing(PersonInfo::getName));
        System.out.println("姓名排序最高的人是：" + compare6.get()); // 此项结果不准确，输出的一直是“黄忠”，应该是关羽或关兴，不知为何

        // Comparator.comparingInt
        // 只能比较整数，即使输入浮点数，比较的也是整数部分
        System.out.println("********************max：Comparator.comparingInt***********************");
        Optional<PersonInfo> comparingInt1 = personList.parallelStream().max(Comparator.comparingInt(PersonInfo::getSalary));
        System.out.println("薪资最高的人是：" + comparingInt1.get());
        Optional<PersonInfo> comparingInt2 = personList.parallelStream().max(Comparator.comparingInt(PersonInfo::getAge));
        System.out.println("年龄最大的人是：" + comparingInt2.get());
        Optional<Integer> comparingInt3 = intList.parallelStream().max(Comparator.comparingInt(Integer::intValue));
        System.out.println("正整数集合的最大值为：" + comparingInt3.get());
        Optional<Float> comparingInt4 = doubleList.parallelStream().max(Comparator.comparingInt(Float::intValue));
        System.out.println("浮点数集合的整数部分最大值为：" + comparingInt4.get());

        // Comparator.comparingDouble
        // 只能比较浮点型和整数型
        System.out.println("********************max：Comparator.comparingDouble***********************");
        Optional<Float> comparingDouble1 = doubleList.parallelStream().max(Comparator.comparingDouble(Float::doubleValue));
        System.out.println("浮点数集合的最大值为：" + comparingDouble1.get());
        Optional<Integer> comparingDouble2 = intList.parallelStream().max(Comparator.comparingDouble(Integer::intValue));
        System.out.println("正整数集合的最大值为：" + comparingDouble2.get());
        Optional<PersonInfo> comparingDouble3 = personList.parallelStream().max(Comparator.comparingDouble(PersonInfo::getSalary));
        System.out.println("薪资最高的人是：" + comparingDouble3.get());

        // compareTo
        // 只能比较浮点型和整数型
        System.out.println("********************max：compareTo***********************");
        Optional<Integer> compareTo1 = intList.parallelStream().max(Integer::compareTo);
        System.out.println("正整数集合的最大值为：" + compareTo1.get());
        Optional<Float> compareTo2 = getDoubleList().parallelStream().max(Float::compareTo);
        System.out.println("浮点数集合的最大值为：" + compareTo2.get());



        // 自定义排序，输出结果为数组中的最小值，如果改成o1-o2,则结果为集合的最大值
        System.out.println("********************max：自定义***********************");
        Optional<Integer> max4 = intList.parallelStream().max((o1, o2) -> o2 - o1);
        System.out.println("正整数集合的最大值为：" + max4.get());



        // min【用法和max用法一样，上面的max都可以替换成min】
        System.out.println("********************min***********************");
        Optional<Integer> min = intList.parallelStream().min(Comparator.comparing(Integer::intValue));
        System.out.println("正整数集合的最小值为：" + min.get());



        // count
        System.out.println("********************count***********************");
        long count = intList.parallelStream().filter(p -> p > 20).count();
        System.out.println("正整数集合中大于20的元素个数：" + count);
        long count1 = personList.parallelStream().filter(p -> p.getArea().contains("关中")).count();
        System.out.println("地址中包含汉中的人有：" + count1 + "个");
        long count2 = personList.parallelStream().count();
        System.out.println("集合中有：" + count2 + "个元素");


        Optional<Float> max = getDoubleList().parallelStream().max(Comparator.naturalOrder());
        System.out.println("花甲粉：" + max);
        Optional<Float> max1 = getDoubleList().parallelStream().max(Comparator.nullsFirst(Float::compareTo));
        System.out.println("花甲粉1：" + max1);
        Optional<Float> max2 = getDoubleList().parallelStream().max(Comparator.nullsLast(Float::compareTo));
        System.out.println("花甲粉2：" + max2);
        Optional<Integer> max5 = getIntList().parallelStream().max(Comparator.naturalOrder());
        System.out.println("花甲粉5：" + max5);
    }

    /**
     * 映射（map、flatMap）
     * map：接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void mapStreamTest() {
        /**
         * 备注：返回结果的数据类型由map函数控制（基本数据类型还是引用数据类型）
         */

        List<String> englishStr = getEnglishStr();
        List<Integer> intList = getIntList();
        List<String> strLsit = getStrLsit();
        List<Float> doubleList = getDoubleList();
        List<PersonInfo> personList = getPersonList();

        // map
        // 将英文字符全部转大写
        System.out.println("将英文字符全部转大写：");
        List<String> capital = englishStr.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        //capital.forEach(System.out::println);

        // 整数集合每个元素加10
        System.out.println("整数集合每个元素加10：");
        intList = intList.stream().map(x -> x + 10).collect(Collectors.toList());
        //intList.forEach(System.out::println);

        // 将每个员工薪资加1000
        System.out.println("将每个员工薪资加1000：");
        personList = personList.parallelStream().map(person -> {
            person.setSalary(person.getSalary() + 1000);
            return person;
        }).collect(Collectors.toList());
        //personList.forEach(System.out::println);



        // mapToInt、mapToLong、mapToDouble
        // 输出字符串集合中每个元素的长度
        System.out.println("输出字符串集合中每个元素的长度：");
        //strLsit.parallelStream().mapToInt(String::length).forEach(System.out::println);

        // 求集合元素的和
        double sum = doubleList.parallelStream().mapToDouble(Number::doubleValue).sum();
        System.out.println("浮点数集合元素的和为：" + sum);
        int sum1 = intList.parallelStream().mapToInt(Number::intValue).sum();
        System.out.println("正整数集合元素的和为：" + sum1);

        // 求集合元素的平均数
        double average = doubleList.parallelStream().mapToDouble(Number::doubleValue).average().getAsDouble();
        System.out.println("集合元素的平均数为：" + average);

        // 求集合元素的最大值
        double max = doubleList.parallelStream().mapToDouble(Number::doubleValue).max().getAsDouble();
        System.out.println("集合元素的最大值为：" + max);

        // 求员工的最高薪资
        int asInt = personList.parallelStream().mapToInt(PersonInfo::getSalary).max().getAsInt();
        System.out.println("员工的最高薪资为：" + asInt);



        // flatMap、flatMapToInt、flatMapToLong、flatMapToDouble
        // 集合合并--基本数据类型【思路，先将集合转成字符串存入一个新集合中，然后在使用stream流内部切割】
        ArrayList<String> list = new ArrayList<>();
        list.add(strLsit.toString().replace("[","").replace("]",""));
        list.add(englishStr.toString().replace("[","").replace("]",""));
        List<String> newList = list.parallelStream().flatMap(x -> {
            String[] split = x.split(",");
            return Arrays.stream(split);
        }).collect(Collectors.toList());
        newList.forEach(System.out::println);
    }

    /**
     * 归约（reduce）
     * 也称缩减，是把一个流缩减成一个值，能实现对集合求和、求积和求最值操作
     */
    @Test
    public void reduceStreamTest() {
        List<Integer> intList = getIntList();
        List<PersonInfo> personList = getPersonList();

        // 1、基本数据类型集合操作
        // 求和
        Optional<Integer> sum1 = intList.parallelStream().reduce((x, y) -> x + y);
        System.out.println("求和方式1：" + sum1.get());
        Optional<Integer> sum2 = intList.parallelStream().reduce(Integer::sum);
        System.out.println("求和方式2：" + sum2.get());
        Integer sum3 = intList.parallelStream().reduce(0, Integer::sum); // 【此种方式会将identity + sum的值当成集合的值比如identity等于10，则将集合的sum加上10】
        System.out.println("求和方式3：" + sum3);

        // 求乘积
        Optional<Integer> product = intList.parallelStream().reduce((x, y) -> x * y);
        System.out.println("集合的乘积为：" + product.get());

        // 求最大值
        Optional<Integer> max1 = intList.parallelStream().reduce((x, y) -> x > y ? x : y);
        System.out.println("1集合的最大值为：" + max1.get());
        Optional<Integer> max2 = intList.parallelStream().reduce(Integer::max);
        System.out.println("2集合的最大值为：" + max2.get());
        Integer max3 = intList.parallelStream().reduce(1, Integer::max);
        System.out.println("3集合的最大值为：" + max3);


        // 2、引用数据类型集合操作
        // 求最高工资
        Optional<PersonInfo> max4 = personList.parallelStream().reduce((x, y) -> {
            return x.getSalary() > y.getSalary() ? x : y;
        });
        System.out.println("1薪资最高是：" + max4.get().getSalary());
        Optional<Integer> max5 = personList.parallelStream().map(PersonInfo::getSalary).reduce(Integer::max);
        System.out.println("2薪资最高是：" + max5.get());
        Integer max6 = personList.parallelStream().map(PersonInfo::getSalary).reduce(Integer::max).get();
        System.out.println("3薪资最高是：" + max6);
        Integer max7 = personList.parallelStream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), Integer::max);
        System.out.println("4薪资最高是：" + max7);
        Integer max8 = personList.parallelStream().reduce(0, (max, p) -> max > p.getSalary() ? max : p.getSalary(), (max01, max02) -> max01 > max02 ? max01 : max02);
        System.out.println("5薪资最高是：" + max8);

        // 求员工工资之和
        Optional<Integer> sum4 = personList.parallelStream().map(PersonInfo::getSalary).reduce(Integer::sum);
        System.out.println("1员工工资之和为：" + sum4.get());
        Integer sum5 = personList.parallelStream().map(PersonInfo::getSalary).reduce(0, Integer::sum);
        System.out.println("2员工工资之和为：" + sum5);
        Optional<Integer> sum6 = personList.parallelStream().map(PersonInfo::getSalary).reduce((x, y) -> x + y);
        System.out.println("3员工工资之和为：" + sum6.get());
        Integer sum7 = personList.parallelStream().reduce(0, (sum, p) -> sum += p.getSalary(), (sum11, sum12) -> sum11 + sum12);
        System.out.println("4员工工资之和为：" + sum7);
        Integer sum8 = personList.parallelStream().reduce(0, (sum, p) -> sum += p.getSalary(), Integer::sum);
        System.out.println("5员工工资之和为：" + sum8);
    }

    /**
     * 收集（collect）
     *  把一个流收集起来，可以是一个集合，也可以是一个值，
     *  主要依赖java.util.stream.Collectors类内置的静态方法
     * 1、归集（toList/toSet/toMap）
     * 2、统计（count/averaging）
     * 3、分组（partitioningBy/grouping）
     * 4、接合（joining）
     * 5、归约（reducing）
     */
    @Test
    public void collectStreamTest() {
        List<PersonInfo> personList = getPersonList();
        List<Integer> intList = getIntList();

        /**
         * 1、归集（toList、toSet、toMap）
         */
        // 结果转成list集合
        List<Integer> collect1 = intList.parallelStream().filter(p -> p > 20).collect(Collectors.toList());
        // 结果转成set集合
        Set<Integer> collect2 = intList.parallelStream().filter(p -> p > 20).collect(Collectors.toSet());
        // 结果转成map
        Map<String, PersonInfo> map = personList.parallelStream().filter(p -> p.getSalary() > 3000).collect(Collectors.toMap(PersonInfo::getName, p -> p));


        /**
         * 2、统计（count/averaging）
         */
        // 求员工总数
        Long numbOfPeople = personList.parallelStream().collect(Collectors.counting());
        System.out.println("员工总数为：" + numbOfPeople);
        // 计算平均薪资
        Double salary = personList.parallelStream().
                collect(Collectors.averagingDouble(PersonInfo::getSalary));
        System.out.println("平均薪资为：" + salary);
        // 计算最高薪资
        Optional<Integer> maxSalary1 = personList.parallelStream().
                map(PersonInfo::getSalary).max(Comparator.naturalOrder());
        Optional<Integer> maxSalary2 = personList.parallelStream().
                map(PersonInfo::getSalary).collect(Collectors.maxBy(Integer::compare));
        Optional<Integer> maxSalary3 = personList.parallelStream().
                map(PersonInfo::getSalary).collect(Collectors.maxBy(Integer::compareTo));
        System.out.println("1最高薪资为：" + maxSalary1.get());
        System.out.println("2最高薪资为：" + maxSalary2.get());
        System.out.println("3最高薪资为：" + maxSalary3.get());
        // 计算员工工资之和
        Double sumSalary = personList.parallelStream().
                collect(Collectors.summingDouble(PersonInfo::getSalary));
        System.out.println("员工工资之和为：" + sumSalary);
        // 一次性统计员工工资所有信息
        DoubleSummaryStatistics doubleSummaryStatistics = personList.parallelStream().
                collect(Collectors.summarizingDouble(PersonInfo::getSalary));
        System.out.println("员工总数为：" + doubleSummaryStatistics.getCount());
        System.out.println("平均薪资为：" + doubleSummaryStatistics.getAverage());
        System.out.println("最高薪资为：" + doubleSummaryStatistics.getMax());
        System.out.println("最高薪资为：" + doubleSummaryStatistics.getMin());
        System.out.println("员工工资之和为：" + doubleSummaryStatistics.getSum());


        /**
         * 3、分组（partitionBy/groupingBy）
         *     分区：将stream按条件分为两个Map，比如员工按薪资是否高于8000分为两部分。
         *     分组：将集合分为多个Map，比如员工按性别分组。有单级分组和多级分组
         */
        // 按照员工薪资是否大于4600分为两组
        Map<Boolean, List<PersonInfo>> salaryMap = personList.parallelStream().
                collect(Collectors.partitioningBy(p -> p.getSalary() > 4600));
        // 将员工按照性别分组
        Map<String, List<PersonInfo>> sexMap = personList.parallelStream().
                collect(Collectors.groupingBy(PersonInfo::getSex));
        // 先将员工按照性别分组，在按照地区分组
        Map<String, Map<String, List<PersonInfo>>> sexAndAreaMap = personList.parallelStream().
                collect(Collectors.groupingBy(PersonInfo::getSex, Collectors.groupingBy(PersonInfo::getArea)));
        // 先将员工按照性别分组，然后按照
        Map<String, Map<String, Map<Integer, List<PersonInfo>>>> sexAndAreaAndAgeMap = personList.parallelStream().
                collect(Collectors.groupingBy(PersonInfo::getSex, Collectors.groupingBy(PersonInfo::getArea, Collectors.groupingBy(PersonInfo::getAge))));


        /**
         * 4、接合（joining）
         *     joining可以将stream中的元素用特定的连接符（没有的话，则直接连接）连接成一个字符串
         */
        // 取出所有员工的姓名
        String nameListStr = personList.parallelStream().map(PersonInfo::getName).collect(Collectors.joining(","));
        System.out.println("所有员工的姓名字符串：" + nameListStr);
        String namestr = personList.stream().map(PersonInfo::getName).collect(Collectors.joining());
        System.out.println("所有员工的姓名字符串：" + namestr);


        /**
         * 5、归约（reducing）
         */
        Optional<Integer> salarySum = personList.parallelStream().map(PersonInfo::getSalary).reduce(Integer::sum);
        System.out.println("所有员工的工资之和：" + salarySum.get());
    }

    /**
     * 排序（sorted）
     */
    @Test
    public void sortedStreamTest() {
        List<PersonInfo> personList = getPersonList();

        // 按照员工薪资升序排序（自然排序）
        List<PersonInfo> sortAsc = personList.parallelStream().
                sorted(Comparator.comparingDouble(PersonInfo::getSalary)).
                map(p -> {
                    return p;
                }).
                collect(Collectors.toList());

        // 按照员工工资倒序排序
        List<PersonInfo> sprtDesc = personList.parallelStream().
                sorted(Comparator.comparingDouble(PersonInfo::getSalary).reversed()).
                map(p -> {
                    return p;
                }).
                collect(Collectors.toList());

        // 先按员工薪资升序排序，再按年龄升序排序（返回的是员工姓名集合，也可以返回员工集合）
        List<String> sortSalaryAndAge = personList.parallelStream().
                sorted(Comparator.comparing(PersonInfo::getSalary).thenComparingInt(PersonInfo::getAge)).
                map(PersonInfo::getName).collect(Collectors.toList());

        // 先按工资再按年龄自定义排序（降序）
        List<PersonInfo> custom = personList.parallelStream().
                sorted((p1, p2) -> {
                    if (p1.getSalary() == p2.getSalary()) {
                        return p2.getAge() - p1.getAge();
                    } else {
                        return p2.getSalary() - p1.getSalary();
                    }
                }).
                map(p -> {
                    return p;
                }).
                collect(Collectors.toList());
    }

    /**
     * 提取、组合（）
     *
     *  流也可以进行合并、去重、限制、跳过等操作
     */
    @Test
    public void extractAndCombinationStreamTest() {
        List<String> strLsit = getStrLsit();
        List<String> englishStr = getEnglishStr();

        // 将两个集合合并并去重
        List<String> mergeLsit = Stream.concat(strLsit.stream(), englishStr.stream()).
                distinct().
                collect(Collectors.toList());
        System.out.println("合并后的新集合：" + mergeLsit);

        // 限制从流中获得前n个数据【从1开始，间隔为2，生成10个数据】
        List<Integer> collect1 = Stream.iterate(1, x -> x + 2).
                limit(10).
                collect(Collectors.toList());

        // 跳过前n个数据【从1开始，跳过1，间隔为2，生成5个数据,结果为3/5/7/9/11】
        List<Integer> collect = Stream.iterate(1, x -> x + 2).
                skip(1).
                limit(5).
                collect(Collectors.toList());

        // 将两个引用数据类型的集合合并
        List<PersonInfo> collect2 = Stream.concat(getPersonList().stream(), getPersonList().stream()).distinct().
                collect(Collectors.toList());
        collect2.forEach(System.out::println);
    }



    /**
     * 蜀汉对象
     * @return
     */
    public List<PersonInfo> getPersonList() {
        List<PersonInfo> list = new ArrayList<>();
        list.add(new PersonInfo("张飞",5000,35,"F","关中"));
        list.add(new PersonInfo("关羽",5500,36,"F","山卡卡"));
        list.add(new PersonInfo("赵云",4800,32,"F","山卡卡"));
        list.add(new PersonInfo("黄忠",4700,65,"F","关中"));
        list.add(new PersonInfo("马超",5000,32,"F","关中"));
        list.add(new PersonInfo("刘备",1000,38,"F","巴蜀"));
        list.add(new PersonInfo("魏延",4500,34,"F","关中"));
        list.add(new PersonInfo("马岱",4600,25,"F","关中"));
        list.add(new PersonInfo("张苞",4800,26,"F","关中"));
        list.add(new PersonInfo("关兴",4900,27,"F","关中"));
        list.add(new PersonInfo("阿莱卡",4555,30,"M","阿拉善"));
        list.add(new PersonInfo("咖啡壶",4555,30,"M","阿拉善"));
        return list;
    }

    /**
     * 整数集合
     * @return
     */
    public List<Integer> getIntList() {
        return Arrays.asList(1, 2, 9, 0, 5, 6, 3, 5, 22, 99, 10, 31, 54, 98, 98, 20, 20, 41, 53, 42, 19, 88);
    }

    /**
     * 单精度浮点数集合
     * @return
     */
    public List<Float> getDoubleList() {
        return Arrays.asList(22.5f, 22.55f, 22.50f, 0f, 55.0f, 66.0f, 3f, 5.03f, 22.23f, 99.0f, 10.0f,
                31.31f, 54.0f, 98.78f, 20.54f, 20.00f, 41.00f, 53.30f, 42.120f, 10.00f, 99.08f);
    }

    /**
     * 字符串集合
     * @return
     */
    public List<String> getStrLsit() {
        List<String> strLsit = Arrays.asList("akajfhkkjabfj", "大咖啡机", "df2d的地方", "阿道夫", "二标段", "而本科及",
                "不容人拷到开发", "爱哭而", "拉何荣荣", "足协杯", "阿日日", "阿克如", "发布VR一", "安慰该客户", "为鼓励和",
                "女厕u我ioanblarjb", "dfjg");
        return strLsit;
    }

    /**
     * 英文字符串集合
     * @return
     */
    public List<String> getEnglishStr() {
        return Arrays.asList("dfjg","afkjlu","dakru","rn ndfj");
    }


    /**
     * 字符串数组
     * @return
     */
    public String[] getStrArray() {
        String[] str = {"2", "15", "7", "22", "13", "21", "78", "4", "1", "68"};
        return str;
    }

}
