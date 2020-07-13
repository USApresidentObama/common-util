package com.commom.util;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

/**
 * List相关工具类
 */
public class ListUtil {

    /**
     * 判断某一元素是否在列表中并返回
     *
     * @param element
     *            待检测元素
     * @param supportValues
     *            检测范围列表
     * @param defaultValue
     *            默认值,不能为空
     * @return 如果 element 为空或者不在列表中则返回 defaultValue值,否则返回element本身.
     */
    public static <T> T checkAndGet(T element, Set<T> supportValues, T defaultValue) {
        // 未做限制
        if (ValidUtil.isEmpty(supportValues)) {
            return element;
        }

        if (element == null) {
            return defaultValue;
        }

        if (supportValues.contains(element)) {
            return element;
        }
        return defaultValue;
    }

    /**
     * 判断某一元素是否在指定范围中并返回
     *
     * @param element
     *            待检测元素
     * @param range
     *            范围
     * @param defaultValue
     *            默认值,不能为空
     * @return 如果 element 为空或者范围中则返回 defaultValue值,否则返回element本身.
     * @since 0.0.3
     */
    public static <T extends Comparable<T>> T checkAndGetByRange(T element, Range<T> range, T defaultValue) {
        // 未做限制
        if (range == null) {
            return element;
        }

        if (element == null) {
            return defaultValue;
        }
        if (range.contains(element)) {
            return element;
        }
        return defaultValue;
    }

    /**
     * 判断某一元素是否在列表中并返回<br>
     * 如果 element 为空则不追加,否则追加element.
     *
     * @param list
     *            列表
     * @param element
     *            待添加元素
     */
    public static <T> void checkAndAdd(List<T> list, T element) {
        if (element == null || list == null) {
            return;
        }
        list.add(element);
    }

    /**
     * 转换成Map
     *
     * @param list
     * @param keyFunction
     * @return
     */
    public static <O, K> Map<K, O> toMap(List<O> list, Function<O, K> keyFunction) {
        if (list == null || list.isEmpty() || keyFunction == null) {
            return Maps.newHashMap();
        }
        // list.stream().collect(Collectors.toMap(keyMapper, valueMapper));

        Map<K, O> resultMap = Maps.newHashMap();
        K key = null;
        for (O o : list) {
            if (o == null) {
                continue;
            }
            key = keyFunction.apply(o);
            if (key == null) {
                continue;
            }
            resultMap.put(key, o);
        }
        return resultMap;
    }

    /**
     * 转换成Map
     *
     * @param list
     * @param keyFunction
     *            key处理
     * @param convertFunction
     *            将O转换成V
     * @return
     */
    public static <O, K, V> Map<K, V> toMap(List<O> list, Function<O, K> keyFunction, Function<O, V> convertFunction) {
        if (list == null || list.isEmpty() || keyFunction == null || convertFunction == null) {
            return Maps.newHashMap();
        }
        Map<K, V> resultMap = Maps.newHashMap();
        K key = null;
        for (O o : list) {
            if (o == null) {
                continue;
            }
            key = keyFunction.apply(o);
            if (key == null) {
                continue;
            }
            resultMap.put(key, convertFunction.apply(o));
        }
        return resultMap;
    }

    // ----------------------------------------- convert

    /**
     * list 转换F(rom) => T(o)
     *
     * @param list
     * @param convert
     * @return
     */
    public static <F, T> List<T> convert(List<F> list, Function<F, T> convert) {
        if (list == null || list.isEmpty() || convert == null) {
            return Lists.newArrayList();
        }

        List<T> backList = Lists.newArrayList();
        T t = null;
        for (F f : list) {
            if (f == null) {
                continue;
            }
            t = convert.apply(f);
            if (t == null) {
                continue;
            }
            backList.add(t);
        }
        return backList;
    }

//    /**
//     * Long集合 转换为Integer集合
//     *
//     * @param list
//     *            {@link Long}集合(注:如long==null or long<=0则转换为0)
//     * @return
//     */
//    public static List<Integer> longList2IntegerList(List<Long> list) {
//        if (ValidUtil.isEmpty(list)) {
//            return Lists.newArrayList();
//        }
//        return convert(list, ConvertFunctions.longToIntegerFunction());
//    }

    // -----------------------------------------
    /**
     * 列表中查询匹配pattern的列表元素<br>
     *
     * @param list
     *            列表
     * @param pattern
     *            匹配的字符串
     * @return 匹配<code>pattern</code> 的元素列表
     */
    public static List<String> filter(List<String> list, String pattern) {
        if (ValidUtil.isEmpty(list)) {
            return Lists.newArrayList();
        }
        if (StringUtil.isBlank(pattern)) {
            return list;
        }
        return Lists.newArrayList(Iterables.filter(list, Predicates.containsPattern(pattern)));
    }

    // -----------------------------------------

    /**
     * list 提取F(rom) => T(o)
     *
     * @param list
     *            列表
     * @param fetch
     *            提取的规则
     * @return
     */
    public static <F, T> List<T> fetch(List<F> list, Function<F, T> fetch) {
        if (list == null || list.isEmpty() || fetch == null) {
            return Lists.newArrayList();
        }
        List<T> backList = Lists.newArrayList();
        T t = null;
        for (F f : list) {
            if (f == null) {
                continue;
            }
            t = fetch.apply(f);
            if (t == null) {
                continue;
            }
            backList.add(t);
        }
        return backList;
    }

    /**
     * 合并list(排重)
     *
     * @param list
     *            集合
     * @param value
     *            增加值
     * @return List<T>
     */
    public static <T> List<T> merge(List<T> list, T value) {
        list = CommonUtil.nullToDefault(list, new ArrayList<T>());
        if (value == null) {
            return list;
        }
        if (Sets.newHashSet(list).contains(value)) {
            return list;
        }
        //
        boolean enableAdd = true;
        if (value instanceof String) {
            if (StringUtil.isBlank((String) value)) {
                enableAdd = false;
            }
        }
        if (enableAdd) {
            list.add(value);
        }
        return list;
    }

    /**
     * 集合新增元素<br>
     * 如果不传function，代表元素一定增加到list中<br>
     * 如果传入的function的apply方法返回true,加到list中，返回false,舍弃<br>
     *
     * @param list
     *            增加的集合
     * @param value
     *            要增加的集合元素
     * @param _function
     *            方法,可以自己实现筛选的元素
     * @return
     * @createTime 2019年3月29日 下午3:02:11
     * @author sunminggui
     */
    public static <T> List<T> add(List<T> list, T value, Function<T, Boolean> _function) {
        if (list == null) {
            list = Lists.newArrayList();
        }
        if (value == null) {
            return list;
        }
        if (_function == null) {
            list.add(value);
            return list;
        }
        Boolean isAdd = _function.apply(value);
        if (isAdd != null && isAdd) {
            list.add(value);
        }
        return list;
    }

    /**
     * 合并list(排重且保证顺序)，排除""、null
     *
     * @param listAry
     *            集合
     * @return List<T>
     */
    public static <T> List<T> sortMerge(List<T>... listAry) {
        return sortMerge(null, listAry);
    }

    /**
     * 合并list(排重且保证顺序)，排除""、null
     *
     * @param _maxSize
     *            合并成功后的list的最大长度
     * @param listAry
     *            合并的多个list
     * @return List<T>
     */
    public static <T> List<T> sortMerge(Integer _maxSize, List<T>... listAry) {
        if (listAry == null || listAry.length <= 0) {
            return Lists.newArrayList();
        }
        List<T> resultList = Lists.newArrayList();
        Set<T> existElementSet = Sets.newHashSet();// 只为了排重使用，方便contains
        for (List<T> list : listAry) {
            if (ValidUtil.isEmpty(list)) {
                continue;
            }
            for (T t : list) {
                if (_maxSize != null && resultList.size() >= _maxSize) {
                    return resultList;
                }
                if (t == null) {
                    continue;
                }
                if (t instanceof String && StringUtil.isBlank((String) t)) {
                    continue;
                }
                if (!existElementSet.contains(t)) {
                    resultList.add(t);
                    existElementSet.add(t);
                }
            }
        }
        return resultList;
    }

    /**
     * 拆分数组
     *
     * @param inputList
     *            要拆分的数组
     * @param eachGroupCount
     *            每个数组的元素个数
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> inputList, int eachGroupCount) {
        if (inputList == null || inputList.isEmpty() || eachGroupCount < 1) {
            return Lists.newArrayList();
        }
        List<List<T>> backGroupList = new ArrayList<List<T>>();
        int totalCount = inputList.size();
        // 待拆分数据不足直接返回
        if (totalCount <= eachGroupCount) {
            backGroupList.add(inputList);
            return backGroupList;
        }
        // 计算出分组数
        int groupSize = totalCount % eachGroupCount == 0 ? totalCount / eachGroupCount
                : (totalCount / eachGroupCount) + 1;
        for (int groupIndex = 0; groupIndex < groupSize; groupIndex++) {
            int endIndex = (groupIndex + 1) * eachGroupCount;
            if (endIndex >= totalCount) {
                endIndex = totalCount;
            }
            backGroupList.add(inputList.subList(groupIndex * eachGroupCount, endIndex));
        }
        return backGroupList;
    }

    /**
     * 截取list指定元素<br>
     * <ol>
     * <li>subList([1,2,3],0,2)==>[1,2]</li>
     * <li>subList([1],0,2)==> [1]</li>
     * <li>subList([],2,3)==> []</li>
     * </ol>
     *
     * @param list
     *            集合
     * @param fromIndex
     *            截取开始元素下标
     * @param toIndex
     *            截取结束元素下标
     * @return
     */
    public static List<Long> subList(List<Long> list, int fromIndex, int toIndex) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (toIndex > list.size()) {
            toIndex = list.size();
        }
        int newSize = toIndex - fromIndex;
        if (newSize <= 0) {
            return Lists.newArrayList();
        }
        Long[] array = list.toArray(new Long[list.size()]);
        Long[] subarray = new Long[newSize];
        System.arraycopy(array, fromIndex, subarray, 0, newSize);
        return Arrays.asList(subarray);
    }

    /**
     * 去重<br>
     * list =[1L, 1L, 2L, 0L, null] <br>
     * 1.removeDuplicate(list, null) // 结果: [0, null, 1, 2] <br>
     * 2.removeDuplicate(list,BCommonPredicates.LongPredicates.Valid) // 结果:[1,2]
     *
     * @param <T>
     *
     * @param list
     *            列表
     * @param predicate
     *            过滤方法(只查询符合条件的数据)
     * @return
     */
    public static <T> List<T> removeDuplicate(List<T> list, Predicate<T> predicate) {
        if (list == null || list.isEmpty()) {
            return Lists.newArrayList();
        }
        // 耗时待测试 remark by zhangbw
        // if (predicate == null) {
        // return list.stream().distinct().collect(Collectors.toList());
        // }
        // return
        // list.stream().filter(predicate).distinct().collect(Collectors.toList());

        if (predicate == null) {
            return Lists.newArrayList(Sets.newHashSet(list));
        }
        //
        Set<T> set = new HashSet<T>();
        for (T value : list) {
            if (predicate.test(value)) {
                set.add(value);
            }
        }
        return new ArrayList<T>(set);
    }

    /**
     * String 转化为List<Long><br>
     * trim处理
     *
     * @param data
     *            指定分隔符(<code>separatorChar</code>)字符串
     * @param separatorChar
     *            字符串的分隔符(如为空则默认",")
     * @return List<Long>
     */
    public static List<Long> string2LongList(String data, String separatorChar) {
        if (StringUtil.isBlank(data)) {
            return null;
        }
        if (StringUtil.isBlank(separatorChar)) {
            separatorChar = ",";
        }
        String[] items = StringUtils.split(data, separatorChar);
        List<Long> result = new ArrayList<Long>(items.length);
        for (String item : items) {
            try {
                result.add(Long.valueOf(item.trim()));
            } catch (NumberFormatException e) {
//                MonitorLogger.getInstance().log(MessageFormat.format("参数类型不正确,item:{0}", item));
                continue;
            }
        }
        return result;
    }

    /**
     * String 转化为List<Long><br>
     *
     * @param data
     *            ","分隔的字符串
     * @return List<Long>
     */
    public static List<Long> string2LongList(String data) {
        return string2LongList(data, ",");
    }

    /**
     * 将String转换成List<Integer>
     *
     * @param data
     *            ","分隔的字符串
     * @return List<Integer>
     */
    public static List<Integer> string2IntegerList(String data) {
        return string2IntegerList(data, ",");
    }

    /**
     * 将String转换成List<Integer>
     *
     * @param data
     *            <code>separatorChar</code>分隔的字符串
     * @param separatorChar
     *            字符串的分隔符(如为空则默认",")
     * @return List<Integer>
     */
    public static List<Integer> string2IntegerList(String data, String separatorChar) {
        if (StringUtil.isBlank(data)) {
            return null;
        }

        if (StringUtil.isBlank(separatorChar)) {
            separatorChar = ",";
        }
        String[] items = StringUtils.split(data, separatorChar);
        List<Integer> result = new ArrayList<Integer>(items.length);
        for (String item : items) {
            result.add(Integer.valueOf(item.trim()));
        }
        return result;
    }
}
